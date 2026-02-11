package com.example.wechat.dao.impl;



import com.example.wechat.dao.UserDao;
import com.example.wechat.model.User;
import com.example.wechat.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String INSERT_SQL = "INSERT INTO user (id, name, profile_picture_path) VALUES (?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM user WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE user SET name = ?, profile_picture_path = ? WHERE id = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT id, name, profile_picture_path FROM user WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, name, profile_picture_path FROM user";

    @Override
    public int insert(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_SQL);
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getProfilePicturePath());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("插入用户失败", e);
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    @Override
    public int deleteById(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_SQL);
            stmt.setString(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("删除用户失败", e);
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    @Override
    public int update(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_SQL);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getProfilePicturePath());
            stmt.setString(3, user.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("更新用户失败", e);
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    @Override
    public User selectById(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("profile_picture_path")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("查询用户失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<User> selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_ALL_SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("profile_picture_path")
                ));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("查询所有用户失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }
}
