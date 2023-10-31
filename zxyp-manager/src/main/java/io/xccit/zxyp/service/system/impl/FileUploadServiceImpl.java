package io.xccit.zxyp.service.system.impl;

import cn.hutool.core.date.DateUtil;
import io.minio.*;
import io.minio.errors.MinioException;
import io.xccit.zxyp.exception.FileUploadException;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.system.IFileUploadService;
import io.xccit.zxyp.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 文件上传接口
     *
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) {
        String url = minioUtil.upload(file);
        return url;
    }
}
