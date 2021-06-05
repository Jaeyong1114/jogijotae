package com.examples.jogijotae;

import java.util.Comparator;

public class POJOclass implements Comparable<POJOclass> {

        private String name;  // 가게이름
        private String lati; // 위도
        private String longi; // 경도
        private int distance; // 거리
        private String position; //포지션


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLat() {
        return lati;
    }

    public void setLat(String lati) {
        this.lati = lati;
    }


    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }




    public POJOclass(String name,String lati,String longi,int distance, String position ) {
        this.name = name;
        this.lati=lati;
        this.longi=longi;
        this.distance=distance;
        this.position=position;

    }

    @Override
    public int compareTo(POJOclass anotherPOJOclass) {
        return Integer.compare(distance, anotherPOJOclass.distance);
    }

/*
    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
 */
}
