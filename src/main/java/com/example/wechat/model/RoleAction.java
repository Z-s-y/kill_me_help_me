package com.example.wechat.model;

import com.example.wechat.model.Game;

public class RoleAction {
    private Integer id;
    private Integer gameId;
    private Integer roleId;
    private String skillOperation;
    private Integer skillActRoleId;

    public RoleAction() {
    }

    public RoleAction(Integer id, Integer gameId, Integer roleId, String skillOperation, Integer skillActRoleId) {
        this.id = id;
        this.gameId = gameId;
        this.roleId = roleId;
        this.skillOperation = skillOperation;
        this.skillActRoleId = skillActRoleId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getSkillOperation() {
        return skillOperation;
    }

    public void setSkillOperation(String skillOperation) {
        this.skillOperation = skillOperation;
    }

    public Integer getSkillActRoleId() {
        return skillActRoleId;
    }

    public void setSkillActRoleId(Integer skillActRoleId) {
        this.skillActRoleId = skillActRoleId;
    }

    @Override
    public String toString() {
        return "RoleAction{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", roleId=" + roleId +
                ", skillOperation='" + skillOperation + '\'' +
                ", skillActRoleId=" + skillActRoleId +
                '}';
    }
}
