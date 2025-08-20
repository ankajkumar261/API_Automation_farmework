package com.Ankajkumar.tests.restfulbooker.crud;

import com.Ankajkumar.base.BaseTest;
import com.Ankajkumar.endpoints.APIConstants;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

@Test
public class TestHealthCheck extends BaseTest {

    public void heathcheck(){

        requestSpecification.basePath(APIConstants.PING_URL);
        response = RestAssured.given(requestSpecification).when().log().all().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

        assertActions.verifyTrue(response.asString().contains("Created"));

    }
}
