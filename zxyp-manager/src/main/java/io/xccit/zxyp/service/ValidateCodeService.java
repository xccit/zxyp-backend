package io.xccit.zxyp.service;

import io.xccit.zxyp.model.vo.system.ValidateCodeVo;

/**
 * @author CH_ywx
 * @date 2023/10/15
 * @description 验证码服务
 */
public interface ValidateCodeService {

    /**
     * 生成图片验证码
     * @return
     */
    ValidateCodeVo generateValidateCode();
}
