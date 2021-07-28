package cn.yzaaa.canal;

import cn.yzaaa.canal.client.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author Honglixi
 * @create 2021-07-28 10:11
 */
@SpringBootApplication
public class CanalApplication implements CommandLineRunner {

    @Resource
    private CanalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }

    /**
     * Spring Boot 启动后，立马可以执行的方法 (implements CommandLineRunner)
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        canalClient.run();
    }
}
