package com.binninfo.tobacco.entity;

public class RobotInfo {
    private Integer id;
    private String robotid;
    private String address;
    private Integer sleepTime;
    private String deniedWordOne;
    private String deniedWordTwo;
    private Integer ltNumber;
    private Integer gtNumber;
    private String overtimeWord;
    private String location;
    private String welcome;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getOvertimeWord() {
        return overtimeWord;
    }

    public void setOvertimeWord(String overtimeWord) {
        this.overtimeWord = overtimeWord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRobotid() {
        return robotid;
    }

    public void setRobotid(String robotid) {
        this.robotid = robotid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getDeniedWordOne() {
        return deniedWordOne;
    }

    public void setDeniedWordOne(String deniedWordOne) {
        this.deniedWordOne = deniedWordOne;
    }

    public String getDeniedWordTwo() {
        return deniedWordTwo;
    }

    public void setDeniedWordTwo(String deniedWordTwo) {
        this.deniedWordTwo = deniedWordTwo;
    }

    public Integer getLtNumber() {
        return ltNumber;
    }

    public void setLtNumber(Integer ltNumber) {
        this.ltNumber = ltNumber;
    }

    public Integer getGtNumber() {
        return gtNumber;
    }

    public void setGtNumber(Integer gtNumber) {
        this.gtNumber = gtNumber;
    }
}
