package cn.yzaaa.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Honglixi
 * @create 2021-05-03-9:22
 */
public interface VodService {

    String uploadVideoAly(MultipartFile file);
}
