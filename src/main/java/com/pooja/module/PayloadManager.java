package com.pooja.module;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.pooja.pojos.*;
public class PayloadManager {

    Gson gson;
    Faker faker;
    String expectFirstname;
    public String createPayloadGson(){

         faker=new Faker();
         expectFirstname=faker.name().firstName();

        Booking booking=new Booking();

        booking.setFirstname(expectFirstname);
        booking.setLastname("Rathod");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);

        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2024-02-04");
        bookingdates.setCheckout("2024-02-06");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        gson=new Gson();
        String jsonStringBooking=gson.toJson(booking);
        System.out.println(jsonStringBooking);

     return jsonStringBooking;

    }

    public String updatePayload(){

        Booking booking=new Booking();

        booking.setFirstname("Pooja");
        booking.setLastname("Rathod");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);

        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2024-02-04");
        bookingdates.setCheckout("2024-02-06");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        gson=new Gson();
        String jsonStringBooking=gson.toJson(booking);
        System.out.println(jsonStringBooking);

        return jsonStringBooking;

    }

    public String setAuthPayload(){

        Auth auth=new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        gson=new Gson();
        String jsonStringBooking=gson.toJson(auth);
        System.out.println(jsonStringBooking);

        return jsonStringBooking;

    }

    public String getTokenFromJson(String tokenRspone){

        gson=new Gson();
        TokenResponse tokenResponse1=gson.fromJson(tokenRspone, TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public BookingResponse bookingResponseJava(String responseString){

        gson=new Gson();
        BookingResponse bookingResponse=gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

 public Booking bookingResponsePutReq(String responseString){

        gson=new Gson();
        Booking booking=gson.fromJson(responseString, Booking.class);

        return booking;

 }

}
