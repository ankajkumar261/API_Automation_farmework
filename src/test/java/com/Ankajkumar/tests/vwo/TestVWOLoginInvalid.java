package com.Ankajkumar.tests.vwo;

import com.Ankajkumar.base.BaseTest;
import com.Ankajkumar.endpoints.APIConstants;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestVWOLoginInvalid extends BaseTest {
@Test
    public void test_VWO_Login_Negative(){

        requestSpecification.baseUri(APIConstants.APP_VWO_URL);
        response= RestAssured.given(requestSpecification).when().body(vwoPayloadManager.setLoginDataInvalid()).log().all().post();

        assertActions.verifyStatusCode(response, 401);


        String msg = vwoPayloadManager.getLoginDataInvalid(response.asString());
        assertActions.verifyStringKey(msg,"Invalid User");

    }

}
