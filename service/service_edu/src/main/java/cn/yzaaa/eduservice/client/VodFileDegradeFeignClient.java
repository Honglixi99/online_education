package cn.yzaaa.eduservice.client;

import cn.yzaaa.vod.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/** 熔断器
 * @author Honglixi
 * @create 2021-05-13-17:34
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    //出错之后执行
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除失败");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除失败");
    }
}
