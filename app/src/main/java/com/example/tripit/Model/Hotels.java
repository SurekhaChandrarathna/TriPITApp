package com.example.tripit.Model;

public class Hotels
{
    private String HotelName, AboutHotel, HotelAddress, HotelContacts, HotelImage;

    public Hotels()
    {

    }

    public Hotels(String HotelName, String AboutHotel, String HotelAddress, String HotelContacts, String HotelImage) {
        this.HotelName = HotelName;
        this.AboutHotel = AboutHotel;
        this.HotelAddress = HotelAddress;
        this.HotelContacts = HotelContacts;
        this.HotelImage = HotelImage;
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

    public String getImage() {
        return HotelImage;
    }

    public void setImage(String hotelImage) {
        HotelImage = hotelImage;
    }



}

