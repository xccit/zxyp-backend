package io.xccit.zxyp.client.user;

import io.xccit.zxyp.model.entity.user.UserAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author CH_ywx
 * @date 2023/11/9
 * @description
 */
@FeignClient(value = "service-user")
public interface IUserClients {

    /**
     * 根据ID获取地址信息
     * @param id
     * @return
     */
    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    UserAddress getAddressById(@PathVariable Long id);
}
