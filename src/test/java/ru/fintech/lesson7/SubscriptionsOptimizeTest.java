package ru.fintech.lesson7;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.json.JSONObject;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.fintech.entity.SubInfo;
import ru.fintech.request.RequestModel;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SubscriptionsOptimizeTest {
public String idCode;
    @Test
    @Tag("get")
    public void getSubscriptionsSimpleTest(){
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id", "6f994192-e701-11e8-9f32-f2801f1b9fd1")
                .queryParam("system_code", "T-API")
                .get("/contacts/k.krasnikova/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200);
    }

   @Test
   @Tag("get")
   public void getSubscriptionsExtractTest(){
        List<SubInfo>  subInfo = new ArrayList<SubInfo>() ;
                subInfo.add(given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id", "6f994192-e701-11e8-9f32-f2801f1b9fd1")
                .queryParam("system_code", "T-API")
                .get("/contacts/k.krasnikova/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200)
                .extract()
                .as(SubInfo.class));
        System.out.println("Result: " + subInfo);
    }

   /* @Test
    public void getSubscriptionsErrorTest(){
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id", "6f994192-e701-11e8-9f32-f2801f1b9fd1")
                .queryParam("system_code", "T-API")
                .get("/contacts/k.krasnikova/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .statusCode(404)
                ;
    }*/

   @Test
   @Tag("get")
    public void getInstrumentsExtractIdCodeTest(){
        idCode = given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id", "6f994192-e701-11e8-9f32-f2801f1b9fd1")
                .queryParam("system_code", "T-API")
                .get("/contacts/k.krasnikova/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200)
                //.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("subs_info.json"))
                .extract()
                .jsonPath()
                .getString("id");
        System.out.println("Result: " + idCode);
    }
    @Test
    @Tag("post")
    public void postSubscriptionsSimpleTest(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("instrument_id", "GAZP_TQBR");
        requestParams.put("sec_name", "GAZP");
        requestParams.put("sec_type","equity");
        requestParams.put("price_alert",15);
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id", "6f994192-e701-11e8-9f32-f2801f1b9fd1")
                .queryParam("system_code", "T-API")
                .body(requestParams)
                .post("/contacts/k.krasnikova/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200);
    }
    @Test
    @Tag("delete")
    public void deleteSubscriptionsSimpleTest(){
        idCode = given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id", "6f994192-e701-11e8-9f32-f2801f1b9fd1")
                .queryParam("system_code", "T-API")
                .get("/contacts/k.krasnikova/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200)
                //.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("instrument_info.json"))
                .extract()
                .jsonPath()
                .getString("id");
        idCode=idCode.substring(1);
        String []splitArray = idCode.split(",");
        given().spec(RequestModel.getRequestSpecification())
                .pathParam("subscription_id",splitArray[0])
                .queryParam("request_id", "6f994192-e701-11e8-9f32-f2801f1b9fd1")
                .queryParam("system_code", "T-API")
                .delete("/contacts/k.krasnikova/subscriptions/{subscription_id}")
                .then()
                //.specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200);
    }

}
