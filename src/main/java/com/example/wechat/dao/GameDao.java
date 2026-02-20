package com.example.wechat.dao;

import com.example.wechat.model.Game;
import java.util.List;

public interface GameDao {
    /**
     * 插入游戏
     * @param game 游戏对象
     * @return 影响的行数
     */
    int insert(Game game);

    /**
     * 根据ID删除游戏
     * @param id 游戏ID
     * @return 影响的行数
     */
    int deleteById(Integer id);

    /**
     * 更新游戏信息
     * @param game 游戏对象
     * @return 影响的行数
     */
    int update(Game game);

    /**
     * 根据ID查询游戏
     * @param id 游戏ID
     * @return 游戏对象
     */
    Game selectById(Integer id);

    /**
     * 查询所有游戏
     * @return 游戏列表
     */
    List<Game> selectAll();

    /**
     * 根据游戏类型查询游戏
     * @param gameType 游戏类型
     * @return 游戏列表
     */
    List<Game> selectByGameType(String gameType);

    /**
     * 根据游戏主持人查询游戏
     * @param gameMaster 游戏主持人
     * @return 游戏列表
     */
    List<Game> selectByGameMaster(String gameMaster);
}
