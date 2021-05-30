package com.examples.jogijotae;



public class Memberinfo {
    private String birthyear;
    private String phone;
    private String gender;
    private String name;


    public Memberinfo(String birthyear, String phone, String gender, String name){
        this.birthyear = birthyear;

        this.phone = phone;
        this.gender = gender;
        this.name = name;
    }
    public String getbirthyear(){
        return this.birthyear;

    }
    public void setbirthyear(String birthyear){
        this.birthyear = birthyear;
    }



    public String getphone(){
        return this.phone;

    }
    public void setphone(String phone){
        this.phone = phone;
    }


    public String getgender(){
        return this.gender;

    }
    public void setgender(String gender){
        this.gender = gender;
    }



    public String getname(){
        return this.name;

    }
    public void setname(String name){
        this.name = name;
    }















/*
    public String getemail(){
        return this.email;

    }
    public void setemail(String email){
        this.email = email;
    }

 */
}
