package io.xccit.zxyp.service.impl;

import cn.hutool.core.date.DateUtil;
import io.minio.*;
import io.minio.errors.MinioException;
import io.xccit.zxyp.exception.FileUploadException;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.IFileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description
 */
@Slf4j
@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.bucket}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    private String url;

    /**
     * 文件上传接口
     *
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) {

        try {
            //TODO 创建MinIO客户端
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(endPoint)
                            .credentials(accessKey, secretKey)
                            .build();
            //TODO 创建bucket
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                log.info("Bucket" + bucketName + " already exists.");
            }
            //TODO 流式文件上传
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replaceAll("-", "");
            //TODO 防止文件名重复+日期文件夹分类
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String filename = dateDir + "/" + uuid + file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
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
            stringBuilder.append(File.separator);
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
        }
        return url;
    }
}
