package cn.yzaaa.vod.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Honglixi
 * @create 2021-04-22-14:24
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.yzaaa"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
