package com.example.wechat.model;

import lombok.Data;

/**
 * 用户请求参数
 */
@Data
public class UserRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;
}
