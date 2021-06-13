package cn.yzaaa.eduorder.client;

import cn.yzaaa.vod.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Honglixi
 * @create 2021-06-13 21:53
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //根据用户id获取用户信息
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
