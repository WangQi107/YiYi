package com.yiyi.translater.model;

public class Info {
    private int id;
    private String userid;
    private String sex;
    private String age;
    private String email;
    private String phone;

    public Info() {
    }

    public Info(String userid) {
        this.userid = userid;
    }

    public Info(String userid, String sex, String age, String email, String phone) {
        this.userid = userid;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public Info(int id, String userid, String sex, String age, String email, String phone) {
        this.id = id;
        this.userid = userid;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
