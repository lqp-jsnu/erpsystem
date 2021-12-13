package edu.yctc.erpsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author lqp
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("edu.yctc.erpsystem.*")
public class ErpsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpsystemApplication.class, args);
    }

}
