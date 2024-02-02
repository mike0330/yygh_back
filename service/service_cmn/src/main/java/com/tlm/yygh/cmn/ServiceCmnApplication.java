package com.tlm.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author:TLM
 * Date:2024/2/2
 * Time:14:22
 * Description:cmn启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.tlm")
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class,args);
    }
}
