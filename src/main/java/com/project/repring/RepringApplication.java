package com.project.repring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RepringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepringApplication.class, args);
    }

}
