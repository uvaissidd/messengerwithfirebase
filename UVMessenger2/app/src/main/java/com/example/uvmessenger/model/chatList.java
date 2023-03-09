package com.example.uvmessenger.model;

public class chatList {
    private String userID;
    private String userName;
    private String description;
    private String date;
    private String urlProfile;

    public chatList(){

    }
    public chatList(String useeID,String userName,String descriotion, String date,String urlProfile){
        this.userID=useeID;
        this.userName=userName;
        this.description=descriotion;
        this.date=date;
        this.urlProfile=urlProfile;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }
}
