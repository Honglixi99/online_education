package cn.yzaaa.msmservice.service;

import java.util.Map;

/**
 * @author Honglixi
 * @create 2021-05-16-16:05
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
