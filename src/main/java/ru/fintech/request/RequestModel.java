package ru.fintech.request;

import io.restassured.authentication.*;
import io.restassured.builder.*;
import io.restassured.filter.log.*;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

import static ru.fintech.EndPoints.*;

public abstract class RequestModel {

    public static RequestSpecification getRequestSpecification(String fRequestId, String requestId, String fSystemCode, String systemCode) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setAuth(authorization())
                .addPathParam("siebel_id", "k.krasnikova")
                .addQueryParam(fRequestId,requestId)
                .addQueryParam(fSystemCode,systemCode)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    //overload method getRequestSpecification
    public static RequestSpecification getRequestSpecification() { //for error test getSubscriptionsWithoutParamsTest
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setAuth(authorization())
                .addPathParam("siebel_id", "k.krasnikova")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    //overload method getRequestSpecification for DataPreparation
        public static RequestSpecification getRequestSpecification(String flag) { //for error test getSubscriptionsWithoutParamsTest
            return new RequestSpecBuilder()
                    .setBaseUri(BASE_URI)
                    .setBasePath(BASE_PATH)
                    .addQueryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                    .addQueryParam("system_code","T-API")
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
