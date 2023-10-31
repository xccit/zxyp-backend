package io.xccit.zxyp.utils;

import cn.hutool.core.date.DateUtil;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.xccit.zxyp.exception.FileUploadException;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ch_ywx
 * @data 2023/10/31
 * @description minio工具类
 */
@Slf4j
@Component
public class MinioUtil {

    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.bucket}")
    private String bucketName;
    @Autowired
    private MinioClient minioClient;
    //返回的图片路径
    private String url;

    /**
     * 判断bucket是否存在，不存在则创建
     */
    public boolean isExistBucket(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("bucket exit, {}", e);
        }
        return false;
    }

    /**
     * 流式上传文件
     * @param file
     * @return
     */
    public String upload(MultipartFile file){
        InputStream inputStream = null;
        try {
            assertBucketExist(bucketName);
            //TODO 流式文件上传
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replaceAll("-", "");
            //TODO 防止文件名重复+日期文件夹分类
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String filename = dateDir + "/" + uuid + file.getOriginalFilename();
            inputStream = file.getInputStream();
            long size = file.getSize();
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName)
                            .object(filename)
                            .stream(inputStream, size, -1)
                            .build());
            //TODO 构建文件路径
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(endPoint);
            stringBuilder.append(bucketName);
            stringBuilder.append("/");
            stringBuilder.append(filename);
            url = stringBuilder.toString();
        } catch (MinioException e) {
            e.printStackTrace();
            throw new FileUploadException(ResultCodeEnum.DATA_ERROR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return url;
    }

    /**
     * 创建存储bucket
     */
    public Boolean createBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除存储bucket
     */
    public Boolean removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 下载文件
     */
    public ResponseEntity<byte[]> download(String fileName, String bucketName) {
        assertBucketExist(bucketName);

        ResponseEntity<byte[]> responseEntity = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {

            in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
            out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            //封装返回值
            byte[] bytes = out.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            headers.setContentLength(bytes.length);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setAccessControlExposeHeaders(List.of("*"));

            responseEntity = new ResponseEntity(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

    /**
     * 判断bucket是否存在
     *
     * @param bucketName
     */
    private void assertBucketExist(String bucketName) {
        //TODO 创建bucket
        boolean found =
                false;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                log.info("Bucket" + bucketName + " already exists.");
            }
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件下载
     *
     * @param fileName
     * @param bucketName
     * @param response
     */
    public void downloadFile(String fileName, String bucketName, HttpServletResponse response) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
            int length = 0;
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            response.reset();
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization,Content-Disposition");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setContentType("application/octet-stream");
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            try {
                in.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        assert out != null;
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    public InputStream downloadFile(String fileName, String bucketName) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return in;
    }

    /**
     * 批量删除文件对象
     */
    public Iterable<Result<DeleteError>> removeObjects(String bucketName, List<String> objects) {
        List<DeleteObject> dos = objects.stream().map(e -> new DeleteObject(e)).collect(Collectors.toList());
        Iterable<Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(dos).build());
        return results;
    }

    /**
     * @param bucketName:
     * @description: 删除桶下面所有文件
     */
    public void deleteBucketFile(String bucketName) {
        try {
            if (StringUtils.isBlank(bucketName)) {
                return;
            }

            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (isExist) {
                minioClient.deleteBucketEncryption(DeleteBucketEncryptionArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    /**
     * 删除文件
     *
     * @param bucketName
     * @param fileName
     * @return
     */
    public boolean removeFile(String bucketName, String fileName) {
        boolean flag = true;
        try {
            //判断桶是否存在
            boolean res = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (res) {
                //删除文件
                minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName)
                        .object(fileName).build());
                log.info("删除文件成功");
            }
        } catch (Exception e) {
            log.error("删除文件失败");
            e.printStackTrace();
            flag = false;
        }

        return flag;

    }

    /**
     * 判断文件是否存在
     *
     * @param bucketName 存储桶
     * @param objectName 对象
     * @return true：存在
     */
    public boolean doesObjectExist(String bucketName, String objectName) {
        boolean exist = true;
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }


}