package com.Ankajkumar.tests.restfulbooker.e2e_integration;


// TestE2EFlow_01

//  Test E2E Scenario 1

//  1. Create a Booking -> bookingID

// 2. Create Token -> token

// 3. Verify that the Create Booking is working - GET Request to bookingID

// 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

import com.Ankajkumar.base.BaseTest;
import com.Ankajkumar.endpoints.APIConstants;
import com.Ankajkumar.pojos.requestPOJO.restfulbooker.Booking;
import com.Ankajkumar.pojos.responsePOJO.restfulbooker.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

// 5. Delete the Booking - Need to get the token, bookingID from above request
public class TestIntegrationFlow1 extends BaseTest {

    @Test(groups = "qa", priority = 1)
    @Owner("Ankaj")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){


        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when()
                .body(payloadManager.createPayloadBookingAsString())
                .log().all().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Lucky");
        Integer bookingid = bookingResponse.getBookingid();
        iTestContext.setAttribute("bookingid",bookingid);


    }


    @Test(groups = "qa", priority = 2)
    @Owner("Ankaj")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext){

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        System.out.println(bookingid);
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertActions.verifyStringKeyNotNull(booking.getFirstname());


    }


    @Test(groups = "qa", priority = 3)
    @Owner("Ankaj")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token",token);


        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.createPayloadBookingAsString()).put();


        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertActions.verifyStringKeyNotNull(booking.getFirstname());
        assertActions.verifyStringKey(booking.getFirstname(),"Lucky");

    }

    @Test(groups = "qa", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");

        String basedelete = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basedelete);

        requestSpecification.basePath(basedelete);

        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.createPayloadBookingAsString()).delete();


        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(201);



    }





}
