package cn.yzaaa.educenter.controller;

import cn.yzaaa.educenter.entity.UcenterMember;
import cn.yzaaa.educenter.service.UcenterMemberService;
import cn.yzaaa.educenter.utils.ConstantWxUtils;
import cn.yzaaa.educenter.utils.HttpClientUtils;
import cn.yzaaa.vod.commonutils.JwtUtils;
import cn.yzaaa.vod.servicebase.exceptionhandler.GuliException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author Honglixi
 * @create 2021-05-28-20:26
 */
@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;

    //2.获取扫描人信息
    @GetMapping("callback")
    public String callback(String code,String state){
//        System.out.println("code:" + code);
////        System.out.println("state:" + state);
        try {
        //1 获取code值，临时票据，类似于验证码
        //2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            System.out.println(accessTokenInfo);
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String) mapAccessToken.get("access_token");
            String openid = (String) mapAccessToken.get("openid");

            //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            //拼接两个参数
            String userInfoUrl = String.format(
                    baseUserInfoUrl,
                    access_token, openid
            );
            //发送请求
            String userInfo = HttpClientUtils.get(userInfoUrl);
//            System.out.println(userInfo);
            //获取返回userinfo字符串扫描人信息
            HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
            String nickname = (String) userInfoMap.get("nickname");
            String headimgurl = (String) userInfoMap.get("headimgurl");

            //把扫描人信息添加到数据库里面
            //判断数据库里面是否存在相同的微信信息
            UcenterMember member = memberService.getOpenIdMember(openid);
            if (member == null){
                member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }
            //使用jwt根据member对象生成token字符串
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            //返回首页面
            return "redirect:http://localhost:3000";

        } catch (Exception e) {
//            e.printStackTrace();
            throw new GuliException(20001,"登陆失败");
        }
    }
    //1.生成微信扫描二维码
    @GetMapping("login")
    public String getWxCode(){

        // 微信开放平台授权baseUrl  %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        //对redirect_url进行urlEncode编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;

        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (Exception e) {
//            e.printStackTrace();
        }
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                ConstantWxUtils.WX_OPEN_REDIRECT_URL,
                "yzaaa"
        );

        //重定向到请求微信地址里面
        return "redirect:" + url;
    }
}
