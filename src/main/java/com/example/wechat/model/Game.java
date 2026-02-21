package com.example.wechat.model;

import java.util.Date;

public class Game {
    private Integer id;
    private String gameType;
    private Date startTime;
    private Date endTime;
    private Integer gameMaster;

    public Game() {
    }

    public Game(Integer id, String gameType, Date startTime, Date endTime, Integer gameMaster) {
        this.id = id;
        this.gameType = gameType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.gameMaster = gameMaster;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getGameMaster() {
        return gameMaster;
    }

    public void setGameMaster(Integer gameMaster) {
        this.gameMaster = gameMaster;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameType='" + gameType + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", gameMaster='" + gameMaster + '\'' +
                '}';
    }
}
