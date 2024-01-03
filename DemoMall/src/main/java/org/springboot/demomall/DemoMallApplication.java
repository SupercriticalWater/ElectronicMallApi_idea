package org.springboot.demomall;

import org.springboot.demomall.utils.PathUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("org.springboot.demomall.mapper")
@SpringBootApplication
public class DemoMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMallApplication.class, args);
    }

}
