package com.example.wechat.model;

public class Role {
    private Integer id;
    private Integer gameId;
    private Integer userId;
    private String roleName;
    private String roleStatus;
    private String roleSkillStatus;
    private String settleFaction;

    public Role() {
    }

    public Role(Integer id, Integer gameId, Integer userId, String roleName, String roleStatus, String roleSkillStatus, String settleFaction) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
        this.roleName = roleName;
        this.roleStatus = roleStatus;
        this.roleSkillStatus = roleSkillStatus;
        this.settleFaction = settleFaction;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getRoleSkillStatus() {
        return roleSkillStatus;
    }

    public void setRoleSkillStatus(String roleSkillStatus) {
        this.roleSkillStatus = roleSkillStatus;
    }

    public String getSettleFaction() {
        return settleFaction;
    }

    public void setSettleFaction(String settleFaction) {
        this.settleFaction = settleFaction;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", userId=" + userId +
                ", roleName='" + roleName + '\'' +
                ", roleStatus='" + roleStatus + '\'' +
                ", roleSkillStatus='" + roleSkillStatus + '\'' +
                ", settleFaction='" + settleFaction + '\'' +
                '}';
    }
}
