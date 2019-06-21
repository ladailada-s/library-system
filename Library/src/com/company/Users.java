package com.company;

public class Users {
    private String userName;
    private String userPosition;

    public Users(String userName,String userPosition) {
        setUserName(userName);
        setUserName(userPosition);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }
}
