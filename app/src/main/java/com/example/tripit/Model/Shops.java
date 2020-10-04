package com.example.tripit.Model;

public class Shops
{
    private String date, time, nam, name, price,size , quantitiy, image, pid;

    public Shops()
    {

    }

    public Shops(String date, String time, String nam, String name, String price, String size, String quantitiy, String image, String pid) {
        this.date = date;
        this.time = time;
        this.nam = nam;
        this.name = name;
        this.price = price;
        this.size = size;
        this.quantitiy = quantitiy;
        this.image = image;
        this.pid = pid;
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

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getquantity() {
        return quantitiy;
    }

    public void setquantity(String quantitiy) {
        this.quantitiy = quantitiy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
