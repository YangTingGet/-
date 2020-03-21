package com.yt.bishe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.yt.bishe.dao")
public class BisheApplication {

    public static void main(String[] args){
        SpringApplication.run(BisheApplication.class,args);
    }

}
