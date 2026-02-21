package com.example.wechat.dao.impl;

import com.example.wechat.dao.GameDao;
import com.example.wechat.model.Game;
import com.example.wechat.util.DBGameProcessUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {

    private static final String INSERT_SQL = "INSERT INTO game (game_type, start_time, end_time, game_master) VALUES (?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM game WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE game SET game_type = ?, start_time = ?, end_time = ?, game_master = ? WHERE id = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT id, game_type, start_time, end_time, game_master FROM game WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, game_type, start_time, end_time, game_master FROM game";
    private static final String SELECT_BY_GAME_TYPE_SQL = "SELECT id, game_type, start_time, end_time, game_master FROM game WHERE game_type = ?";
    private static final String SELECT_BY_GAME_MASTER_SQL = "SELECT id, game_type, start_time, end_time, game_master FROM game WHERE game_master = ?";

    @Override
    public int insert(Game game) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_SQL);
            stmt.setString(1, game.getGameType());
            stmt.setTimestamp(2, new java.sql.Timestamp(game.getStartTime().getTime()));
            stmt.setTimestamp(3, new java.sql.Timestamp(game.getEndTime().getTime()));
            stmt.setInt(4, game.getGameMaster());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("插入游戏失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt);
        }
    }

    @Override
    public int deleteById(Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_SQL);
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("删除游戏失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt);
        }
    }

    @Override
    public int update(Game game) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_SQL);
            stmt.setString(1, game.getGameType());
            stmt.setTimestamp(2, new java.sql.Timestamp(game.getStartTime().getTime()));
            stmt.setTimestamp(3, new java.sql.Timestamp(game.getEndTime().getTime()));
            stmt.setInt(4, game.getGameMaster());
            stmt.setInt(5, game.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("更新游戏失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt);
        }
    }

    @Override
    public Game selectById(Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Game(
                        rs.getInt("id"),
                        rs.getString("game_type"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getInt("game_master")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("查询游戏失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<Game> selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Game> games = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_ALL_SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("id"),
                        rs.getString("game_type"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getInt("game_master")
                ));
            }
            return games;
        } catch (SQLException e) {
            throw new RuntimeException("查询所有游戏失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<Game> selectByGameType(String gameType) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Game> games = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_GAME_TYPE_SQL);
            stmt.setString(1, gameType);
            rs = stmt.executeQuery();
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("id"),
                        rs.getString("game_type"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getInt("game_master")
                ));
            }
            return games;
        } catch (SQLException e) {
            throw new RuntimeException("根据游戏类型查询游戏失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<Game> selectByGameMaster(String gameMaster) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Game> games = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_GAME_MASTER_SQL);
            stmt.setString(1, gameMaster);
            rs = stmt.executeQuery();
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("id"),
                        rs.getString("game_type"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getInt("game_master")
                ));
            }
            return games;
        } catch (SQLException e) {
            throw new RuntimeException("根据游戏主持人查询游戏失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }
}
