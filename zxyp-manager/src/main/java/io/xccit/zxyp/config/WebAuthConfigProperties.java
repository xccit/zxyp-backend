package io.xccit.zxyp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/17
 * @description web权限配置
 */
@Data
@ConfigurationProperties(prefix = "zxyp.web.auth")
public class WebAuthConfigProperties {
    private List<String> noAuthUrls;
}
