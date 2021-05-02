package cn.yzaaa.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @author Honglixi
 * @create 2021-05-02-21:45
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tEZaBtMAXRvzV74efrg","LhHjTAweHVD8qhbC1DPVSocEsUY9jZ");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        //向request对象里面设置视频id
        request.setVideoId("8ce475077d7b4e70a58dcbf637dade09");
        //调用初始化对象的方法得到凭证
        response = client.getAcsResponse(request);
        System.out.println("playauth:"+response.getPlayAuth());
    }
    public static void getPlayUrl() throws Exception {
        //1.根据视频id获取视频播放地址
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tEZaBtMAXRvzV74efrg","LhHjTAweHVD8qhbC1DPVSocEsUY9jZ");

        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request对象里面设置视频id
        request.setVideoId("8ce475077d7b4e70a58dcbf637dade09");
        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.println("PlayInfo.PlayURL=" + playInfo.getPlayURL() + "\n");
        }
    }
}
