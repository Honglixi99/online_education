package cn.yzaaa.vod.eduservice.controller;

import cn.yzaaa.vod.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author Honglixi
 * @create 2021-04-25-12:35
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //解决跨越问题
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
