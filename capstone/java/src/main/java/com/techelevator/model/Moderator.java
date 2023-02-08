package com.techelevator.model;

public class Moderator {
    private int moderatorId;
    private int forumId;
    private String username;

    public Moderator() {
    }

    public Moderator(int moderatorId, int forumId) {
        this.moderatorId = moderatorId;
        this.forumId = forumId;
    }

    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
