package cn.yzaaa.eduservice.client;

import cn.yzaaa.vod.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Honglixi
 * @create 2021-05-12-10:42
 */
@Component
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    //定义调用的方法路径
    //根据视频id删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);

    //定义调用删除多个视频的方法
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList")List<String> videoIdList);
}
