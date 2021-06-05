package com.examples.jogijotae;



public class Person {       //리사이클러뷰 사용하기위한 클래스 생성

    double direction; //거리
    String name; //가게이름
    double lati; //위도
    double longi; //경도

    public Person(String name,double direction,double lati,double longi) {
        this.direction = direction;
        this.name = name;
        this.lati =lati;
        this.longi = longi;
    }

    public double getDirection() {
        return direction;
    }

    public void setdirection(double direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }
}
