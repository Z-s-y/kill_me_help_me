package com.example.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 微信小程序后端应用主类
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("\n========================================");
        System.out.println("微信小程序后端服务启动成功！");
        System.out.println("访问地址: http://localhost:5000");
        System.out.println("========================================\n");
        123
    }
}
