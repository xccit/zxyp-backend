package io.xccit.zxyp.controller.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.system.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description 文件上传
 */
@Tag(name = "文件处理接口")
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {

    @Autowired
    private IFileUploadService fileUploadService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @Operation(summary = "文件上传")
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file){
        String url = fileUploadService.upload(file);
        return AjaxResult.build(url, ResultCodeEnum.SUCCESS);
    }
}
