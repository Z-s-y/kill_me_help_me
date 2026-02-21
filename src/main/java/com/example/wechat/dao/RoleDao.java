package com.example.wechat.dao;


import com.example.wechat.model.Role;
import java.util.List;

public interface RoleDao {
    /**
     * 插入角色
     * @param role 角色对象
     * @return 影响的行数
     */
    int insert(Role role);

    /**
     * 根据ID删除角色
     * @param id 角色ID
     * @return 影响的行数
     */
    int deleteById(Integer id);

    /**
     * 更新角色信息
     * @param role 角色对象
     * @return 影响的行数
     */
    int update(Role role);

    /**
     * 根据ID查询角色
     * @param id 角色ID
     * @return 角色对象
     */
    Role selectById(Integer id);

    /**
     * 查询所有角色
     * @return 角色列表
     */
    List<Role> selectAll();

    /**
     * 根据游戏ID查询角色
     * @param gameId 游戏ID
     * @return 角色列表
     */
    List<Role> selectByGameId(Integer gameId);

    /**
     * 根据用户ID查询角色
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> selectByUserId(Integer userId);

    /**
     * 根据角色名称查询角色
     * @param roleName 角色名称
     * @return 角色列表
     */
    List<Role> selectByRoleName(String roleName);

    /**
     * 根据角色状态查询角色
     * @param roleStatus 角色状态
     * @return 角色列表
     */
    List<Role> selectByRoleStatus(String roleStatus);

    /**
     * 根据结算阵营查询角色
     * @param settleFaction 结算阵营
     * @return 角色列表
     */
    List<Role> selectBySettleFaction(String settleFaction);

    /**
     * 根据游戏ID和用户ID查询角色
     * @param gameId 游戏ID
     * @param userId 用户ID
     * @return 角色对象
     */
    Role selectByGameIdAndUserId(Integer gameId, Integer userId);
}
