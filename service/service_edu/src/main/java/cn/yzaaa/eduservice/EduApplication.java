package cn.yzaaa.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Honglixi
 * @create 2021-04-22-14:24
 */
@SpringBootApplication
@EnableDiscoveryClient //nacos注册
@EnableFeignClients
@ComponentScan(basePackages = {"cn.yzaaa"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
