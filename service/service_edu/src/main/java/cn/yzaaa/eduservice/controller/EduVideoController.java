package cn.yzaaa.eduservice.controller;


import cn.yzaaa.eduservice.client.VodClient;
import cn.yzaaa.vod.commonutils.R;
import cn.yzaaa.eduservice.entity.EduVideo;
import cn.yzaaa.eduservice.service.EduVideoService;
import cn.yzaaa.vod.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-29
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    // 删除视频
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id，调用方法实现视频id
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        //判断小节里是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)){
            //根据视频id，远程调用实现视频删除
            R result = vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode() == 20001){
                throw new GuliException(20001,"删除视频失败，熔断器");
            }

        }

        videoService.removeById(id);
        return R.ok();
    }

    //修改小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        boolean update = videoService.updateById(eduVideo);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }


}

