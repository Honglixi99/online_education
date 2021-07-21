package cn.yzaaa.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Honglixi
 * @create 2021-07-21 21:03
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.yzaaa"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("cn.yzaaa.staservice.mapper")
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class,args);
    }
}
