package ru.fintech.request;

import io.restassured.authentication.*;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

import static ru.fintech.EndPoints.*;

public abstract class RequestModel {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setAuth(authorization())
                .addPathParam("siebel_id", "k.krasnikova")
                .build();
    }


    public static PreemptiveBasicAuthScheme authorization(){
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(USER_LOGIN);
        authScheme.setPassword(USER_PASS);
        return authScheme;

    }
    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }



}
