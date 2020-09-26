package com.example.guides.Model;

public class Guides {

    String  category , guidename , guidecontactnumber , guideage , guideexperiance , guideamount , guidimage , gid , date , time ;

    public Guides()
    {


    }

    public Guides(String category, String guidename, String guidecontactnumber, String guideage, String guideexperiance, String guideamount, String guidimage, String gid, String date, String time) {
        this.category = category;
        this.guidename = guidename;
        this.guidecontactnumber = guidecontactnumber;
        this.guideage = guideage;
        this.guideexperiance = guideexperiance;
        this.guideamount = guideamount;
        this.guidimage = guidimage;
        this.gid = gid;
        this.date = date;
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGuidename() {
        return guidename;
    }

    public void setGuidename(String guidename) {
        this.guidename = guidename;
    }

    public String getGuidecontactnumber() {
        return guidecontactnumber;
    }

    public void setGuidecontactnumber(String guidecontactnumber) {
        this.guidecontactnumber = guidecontactnumber;
    }

    public String getGuideage() {
        return guideage;
    }

    public void setGuideage(String guideage) {
        this.guideage = guideage;
    }

    public String getGuideexperiance() {
        return guideexperiance;
    }

    public void setGuideexperiance(String guideexperiance) {
        this.guideexperiance = guideexperiance;
    }

    public String getGuideamount() {
        return guideamount;
    }

    public void setGuideamount(String guideamount) {
        this.guideamount = guideamount;
    }

    public String getGuidimage() {
        return guidimage;
    }

    public void setGuidimage(String guidimage) {
        this.guidimage = guidimage;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
