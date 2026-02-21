package com.example.wechat.dao.impl;

import com.example.wechat.dao.RoleActionDao;
import com.example.wechat.model.RoleAction;
import com.example.wechat.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleActionDaoImpl implements RoleActionDao {

    private static final String INSERT_SQL = "INSERT INTO role_action (game_id, role_id, skill_operation, skill_act_role_id) VALUES (?, ?, ?, ?)";

    private static final String DELETE_SQL = "DELETE FROM role_action WHERE id = ?";

    private static final String UPDATE_SQL = "UPDATE role_action SET game_id = ?, role_id = ?, skill_operation = ?, skill_act_role_id = ? WHERE id = ?";

    private static final String SELECT_BY_ID_SQL = "SELECT id, game_id, role_id, skill_operation, skill_act_role_id FROM role_action WHERE id = ?";

    private static final String SELECT_ALL_SQL = "SELECT id, game_id, role_id, skill_operation, skill_act_role_id FROM role_action";

    private static final String SELECT_BY_GAME_ID_SQL = "SELECT id, game_id, role_id, skill_operation, skill_act_role_id FROM role_action WHERE game_id = ?";

    private static final String SELECT_BY_ROLE_ID_SQL = "SELECT id, game_id, role_id, skill_operation, skill_act_role_id FROM role_action WHERE role_id = ?";

    private static final String SELECT_BY_SKILL_OPERATION_SQL = "SELECT id, game_id, role_id, skill_operation, skill_act_role_id FROM role_action WHERE skill_operation = ?";

    private static final String SELECT_BY_SKILL_ACT_ROLE_ID_SQL = "SELECT id, game_id, role_id, skill_operation, skill_act_role_id FROM role_action WHERE skill_act_role_id = ?";

    private static final String SELECT_BY_GAME_ID_AND_ROLE_ID_SQL = "SELECT id, game_id, role_id, skill_operation, skill_act_role_id FROM role_action WHERE game_id = ? AND role_id = ?";

    @Override
    public int insert(RoleAction roleAction) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_SQL);
            int index = 1;
            stmt.setInt(index++, roleAction.getGameId());
            stmt.setInt(index++, roleAction.getRoleId());
            stmt.setString(index++, roleAction.getSkillOperation());
            stmt.setInt(index++, roleAction.getSkillActRoleId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("插入角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    @Override
    public int deleteById(Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_SQL);
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("删除角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    @Override
    public int update(RoleAction roleAction) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_SQL);
            int index = 1;
            stmt.setInt(index++, roleAction.getGameId());
            stmt.setInt(index++, roleAction.getRoleId());
            stmt.setString(index++, roleAction.getSkillOperation());
            stmt.setInt(index++, roleAction.getSkillActRoleId());
            stmt.setInt(index++, roleAction.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("更新角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    @Override
    public RoleAction selectById(Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToRoleAction(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("查询角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<RoleAction> selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RoleAction> roleActions = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_ALL_SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roleActions.add(mapResultSetToRoleAction(rs));
            }
            return roleActions;
        } catch (SQLException e) {
            throw new RuntimeException("查询所有角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<RoleAction> selectByGameId(Integer gameId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RoleAction> roleActions = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_GAME_ID_SQL);
            stmt.setInt(1, gameId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roleActions.add(mapResultSetToRoleAction(rs));
            }
            return roleActions;
        } catch (SQLException e) {
            throw new RuntimeException("根据游戏ID查询角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<RoleAction> selectByRoleId(Integer roleId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RoleAction> roleActions = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ROLE_ID_SQL);
            stmt.setInt(1, roleId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roleActions.add(mapResultSetToRoleAction(rs));
            }
            return roleActions;
        } catch (SQLException e) {
            throw new RuntimeException("根据角色ID查询角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<RoleAction> selectBySkillOperation(String skillOperation) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RoleAction> roleActions = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_SKILL_OPERATION_SQL);
            stmt.setString(1, skillOperation);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roleActions.add(mapResultSetToRoleAction(rs));
            }
            return roleActions;
        } catch (SQLException e) {
            throw new RuntimeException("根据技能操作查询角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<RoleAction> selectBySkillActRoleId(Integer skillActRoleId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RoleAction> roleActions = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_SKILL_ACT_ROLE_ID_SQL);
            stmt.setInt(1, skillActRoleId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roleActions.add(mapResultSetToRoleAction(rs));
            }
            return roleActions;
        } catch (SQLException e) {
            throw new RuntimeException("根据技能作用角色ID查询角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    @Override
    public List<RoleAction> selectByGameIdAndRoleId(Integer gameId, Integer roleId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RoleAction> roleActions = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_GAME_ID_AND_ROLE_ID_SQL);
            stmt.setInt(1, gameId);
            stmt.setInt(2, roleId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                roleActions.add(mapResultSetToRoleAction(rs));
            }
            return roleActions;
        } catch (SQLException e) {
            throw new RuntimeException("根据游戏ID和角色ID查询角色动作失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    private RoleAction mapResultSetToRoleAction(ResultSet rs) throws SQLException {
        RoleAction roleAction = new RoleAction();
        roleAction.setId(rs.getInt("id"));
        roleAction.setGameId(rs.getInt("game_id"));
        roleAction.setRoleId(rs.getInt("role_id"));
        roleAction.setSkillOperation(rs.getString("skill_operation"));
        roleAction.setSkillActRoleId(rs.getInt("skill_act_role_id"));
        return roleAction;
    }
}
