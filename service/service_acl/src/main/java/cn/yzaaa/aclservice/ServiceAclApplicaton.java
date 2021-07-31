package cn.yzaaa.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Honglixi
 * @create 2021-07-28 15:38
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cn.yzaaa")
@MapperScan("cn.yzaaa.aclservice.mapper")
public class ServiceAclApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplicaton.class,args);
    }
}
