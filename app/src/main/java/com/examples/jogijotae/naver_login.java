package com.examples.jogijotae;

public class naver_login {
    String name;
    String email;
    String gender;
    String birthyear;
    String mobile;

    public naver_login() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public naver_login(String name, String email, String gender, String birthyear, String mobile) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthyear = birthyear;
        this.mobile = mobile;
    }

}