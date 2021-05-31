package com.examples.jogijotae;



public class Memberinfo {
    private String birthyear;
    private String mobile;
    private String gender;
    private String name;


    public Memberinfo(String birthyear, String mobile, String gender, String name){
        this.birthyear = birthyear;

        this.mobile = mobile;
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
        return this.mobile;

    }
    public void setphone(String phone){
        this.mobile = mobile;
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
