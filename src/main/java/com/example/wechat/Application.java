package com.example.wechat;

import com.example.wechat.dao.GameDao;
import com.example.wechat.dao.UserDao;
import com.example.wechat.dao.impl.GameDaoImpl;
import com.example.wechat.dao.impl.UserDaoImpl;
import com.example.wechat.model.Game;
import com.example.wechat.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

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

        Date date = new Date("2028-01-01 00:00:00");
        Date date2 = new Date("2028-01-02 00:00:00");

        Game game = new Game(1, "type", date, date2, "master");
        GameDao gameDao = new GameDaoImpl();
        gameDao.insert(game);
    }
}
