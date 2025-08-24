package com.Ankajkumar.tests.restfulbooker.crud;


import com.Ankajkumar.base.BaseTest;
import com.Ankajkumar.endpoints.APIConstants;
import com.Ankajkumar.pojos.responsePOJO.restfulbooker.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
public class TestCreateBooking extends BaseTest {


    @Test(groups = "reg", priority = 1)
    @Owner("Ankaj kumar")
    @Description("TC#1 - Verify that the Booking can be Created")
    public void testCreateBookingPOST_Positive() {

        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .log().all().post();

        //Extraction Part - 2
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());


        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Lucky");


    }

    @Test(groups = "reg", priority = 1)
    @Owner("Ankaj kumar")
    @Description("TC#2 - Verify that the Booking can't be Created, When Payload is null")
    public void testCreateBookingPOST_Negative() {

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().
                body("{}").log().all().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);
    }

    @Test(groups = "reg", priority = 1)
    @Owner("Ankaj kumar")
    @Description("TC#3 - Verify that the Booking can be Created with Faker")
    public void testCreateBooking_faker_POST_Positive(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingFakerJS())
                .log().all().post();

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKeyNotNull(bookingResponse.getBooking().getFirstname());

    }
    @Test(groups = "reg", priority = 1)
    @Owner("Ankaj kumar")
    @Description("TC#4 - Verify that the Booking can be Created with Faker with no html payload")
    public void testCreateBooking_faker_negative_() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        requestSpecification.contentType(ContentType.HTML);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingFakerJS())
                .log().all().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);

       }


    @Test(groups = "reg", priority = 1)
    @Owner("Ankaj kumar")
    @Description("TC#5 - Verify that the Booking can be Created, URL is wrong")
    public void testCreateBookingPOST_NEGATIVE_BASE_URL(){
        requestSpecification.baseUri(APIConstants.APP_VWO_URL);
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        requestSpecification.contentType(ContentType.HTML);

        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingFakerJS()).log().all().post();



        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

    }

}
