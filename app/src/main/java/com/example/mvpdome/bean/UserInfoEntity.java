package com.example.mvpdome.bean;

public class UserInfoEntity {

    public String name;
    public String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public UserInfoEntity(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
