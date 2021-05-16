package cn.yzaaa.msmservice.controller;

import cn.yzaaa.msmservice.service.MsmService;
import cn.yzaaa.msmservice.utils.RandomUtil;
import cn.yzaaa.vod.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Honglixi
 * @create 2021-05-16-16:03
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;
    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //生成随机数，传到阿里云
        String code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(param,phone);
        if (isSend){
            return R.ok();
        }else {
            return R.error().message("短信发送失败");
        }
    }
}
