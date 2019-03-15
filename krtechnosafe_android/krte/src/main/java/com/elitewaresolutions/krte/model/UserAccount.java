package com.elitewaresolutions.krte.model;

/**
 * Created by Tithi on 08-06-2017.
 */
public class UserAccount {
    private int id;
    String fname;
    String lname;
    String email;
    String pass;
    String civ;
    String birth;
    String ph;
    String coun;
    String hob;

    // Empty constructor
    public UserAccount() {

    }

    public UserAccount(int id,String fname,String lname,String email,String pass,String civ,String birth,String ph, String coun,String hob){
        this.id=id;
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
        this.civ=civ;
        this.birth=birth;
        this.ph=ph;
        this.coun=coun;
        this.hob=hob;

    }

    public UserAccount(String fname,String lname,String email,String pass ){
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
    }

    public UserAccount(String civ,String birth,String ph,String coun,String hob ){
        this.civ=civ;
        this.birth=birth;
        this.ph=ph;
        this.coun=coun;
        this.hob=hob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCiv() {
        return civ;
    }

    public void setCiv(String civ) {
        this.civ = civ;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getCoun() {
        return coun;
    }

    public void setCoun(String coun) {
        this.coun = coun;
    }

    public String getHob() {
        return hob;
    }

    public void setHob(String hob) { this.hob = hob; }

}
