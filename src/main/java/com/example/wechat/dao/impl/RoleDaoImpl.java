package com.example.wechat.dao.impl;


import com.example.wechat.dao.RoleDao;
import com.example.wechat.model.Role;
import com.example.wechat.util.DBGameProcessUtil;
import com.example.wechat.util.DBUserInformationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private static final String INSERT_SQL = "INSERT INTO role (game_id, user_id, role_name, role_status, role_skill_status, settle_faction) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String DELETE_SQL = "DELETE FROM role WHERE id = ?";

    private static final String UPDATE_SQL = "UPDATE role SET game_id = ?, user_id = ?, role_name = ?, role_status = ?, role_skill_status = ?, settle_faction = ? WHERE id = ?";

    private static final String SELECT_BY_ID_SQL = "SELECT id, game_id, user_id, role_name, role_status, role_skill_status, settle_faction FROM role WHERE id = ?";

    private static final String SELECT_ALL_SQL = "SELECT id, game_id, user_id, role_name, role_status, role_skill_status, settle_faction FROM role";

    private static final String SELECT_BY_GAME_ID_SQL = "SELECT id, game_id, user_id, role_name, role_status, role_skill_status, settle_faction FROM role WHERE game_id = ?";

    private static final String SELECT_BY_USER_ID_SQL = "SELECT id, game_id, user_id, role_name, role_status, role_skill_status, settle_faction FROM role WHERE user_id = ?";

    private static final String SELECT_BY_ROLE_NAME_SQL = "SELECT id, game_id, user_id, role_name, role_status, role_skill_status, settle_faction FROM role WHERE role_name = ?";

    private static final String SELECT_BY_ROLE_STATUS_SQL = "SELECT id, game_id, user_id, role_name, role_status, role_skill_status, settle_faction FROM role WHERE role_status = ?";

    private static final String SELECT_BY_SETTLE_FACTION_SQL = "SELECT id, game_id, user_id, role_name, role_status, role_skill_status, settle_faction FROM role WHERE settle_faction = ?";

    private static final String SELECT_BY_GAME_ID_AND_USER_ID_SQL = "SELECT id, game_id, user_id, role_name, role_status, role_skill_status, settle_faction FROM role WHERE game_id = ? AND user_id = ?";

    @Override
    public int insert(Role role) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_SQL);
            int index = 1;
            stmt.setInt(index++, role.getGameId());
            stmt.setInt(index++, role.getUserId());
            stmt.setString(index++, role.getRoleName());
            stmt.setString(index++, role.getRoleStatus());
            stmt.setString(index++, role.getRoleSkillStatus());
            stmt.setString(index++, role.getSettleFaction());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("插入角色失败", e);
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
            throw new RuntimeException("删除角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt);
        }
    }

    @Override
    public int update(Role role) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_SQL);
            int index = 1;
            stmt.setInt(index++, role.getGameId());
            stmt.setInt(index++, role.getUserId());
            stmt.setString(index++, role.getRoleName());
            stmt.setString(index++, role.getRoleStatus());
            stmt.setString(index++, role.getRoleSkillStatus());
            stmt.setString(index++, role.getSettleFaction());
            stmt.setInt(index++, role.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("更新角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt);
        }
    }

    @Override
    public Role selectById(Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToRole(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("查询角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<Role> selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Role> roles = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_ALL_SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roles.add(mapResultSetToRole(rs));
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException("查询所有角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<Role> selectByGameId(Integer gameId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Role> roles = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_GAME_ID_SQL);
            stmt.setInt(1, gameId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roles.add(mapResultSetToRole(rs));
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException("根据游戏ID查询角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<Role> selectByUserId(Integer userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Role> roles = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_USER_ID_SQL);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roles.add(mapResultSetToRole(rs));
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException("根据用户ID查询角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<Role> selectByRoleName(String roleName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Role> roles = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ROLE_NAME_SQL);
            stmt.setString(1, roleName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roles.add(mapResultSetToRole(rs));
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException("根据角色名称查询角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<Role> selectByRoleStatus(String roleStatus) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Role> roles = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ROLE_STATUS_SQL);
            stmt.setString(1, roleStatus);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roles.add(mapResultSetToRole(rs));
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException("根据角色状态查询角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<Role> selectBySettleFaction(String settleFaction) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Role> roles = new ArrayList<>();
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_SETTLE_FACTION_SQL);
            stmt.setString(1, settleFaction);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roles.add(mapResultSetToRole(rs));
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException("根据结算阵营查询角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public Role selectByGameIdAndUserId(Integer gameId, Integer userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBGameProcessUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_GAME_ID_AND_USER_ID_SQL);
            stmt.setInt(1, gameId);
            stmt.setInt(2, userId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToRole(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("根据游戏ID和用户ID查询角色失败", e);
        } finally {
            DBGameProcessUtil.close(conn, stmt, rs);
        }
    }

    private Role mapResultSetToRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setGameId(rs.getInt("game_id"));
        role.setUserId(rs.getInt("user_id"));
        role.setRoleName(rs.getString("role_name"));
        role.setRoleStatus(rs.getString("role_status"));
        role.setRoleSkillStatus(rs.getString("role_skill_status"));
        role.setSettleFaction(rs.getString("settle_faction"));
        return role;
    }
}
