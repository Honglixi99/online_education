package cn.yzaaa.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Honglixi
 * @create 2021-05-20-14:13
 */
@ComponentScan({"cn.yzaaa"})
@SpringBootApplication
@MapperScan("cn.yzaaa.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
