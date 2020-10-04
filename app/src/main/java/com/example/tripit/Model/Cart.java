package com.example.tripit.Model;

public class Cart
{
    private String pid, size , price, quantity;

    public Cart()
    {

    }

    public Cart(String pid, String size, String price, String quantity)
    {
        this.pid = pid;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }

    public String getpid() {
        return pid;
    }

    public void setpid(String pid) {
        this.pid = pid;
    }

    public String getsize() {
        return size;
    }

    public void setsize(String size) {
        this.size = size;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }

    public String getquantity() {
        return quantity;
    }

    public void setquantity(String quantity) {
        this.quantity = quantity;
    }
}
