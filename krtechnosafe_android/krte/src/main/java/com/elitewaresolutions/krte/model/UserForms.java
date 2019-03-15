package com.elitewaresolutions.krte.model;

/**
 * Created by Tithi on 20-06-2017.
 */
public class UserForms {

    private int id1;
    private String equip;
    private String equipsr;
    private String make;
    private String dateMfg;
    private String cap;
    private String loc;
    private String shell;
    private String smooth;
    private String drain;
    private String tight;
    private String thick;
    private String lam;
    private String crack;
    private String hydro;
    private String range;
    private String valve;

    // Empty constructor
    public UserForms() {

    }

    public UserForms(int id1,String equip,String equipsr,String make,String dateMfg,String cap,String loc,String shell,String smooth,String drain,String tight,String thick,String lam,String crack,String hydro,String range,String valve){
      this.id1=id1;
        this.equip=equip;
        this.equipsr=equipsr;
        this.make=make;
        this.dateMfg=dateMfg;
        this.cap=cap;
        this.loc=loc;
        this.shell=shell;
        this.smooth=smooth;
        this.drain=drain;
        this.tight=tight;
        this.thick=thick;
        this.lam=lam;
        this.crack=crack;
        this.hydro=hydro;
        this.range=range;
        this.valve=valve;

    }
    public UserForms(String equip,String equipsr,String make,String dateMfg,String cap,String loc,String shell,String smooth,String drain,String tight,String thick,String lam,String crack,String hydro,String range,String valve){

        this.equip=equip;
        this.equipsr=equipsr;
        this.make=make;
        this.dateMfg=dateMfg;
        this.cap=cap;
        this.loc=loc;
        this.shell=shell;
        this.smooth=smooth;
        this.drain=drain;
        this.tight=tight;
        this.thick=thick;
        this.lam=lam;
        this.crack=crack;
        this.hydro=hydro;
        this.range=range;
        this.valve=valve;

    }

    public String getValve() {
        return valve;
    }

    public void setValve(String valve) {
        this.valve = valve;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getHydro() {
        return hydro;
    }

    public void setHydro(String hydro) {
        this.hydro = hydro;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public String getEquipsr() {
        return equipsr;
    }

    public void setEquipsr(String equipsr) {
        this.equipsr = equipsr;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getDateMfg() {
        return dateMfg;
    }

    public void setDateMfg(String dateMfg) {
        this.dateMfg = dateMfg;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getShell() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    public String getDrain() {
        return drain;
    }

    public void setDrain(String drain) {
        this.drain = drain;
    }

    public String getTight() {
        return tight;
    }

    public void setTight(String tight) {
        this.tight = tight;
    }

    public String getThick() {
        return thick;
    }

    public void setThick(String thick) {
        this.thick = thick;
    }

    public String getLam() {
        return lam;
    }

    public void setLam(String lam) {
        this.lam = lam;
    }

    public String getCrack() {
        return crack;
    }

    public void setCrack(String crack) {
        this.crack = crack;
    }

    public String getSmooth() {
        return smooth;
    }

    public void setSmooth(String smooth) {
        this.smooth = smooth;
    }
}
