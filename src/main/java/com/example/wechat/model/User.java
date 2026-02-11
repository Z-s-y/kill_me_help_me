package com.example.wechat.model;

public class User {
    private String id;
    private String name;
    private String profilePicturePath;

    public User() {
    }

    public User(String id, String name, String profilePicturePath) {
        this.id = id;
        this.name = name;
        this.profilePicturePath = profilePicturePath;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profilePicturePath='" + profilePicturePath + '\'' +
                '}';
    }
}
