package com.yiyi.translater.model;

public class Collect {
    private int id;
    private int userid;
    private String time;
    private String cotent;
    private String yuan;

    public Collect() {
    }

    public Collect(int userid, String time, String cotent, String yuan) {
        this.userid = userid;
        this.time = time;
        this.cotent = cotent;
        this.yuan = yuan;
    }

    public Collect(int id, int userid, String time, String cotent, String yuan) {
        this.id = id;
        this.userid = userid;
        this.time = time;
        this.cotent = cotent;
        this.yuan = yuan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public String getYuan() {
        return yuan;
    }

    public void setYuan(String yuan) {
        this.yuan = yuan;
    }
}
