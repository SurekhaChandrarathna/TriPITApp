package com.example.hotels.Model;

public class Hotels
{
    private String HotelName, AboutHotel, HotelAddress, HotelContacts, Hotelimage;

    public Hotels()
    {

    }

    public Hotels(String HotelName, String AboutHotel, String HotelAddress, String HotelContacts ,String Hotelimage)
    {
        this.HotelName = HotelName;
        this.AboutHotel = AboutHotel;
        this.HotelAddress = HotelAddress;
        this.HotelContacts = HotelContacts;
        this.Hotelimage= Hotelimage;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getAboutHotel() {
        return AboutHotel;
    }

    public void setAboutHotel(String aboutHotel) {
        AboutHotel = aboutHotel;
    }

    public String getHotelAddress() {
        return HotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        HotelAddress = hotelAddress;
    }

    public String getHotelContacts() {
        return HotelContacts;
    }

    public void setHotelContacts(String hotelContacts) {
        HotelContacts = hotelContacts;
    }

    public String getHotelimage() {
        return Hotelimage;
    }

    public void setHotelimage(String hotelimage) {
        Hotelimage = hotelimage;
    }
}
