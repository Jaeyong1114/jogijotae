package com.examples.jogijotae;

public class Interest {
    String place01, place02, place03, place04;
    String restarant01, restarant02,restarant03, restarant04;
    String name;
    String gender;
    String birthyear;
    String mobile;

    public Interest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getplace01() {
        return place01;
    }

    public void setplace01(String place01) {
        this.place01 = place01;
    }

    public String getplace02() {
        return place02;
    }

    public void setplace02(String place02) {
        this.place02 = place02;
    }

    public String getplace03() {
        return place03;
    }

    public void setplace03(String place03) {
        this.place03 = place03;
    }

    public String getplace04() {
        return place04;
    }

    public void setplace04(String place04) {
        this.place04 = place04;
    }

    public String getrestarant01() {
        return restarant01;
    }

    public void setrestarant01(String restarant01) {
        this.restarant01 = restarant01;
    }

    public String getrestarant02() {
        return restarant02;
    }

    public void setrestarant02(String restarant02) {
        this.restarant02 = restarant02;
    }

    public String getrestarant03() {
        return restarant03;
    }

    public void setrestarant03(String restarant03) {
        this.restarant03 = restarant03;
    }

    public String getrestarant04() {
        return restarant04;
    }

    public void setrestarant04(String restarant04) {
        this.restarant04 = restarant04;
    }

    public Interest(String place01, String place02, String place03, String place04, String restarant01, String restarant02, String restarant03, String restarant04,
                    String name,  String gender, String birthyear, String mobile ) {
        this.place01 = place01;
        this.place02 = place02;
        this.place03 = place03;
        this.place04 = place04;
        this.restarant01 = restarant01;
        this.restarant02 = restarant02;
        this.restarant03 = restarant03;
        this.restarant04 = restarant04;
        this.name = name;
        this.gender = gender;
        this.birthyear = birthyear;
        this.mobile = mobile;
    }

}
