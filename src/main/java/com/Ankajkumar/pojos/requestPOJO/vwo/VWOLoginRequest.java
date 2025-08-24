package com.Ankajkumar.pojos.requestPOJO.vwo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VWOLoginRequest {

    private String password;
    @SerializedName("recaptcha_response_field")
    private String recaptchaResponseField;
    @Expose
    private Boolean remember;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecaptchaResponseField() {
        return recaptchaResponseField;
    }

    public void setRecaptchaResponseField(String recaptchaResponseField) {
        this.recaptchaResponseField = recaptchaResponseField;
    }

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Expose
    private String username;
}
