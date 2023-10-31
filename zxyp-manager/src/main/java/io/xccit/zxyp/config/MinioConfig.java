package io.xccit.zxyp.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description
 */
@Configuration
public class MinioConfig {

    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.bucket}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient minioClient(){
        //TODO 创建MinIO客户端
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(endPoint)
                        .credentials(accessKey, secretKey)
                        .build();
        return minioClient;
    }
}
