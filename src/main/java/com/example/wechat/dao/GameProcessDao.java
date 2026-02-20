package com.example.wechat.dao;

import com.example.wechat.model.GameProcess;

import java.util.List;

public interface GameProcessDao {
    /**
     * 插入游戏进程
     * @param gameProcess 游戏进程对象
     * @return 影响的行数
     */
    int insert(GameProcess gameProcess);

    /**
     * 根据ID删除游戏进程
     * @param id 游戏进程ID
     * @return 影响的行数
     */
    int deleteById(Integer id);

    /**
     * 更新游戏进程信息
     * @param gameProcess 游戏进程对象
     * @return 影响的行数
     */
    int update(GameProcess gameProcess);

    /**
     * 根据ID查询游戏进程
     * @param id 游戏进程ID
     * @return 游戏进程对象
     */
    GameProcess selectById(Integer id);

    /**
     * 查询所有游戏进程
     * @return 游戏进程列表
     */
    List<GameProcess> selectAll();

    /**
     * 根据游戏ID查询游戏进程
     * @param gameId 游戏ID
     * @return 游戏进程列表
     */
    List<GameProcess> selectByGameId(Integer gameId);

    /**
     * 根据版本查询游戏进程
     * @param version 版本
     * @return 游戏进程列表
     */
    List<GameProcess> selectByVersion(Integer version);

    /**
     * 根据阶段查询游戏进程
     * @param stage 阶段
     * @return 游戏进程列表
     */
    List<GameProcess> selectByStage(String stage);

    /**
     * 根据游戏ID和版本查询游戏进程
     * @param gameId 游戏ID
     * @param version 版本
     * @return 游戏进程对象
     */
    GameProcess selectByGameIdAndVersion(Integer gameId, Integer version);
}
