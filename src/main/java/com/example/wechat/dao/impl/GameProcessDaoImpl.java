package com.example.wechat.dao.impl;

import com.example.wechat.dao.GameProcessDao;
import com.example.wechat.model.GameProcess;
import com.example.wechat.util.DBGameProcessUtil;
import com.example.wechat.util.DBUserInformationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameProcessDaoImpl implements GameProcessDao {

    private static final String INSERT_SQL = "INSERT INTO game_process (game_id, version, stage, game_actor_nums, " +
            "role1_id, role1_action_id, role2_id, role2_action_id, role3_id, role3_action_id, " +
            "role4_id, role4_action_id, role5_id, role5_action_id, role6_id, role6_action_id, " +
            "role7_id, role7_action_id, role8_id, role8_action_id, role9_id, role9_action_id, " +
            "role10_id, role10_action_id, role11_id, role11_action_id, role12_id, role12_action_id, " +
            "role13_id, role13_action_id, role14_id, role14_action_id, role15_id, role15_action_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_SQL = "DELETE FROM game_process WHERE id = ?";

    private static final String UPDATE_SQL = "UPDATE game_process SET game_id = ?, version = ?, stage = ?, game_actor_nums = ?, " +
            "role1_id = ?, role1_action_id = ?, role2_id = ?, role2_action_id = ?, role3_id = ?, role3_action_id = ?, " +
            "role4_id = ?, role4_action_id = ?, role5_id = ?, role5_action_id = ?, role6_id = ?, role6_action_id = ?, " +
            "role7_id = ?, role7_action_id = ?, role8_id = ?, role8_action_id = ?, role9_id = ?, role9_action_id = ?, " +
            "role10_id = ?, role10_action_id = ?, role11_id = ?, role11_action_id = ?, role12_id = ?, role12_action_id = ?, " +
            "role13_id = ?, role13_action_id = ?, role14_id = ?, role14_action_id = ?, role15_id = ?, role15_action_id = ? " +
            "WHERE id = ?";

    private static final String SELECT_BY_ID_SQL = "SELECT id, game_id, version, stage, game_actor_nums, " +
            "role1_id, role1_action_id, role2_id, role2_action_id, role3_id, role3_action_id, " +
            "role4_id, role4_action_id, role5_id, role5_action_id, role6_id, role6_action_id, " +
            "role7_id, role7_action_id, role8_id, role8_action_id, role9_id, role9_action_id, " +
            "role10_id, role10_action_id, role11_id, role11_action_id, role12_id, role12_action_id, " +
            "role13_id, role13_action_id, role14_id, role14_action_id, role15_id, role15_action_id " +
            "FROM game_process WHERE id = ?";

    private static final String SELECT_ALL_SQL = "SELECT id, game_id, version, stage, game_actor_nums, " +
            "role1_id, role1_action_id, role2_id, role2_action_id, role3_id, role3_action_id, " +
            "role4_id, role4_action_id, role5_id, role5_action_id, role6_id, role6_action_id, " +
            "role7_id, role7_action_id, role8_id, role8_action_id, role9_id, role9_action_id, " +
            "role10_id, role10_action_id, role11_id, role11_action_id, role12_id, role12_action_id, " +
            "role13_id, role13_action_id, role14_id, role14_action_id, role15_id, role15_action_id " +
            "FROM game_process";

    private static final String SELECT_BY_GAME_ID_SQL = "SELECT id, game_id, version, stage, game_actor_nums, " +
            "role1_id, role1_action_id, role2_id, role2_action_id, role3_id, role3_action_id, " +
            "role4_id, role4_action_id, role5_id, role5_action_id, role6_id, role6_action_id, " +
            "role7_id, role7_action_id, role8_id, role8_action_id, role9_id, role9_action_id, " +
            "role10_id, role10_action_id, role11_id, role11_action_id, role12_id, role12_action_id, " +
            "role13_id, role13_action_id, role14_id, role14_action_id, role15_id, role15_action_id " +
            "FROM game_process WHERE game_id = ?";

    private static final String SELECT_BY_VERSION_SQL = "SELECT id, game_id, version, stage, game_actor_nums, " +
            "role1_id, role1_action_id, role2_id, role2_action_id, role3_id, role3_action_id, " +
            "role4_id, role4_action_id, role5_id, role5_action_id, role6_id, role6_action_id, " +
            "role7_id, role7_action_id, role8_id, role8_action_id, role9_id, role9_action_id, " +
            "role10_id, role10_action_id, role11_id, role11_action_id, role12_id, role12_action_id, " +
            "role13_id, role13_action_id, role14_id, role14_action_id, role15_id, role15_action_id " +
            "FROM game_process WHERE version = ?";

    private static final String SELECT_BY_STAGE_SQL = "SELECT id, game_id, version, stage, game_actor_nums, " +
            "role1_id, role1_action_id, role2_id, role2_action_id, role3_id, role3_action_id, " +
            "role4_id, role4_action_id, role5_id, role5_action_id, role6_id, role6_action_id, " +
            "role7_id, role7_action_id, role8_id, role8_action_id, role9_id, role9_action_id, " +
            "role10_id, role10_action_id, role11_id, role11_action_id, role12_id, role12_action_id, " +
            "role13_id, role13_action_id, role14_id, role14_action_id, role15_id, role15_action_id " +
            "FROM game_process WHERE stage = ?";

    private static final String SELECT_BY_GAME_ID_AND_VERSION_SQL = "SELECT id, game_id, version, stage, game_actor_nums, " +
            "role1_id, role1_action_id, role2_id, role2_action_id, role3_id, role3_action_id, " +
            "role4_id, role4_action_id, role5_id, role5_action_id, role6_id, role6_action_id, " +
            "role7_id, role7_action_id, role8_id, role8_action_id, role9_id, role9_action_id, " +
            "role10_id, role10_action_id, role11_id, role11_action_id, role12_id, role12_action_id, " +
            "role13_id, role13_action_id, role14_id, role14_action_id, role15_id, role15_action_id " +
            "FROM game_process WHERE game_id = ? AND version = ?";

    @Override
    public int insert(GameProcess gameProcess) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_SQL);
            int index = 1;
            stmt.setInt(index++, gameProcess.getGameId());
            stmt.setInt(index++, gameProcess.getVersion());
            stmt.setString(index++, gameProcess.getStage());
            stmt.setInt(index++, gameProcess.getGameActorNums());
            stmt.setInt(index++, gameProcess.getRole1Id());
            stmt.setInt(index++, gameProcess.getRole1ActionId());
            stmt.setInt(index++, gameProcess.getRole2Id());
            stmt.setInt(index++, gameProcess.getRole2ActionId());
            stmt.setInt(index++, gameProcess.getRole3Id());
            stmt.setInt(index++, gameProcess.getRole3ActionId());
            stmt.setInt(index++, gameProcess.getRole4Id());
            stmt.setInt(index++, gameProcess.getRole4ActionId());
            stmt.setInt(index++, gameProcess.getRole5Id());
            stmt.setInt(index++, gameProcess.getRole5ActionId());
            stmt.setInt(index++, gameProcess.getRole6Id());
            stmt.setInt(index++, gameProcess.getRole6ActionId());
            stmt.setInt(index++, gameProcess.getRole7Id());
            stmt.setInt(index++, gameProcess.getRole7ActionId());
            stmt.setInt(index++, gameProcess.getRole8Id());
            stmt.setInt(index++, gameProcess.getRole8ActionId());
            stmt.setInt(index++, gameProcess.getRole9Id());
            stmt.setInt(index++, gameProcess.getRole9ActionId());
            stmt.setInt(index++, gameProcess.getRole10Id());
            stmt.setInt(index++, gameProcess.getRole10ActionId());
            stmt.setInt(index++, gameProcess.getRole11Id());
            stmt.setInt(index++, gameProcess.getRole11ActionId());
            stmt.setInt(index++, gameProcess.getRole12Id());
            stmt.setInt(index++, gameProcess.getRole12ActionId());
            stmt.setInt(index++, gameProcess.getRole13Id());
            stmt.setInt(index++, gameProcess.getRole13ActionId());
            stmt.setInt(index++, gameProcess.getRole14Id());
            stmt.setInt(index++, gameProcess.getRole14ActionId());
            stmt.setInt(index++, gameProcess.getRole15Id());
            stmt.setInt(index++, gameProcess.getRole15ActionId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("插入游戏进程失败", e);
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
            throw new RuntimeException("删除游戏进程失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt);
        }
    }

    @Override
    public int update(GameProcess gameProcess) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_SQL);
            int index = 1;
            stmt.setInt(index++, gameProcess.getGameId());
            stmt.setInt(index++, gameProcess.getVersion());
            stmt.setString(index++, gameProcess.getStage());
            stmt.setInt(index++, gameProcess.getGameActorNums());
            stmt.setInt(index++, gameProcess.getRole1Id());
            stmt.setInt(index++, gameProcess.getRole1ActionId());
            stmt.setInt(index++, gameProcess.getRole2Id());
            stmt.setInt(index++, gameProcess.getRole2ActionId());
            stmt.setInt(index++, gameProcess.getRole3Id());
            stmt.setInt(index++, gameProcess.getRole3ActionId());
            stmt.setInt(index++, gameProcess.getRole4Id());
            stmt.setInt(index++, gameProcess.getRole4ActionId());
            stmt.setInt(index++, gameProcess.getRole5Id());
            stmt.setInt(index++, gameProcess.getRole5ActionId());
            stmt.setInt(index++, gameProcess.getRole6Id());
            stmt.setInt(index++, gameProcess.getRole6ActionId());
            stmt.setInt(index++, gameProcess.getRole7Id());
            stmt.setInt(index++, gameProcess.getRole7ActionId());
            stmt.setInt(index++, gameProcess.getRole8Id());
            stmt.setInt(index++, gameProcess.getRole8ActionId());
            stmt.setInt(index++, gameProcess.getRole9Id());
            stmt.setInt(index++, gameProcess.getRole9ActionId());
            stmt.setInt(index++, gameProcess.getRole10Id());
            stmt.setInt(index++, gameProcess.getRole10ActionId());
            stmt.setInt(index++, gameProcess.getRole11Id());
            stmt.setInt(index++, gameProcess.getRole11ActionId());
            stmt.setInt(index++, gameProcess.getRole12Id());
            stmt.setInt(index++, gameProcess.getRole12ActionId());
            stmt.setInt(index++, gameProcess.getRole13Id());
            stmt.setInt(index++, gameProcess.getRole13ActionId());
            stmt.setInt(index++, gameProcess.getRole14Id());
            stmt.setInt(index++, gameProcess.getRole14ActionId());
            stmt.setInt(index++, gameProcess.getRole15Id());
            stmt.setInt(index++, gameProcess.getRole15ActionId());
            stmt.setInt(index++, gameProcess.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("更新游戏进程失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt);
        }
    }

    @Override
    public GameProcess selectById(Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToGameProcess(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("查询游戏进程失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<GameProcess> selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<GameProcess> gameProcesses = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_ALL_SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                gameProcesses.add(mapResultSetToGameProcess(rs));
            }
            return gameProcesses;
        } catch (SQLException e) {
            throw new RuntimeException("查询所有游戏进程失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<GameProcess> selectByGameId(Integer gameId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<GameProcess> gameProcesses = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_GAME_ID_SQL);
            stmt.setInt(1, gameId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                gameProcesses.add(mapResultSetToGameProcess(rs));
            }
            return gameProcesses;
        } catch (SQLException e) {
            throw new RuntimeException("根据游戏ID查询游戏进程失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<GameProcess> selectByVersion(Integer version) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<GameProcess> gameProcesses = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_VERSION_SQL);
            stmt.setInt(1, version);
            rs = stmt.executeQuery();
            while (rs.next()) {
                gameProcesses.add(mapResultSetToGameProcess(rs));
            }
            return gameProcesses;
        } catch (SQLException e) {
            throw new RuntimeException("根据版本查询游戏进程失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<GameProcess> selectByStage(String stage) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<GameProcess> gameProcesses = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_STAGE_SQL);
            stmt.setString(1, stage);
            rs = stmt.executeQuery();
            while (rs.next()) {
                gameProcesses.add(mapResultSetToGameProcess(rs));
            }
            return gameProcesses;
        } catch (SQLException e) {
            throw new RuntimeException("根据阶段查询游戏进程失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public GameProcess selectByGameIdAndVersion(Integer gameId, Integer version) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_GAME_ID_AND_VERSION_SQL);
            stmt.setInt(1, gameId);
            stmt.setInt(2, version);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToGameProcess(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("根据游戏ID和版本查询游戏进程失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    private GameProcess mapResultSetToGameProcess(ResultSet rs) throws SQLException {
        GameProcess gameProcess = new GameProcess();
        gameProcess.setId(rs.getInt("id"));
        gameProcess.setGameId(rs.getInt("game_id"));
        gameProcess.setVersion(rs.getInt("version"));
        gameProcess.setStage(rs.getString("stage"));
        gameProcess.setGameActorNums(rs.getInt("game_actor_nums"));
        gameProcess.setRole1Id(rs.getInt("role1_id"));
        gameProcess.setRole1ActionId(rs.getInt("role1_action_id"));
        gameProcess.setRole2Id(rs.getInt("role2_id"));
        gameProcess.setRole2ActionId(rs.getInt("role2_action_id"));
        gameProcess.setRole3Id(rs.getInt("role3_id"));
        gameProcess.setRole3ActionId(rs.getInt("role3_action_id"));
        gameProcess.setRole4Id(rs.getInt("role4_id"));
        gameProcess.setRole4ActionId(rs.getInt("role4_action_id"));
        gameProcess.setRole5Id(rs.getInt("role5_id"));
        gameProcess.setRole5ActionId(rs.getInt("role5_action_id"));
        gameProcess.setRole6Id(rs.getInt("role6_id"));
        gameProcess.setRole6ActionId(rs.getInt("role6_action_id"));
        gameProcess.setRole7Id(rs.getInt("role7_id"));
        gameProcess.setRole7ActionId(rs.getInt("role7_action_id"));
        gameProcess.setRole8Id(rs.getInt("role8_id"));
        gameProcess.setRole8ActionId(rs.getInt("role8_action_id"));
        gameProcess.setRole9Id(rs.getInt("role9_id"));
        gameProcess.setRole9ActionId(rs.getInt("role9_action_id"));
        gameProcess.setRole10Id(rs.getInt("role10_id"));
        gameProcess.setRole10ActionId(rs.getInt("role10_action_id"));
        gameProcess.setRole11Id(rs.getInt("role11_id"));
        gameProcess.setRole11ActionId(rs.getInt("role11_action_id"));
        gameProcess.setRole12Id(rs.getInt("role12_id"));
        gameProcess.setRole12ActionId(rs.getInt("role12_action_id"));
        gameProcess.setRole13Id(rs.getInt("role13_id"));
        gameProcess.setRole13ActionId(rs.getInt("role13_action_id"));
        gameProcess.setRole14Id(rs.getInt("role14_id"));
        gameProcess.setRole14ActionId(rs.getInt("role14_action_id"));
        gameProcess.setRole15Id(rs.getInt("role15_id"));
        gameProcess.setRole15ActionId(rs.getInt("role15_action_id"));
        return gameProcess;
    }
}
