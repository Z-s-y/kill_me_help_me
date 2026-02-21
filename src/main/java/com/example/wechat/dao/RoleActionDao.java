package com.example.wechat.dao;

import com.example.wechat.model.RoleAction;

import java.util.List;

public interface RoleActionDao {
    /**
     * 插入角色动作
     * @param roleAction 角色动作对象
     * @return 影响的行数
     */
    int insert(RoleAction roleAction);

    /**
     * 根据ID删除角色动作
     * @param id 角色动作ID
     * @return 影响的行数
     */
    int deleteById(Integer id);

    /**
     * 更新角色动作信息
     * @param roleAction 角色动作对象
     * @return 影响的行数
     */
    int update(RoleAction roleAction);

    /**
     * 根据ID查询角色动作
     * @param id 角色动作ID
     * @return 角色动作对象
     */
    RoleAction selectById(Integer id);

    /**
     * 查询所有角色动作
     * @return 角色动作列表
     */
    List<RoleAction> selectAll();

    /**
     * 根据游戏ID查询角色动作
     * @param gameId 游戏ID
     * @return 角色动作列表
     */
    List<RoleAction> selectByGameId(Integer gameId);

    /**
     * 根据角色ID查询角色动作
     * @param roleId 角色ID
     * @return 角色动作列表
     */
    List<RoleAction> selectByRoleId(Integer roleId);

    /**
     * 根据技能操作查询角色动作
     * @param skillOperation 技能操作
     * @return 角色动作列表
     */
    List<RoleAction> selectBySkillOperation(String skillOperation);

    /**
     * 根据技能作用角色ID查询角色动作
     * @param skillActRoleId 技能作用角色ID
     * @return 角色动作列表
     */
    List<RoleAction> selectBySkillActRoleId(Integer skillActRoleId);

    /**
     * 根据游戏ID和角色ID查询角色动作
     * @param gameId 游戏ID
     * @param roleId 角色ID
     * @return 角色动作列表
     */
    List<RoleAction> selectByGameIdAndRoleId(Integer gameId, Integer roleId);
}
