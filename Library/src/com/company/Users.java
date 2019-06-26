package com.company;


public class Users {
    private String sName;
    private String sLastName;
    private String sPosition;


    public Users(String sName, String sLastName, String sPosition) {
        setsName(sName);
        setsLastName(sLastName);
        setsPosition(sPosition);
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsLastName() {
        return sLastName;
    }

    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public String getsPosition() {
        return sPosition;
    }

    public void setsPosition(String sPosition) {
        this.sPosition = sPosition;
    }
}