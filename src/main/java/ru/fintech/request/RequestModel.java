package ru.fintech.request;

import io.restassured.authentication.*;
import io.restassured.builder.*;
import io.restassured.filter.log.*;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

import static ru.fintech.EndPoints.*;

public abstract class RequestModel {

    public static RequestSpecification getRequestSpecification() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(USER_LOGIN);
        authScheme.setPassword(USER_PASS);
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setAuth(authScheme)
                .addQueryParam("request_id", "84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .addQueryParam("system_code", "T-API")
                .addPathParam("siebel_id", "k.krasnikova")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                //.addFilter(new ErrorLoggingFilter())  //for error demonstrate
                .build();
    }

    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }
    public static RequestSpecification getRequestSpecification1() { //for error test getSubscriptionsWithoutParamsTest
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(USER_LOGIN);
        authScheme.setPassword(USER_PASS);
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setAuth(authScheme)
                .addPathParam("siebel_id", "k.krasnikova")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                //.addFilter(new ErrorLoggingFilter())  //for error demonstrate
                .build();
    }

    public static ResponseSpecification getResponseSpecification1() {//for error test getSubscriptionsWithoutParamsTest
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(400)
                .build();
    }

}
