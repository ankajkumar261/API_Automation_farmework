package com.Ankajkumar.tests.vwo;

import com.Ankajkumar.base.BaseTest;
import com.Ankajkumar.endpoints.APIConstants;
import com.Ankajkumar.pojos.responsePOJO.vwo.LoginResponse;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class TestVWOLoginValid extends BaseTest {


    @Test
    public void test_VWO_Login_Positive() {
        requestSpecification.baseUri(APIConstants.APP_VWO_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(vwoPayloadManager.setLoginDataValid()).log().all()
                .post();

        LoginResponse loginResponse = vwoPayloadManager.getLoginDataValid(response.asString());
        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);

        System.out.println(response.asString());


        System.out.println(loginResponse.getAccountId());
        assertActions.verifyStringKeyNotNull(loginResponse.getAccountId());
        assertActions.verifyStringKey(loginResponse.getName(), "Aman");


    }


}
