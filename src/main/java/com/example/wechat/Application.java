package com.example.wechat;

import com.example.wechat.dao.GameDao;
import com.example.wechat.dao.UserDao;
import com.example.wechat.dao.impl.GameDaoImpl;
import com.example.wechat.dao.impl.UserDaoImpl;
import com.example.wechat.model.Game;
import com.example.wechat.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

        LocalDateTime localDateTime = LocalDateTime.of(2026, 2, 21, 23, 15, 0);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime localDateTime2 = LocalDateTime.of(2026, 2, 21, 23, 45, 0);
        Date date2 = Date.from(localDateTime2.atZone(ZoneId.systemDefault()).toInstant());


        Game game = new Game(1, "type", date, date2, 1);
        GameDao gameDao = new GameDaoImpl();
        gameDao.insert(game);
    }
}
