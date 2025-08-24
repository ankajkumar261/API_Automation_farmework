package com.Ankajkumar.modules.VWOpayloadManager;

import com.Ankajkumar.pojos.requestPOJO.vwo.VWOLoginRequest;
import com.Ankajkumar.pojos.responsePOJO.vwo.InvalidloginResponse;
import com.Ankajkumar.pojos.responsePOJO.vwo.LoginResponse;
import com.google.gson.Gson;

public class VWOPayloadManager {

    Gson gson;

    public String setLoginDataInvalid(){
        VWOLoginRequest loginRequest = new VWOLoginRequest();
        loginRequest.setUsername("contact+aug@thetestingacademy.com");
        loginRequest.setPassword("abc");
        loginRequest.setRemember(false);
        loginRequest.setRecaptchaResponseField("");

        gson = new Gson();
        String jsonPayloadString = gson.toJson(loginRequest);
        System.out.println("Payload Login to the -> " + jsonPayloadString);
        return jsonPayloadString;

    }

    public String setLoginDataValid(){
        VWOLoginRequest loginRequest = new VWOLoginRequest();
        loginRequest.setUsername("contact+aug@thetestingacademy.com");
        loginRequest.setPassword("TtxkgQ!s$rJBk85");
        loginRequest.setRemember(false);
        loginRequest.setRecaptchaResponseField("");


        gson = new Gson();
        String jsonPayloadString = gson.toJson(loginRequest);
        System.out.println("Payload Login to the -> " + jsonPayloadString);
        return jsonPayloadString;
    }

    public String getLoginDataInvalid(String loginResponseEx){
        gson = new Gson();
        InvalidloginResponse invalidloginResponse = gson.fromJson(loginResponseEx, InvalidloginResponse.class);
        return invalidloginResponse.getMessage();
    }

    public LoginResponse getLoginDataValid(String loginResponseEx){
        gson = new Gson();
        LoginResponse loginResponse = gson.fromJson(loginResponseEx, LoginResponse.class);
        return loginResponse;

    }



}
