package com.example.presidentvote;

public class SingerItem {
    String name;
    String cs;
    String gender;
    String num;
    int resId;

    public SingerItem(String name, String cs, String gender, String num, int resId){
        this.name = name;
        this.cs = cs;
        this.gender = gender;
        this.num = num;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCs() {
        return cs;
    }
    public void setCs(String name) {
        this.cs = cs;
    }
    public String getGender() {
        return gender;
    }
    public void setGender() {
        this.gender = gender;
    }
    public String getNum() {
        return num;
    }
    public void setNum(String name) {
        this.num = num;
    }
    public int getResId() {
        return resId;
    }
    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", cs='" + cs + '\'' +
                '}';
    }
}
