package com.rocketmail.vaishnavanil.hackcovit.enums;

public enum State {
    DELHI(1,"Delhi");
    private int id;
    private String dbVal;
    State(int id, String name){
        this.id = id;
        dbVal = name;
    }

    public String getDBVal() {
        return dbVal;
    }
}
