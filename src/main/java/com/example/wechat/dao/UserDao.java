package com.example.wechat.dao;

import com.example.wechat.model.User;
import java.util.List;

public interface UserDao {
    /**
     * 插入用户
     * @param user 用户对象
     * @return 影响的行数
     */
    int insert(User user);

    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return 影响的行数
     */
    int deleteById(String id);

    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 影响的行数
     */
    int update(User user);

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    User selectById(String id);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> selectAll();
}
