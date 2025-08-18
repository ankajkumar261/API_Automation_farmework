package com.Ankajkumar.modules;
import com.Ankajkumar.pojos.requestPOJO.restfulbooker.Booking;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

import com.Ankajkumar.pojos.requestPOJO.restfulbooker.BookingDates;
import com.Ankajkumar.pojos.responsePOJO.restfulbooker.BookingResponse;

public class PayloadManager {

    // The responsibility of POJO is to serialization and deserialization.

    Gson gson=new Gson();
    Faker faker;

    // Convert the Java Object into the JSON String to use as Payload.
    // Serialization
    public String createPayloadBookingAsString(){

        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        return gson.toJson(booking);

//        {
//            "firstname" : "LUCKY",
//                "lastname" : "Dutta",
//                "totalprice" : 3000,
//                "depositpaid" : true,
//                "bookingdates" : {
//            "checkin" : "2025-07-22",
//                    "checkout" : "2025-07-27"
//        },
//            "additionalneeds" : "Breakfast"
//        }
    }
    public String createPayloadBookingAsStringWrongBody(){
        Booking booking = new Booking();
        booking.setFirstname("会意; 會意");
        booking.setLastname("会意; 會意");
        booking.setTotalprice(112);
        booking.setDepositpaid(false);
        

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("5025-02-01");
        bookingdates.setCheckout("5025-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("会意; 會意");

        System.out.println(booking);

        // Java Object -> JSON
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }
    public String createPayloadBookingFakerJS(){
        //  This option is you dynamically generate the first name,
        //  last name and other variables.
        faker = new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1, 1000));
        booking.setDepositpaid(faker.random().nextBoolean());

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        // Java Object -> JSON
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;

        // method with the dynamic data we use,
        // we will fetch the data from excel file.
        // Apache POI
        // String the value, firstName, lastName, and everything, and then we will verify from the response.


    }

    // deserialization ( JSON String to Java Objects)
    // Convert the JSON String to Java Object so that we can verify response Body
    // DeSerialization

    public BookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }


}