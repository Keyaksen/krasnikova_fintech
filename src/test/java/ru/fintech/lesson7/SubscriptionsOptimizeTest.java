package ru.fintech.lesson7;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.fintech.entity.SubInfo;
import ru.fintech.request.RequestModel;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SubscriptionsOptimizeTest {
public String idCode;



    @Test
    @DisplayName("Get all subscriptions")
    @Tag("get")
    public void getSubscriptionsSimpleTest(){
        System.out.println("-----------\"GET\" test-----------");
        given().spec(RequestModel.getRequestSpecification("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15", "system_code", "T-API", "siebel_id", "k.krasnikova"))
                .get("/contacts/{siebel_id}/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200);
    }

   /*@Test
   @DisplayName("Get all subscriptions to List")
   @Tag("get")
   public void getSubscriptionsExtractTest(){
        System.out.println("-----------\"GET\" test-----------");
        List<SubInfo> subInfo = given().spec(RequestModel.getRequestSpecification())
                .get("/contacts/{siebel_id}/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".",SubInfo.class);
        System.out.println("Result: " + subInfo);
    }*/

    @Test
    @DisplayName("Get all subscriptions without parameters")
    @Tag("get")
    public void getSubscriptionsWithoutParamsTest(){
        System.out.println("-----------\"GET\" test-----------");
        given().spec(RequestModel.getRequestSpecification())
                .get("/contacts/{siebel_id}/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(400)
                .body("error", Matchers.equalTo("missing \"request_id\" in query"));
    }
/*
   //@BeforeEach
   @Test
   @DisplayName("Get all id of subscriptions")
   @Tag("get")
    public void getSubscriptionsIdCodeTest(){
        System.out.println("-----------\"GET\" test-----------");
        idCode = given().spec(RequestModel.getRequestSpecification())
                .get("/contacts/{siebel_id}/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("subs_info.json"))
                .extract()
                .jsonPath()
                .getString("id");
        System.out.println("Result: " + idCode);
    }

    //creating JSON object for POST query
    public static JSONObject createJSONObject(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("instrument_id", "TCS_SPBXM");
        requestParams.put("sec_name", "TCS");
        requestParams.put("sec_type","equity");
        requestParams.put("price_alert",15);
        return requestParams;
    }

    @Test
    @DisplayName("Creating price-alert subscription")
    @Tag("post")
    public void postSubscriptionsTest(){
        System.out.println("-----------\"POST\" test-----------");
        given().spec(RequestModel.getRequestSpecification())
                .body(createJSONObject())
                .post("/contacts/{siebel_id}/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Creating duplicate subscription")
    @Tag("post")
    public void postDuplicateSubscriptionsTest(){
        System.out.println("-----------\"POST\" test-----------");
        given().spec(RequestModel.getRequestSpecification())
                .body(createJSONObject())
                .post("/contacts/{siebel_id}/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(409)
                .body("error", Matchers.equalTo("could not create subscription: subscription constraint violated"));
   }

    @Test
    @DisplayName("Deleting last subscription")
    @Tag("delete")
    public void deleteSubscriptionsTest(){
        System.out.println("recall GET query");
        getSubscriptionsIdCodeTest();
        System.out.println("-----------\"DELETE\" test-----------");
        idCode=idCode.substring(1);
        idCode=idCode.substring(0,idCode.length()-1);
        String []splitArray = idCode.split(",");
        given().spec(RequestModel.getRequestSpecification())
                .pathParam("subscription_id",splitArray[0])
                .delete("/contacts/{siebel_id}/subscriptions/{subscription_id}")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Deleting nonexistent subscription")
    @Tag("delete")
    public void deleteSubscriptionsErrorTest(){
        System.out.println("-----------\"DELETE\" test-----------");
        idCode="123abc123";
        given().spec(RequestModel.getRequestSpecification())
                .pathParam("subscription_id",idCode)
                .delete("/contacts/{siebel_id}/subscriptions/{subscription_id}")
                .then()
                .assertThat()
                .statusCode(500)
                .body("error", Matchers.equalTo("could not cancel subscription: pq: invalid input syntax for uuid: \"" + idCode+"\""));
    }*/

}
