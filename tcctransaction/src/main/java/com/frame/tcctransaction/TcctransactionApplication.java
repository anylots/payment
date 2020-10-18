package com.frame.tcctransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TcctransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcctransactionApplication.class, args);
    }

}
