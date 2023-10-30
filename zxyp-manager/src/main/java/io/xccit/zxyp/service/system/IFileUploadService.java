package io.xccit.zxyp.service.system;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description 文件上传
 */
public interface IFileUploadService {
    /**
     * 文件上传接口
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
