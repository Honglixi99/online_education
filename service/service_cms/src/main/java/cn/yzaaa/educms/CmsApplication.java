package cn.yzaaa.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Honglixi
 * @create 2021-05-14-14:46
 */
@SpringBootApplication
@ComponentScan({"cn.yzaaa"})//指定扫描位置
@MapperScan("cn.yzaaa.educms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
