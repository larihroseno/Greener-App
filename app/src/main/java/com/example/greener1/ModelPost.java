package com.example.greener1;

public class ModelPost {

     String uid, userName, email,dp, pId, pTitle, pDescr, profileImage, pTime;

public ModelPost (){

    }

    public ModelPost(String uid, String userName, String email,String dp, String pId, String pTitle, String pDescr, String profileImage, String pTime) {
        this.uid = uid;
        this.userName = userName;
        this.email = email;
        this.pId = pId;
        this.pTitle = pTitle;
        this.pDescr = pDescr;
        this.profileImage = profileImage;
        this.pTime = pTime;
        this.dp = dp;
    }

    public String getUid() {
        return uid;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpDescr() {
        return pDescr;
    }

    public void setpDescr(String pDescr) {
        this.pDescr = pDescr;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }
}
