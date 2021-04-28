package cn.yzaaa.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Honglixi
 * @create 2021-04-28-9:01
 */
public interface OssService {


    String uploadFileAvatar(MultipartFile file);
}
