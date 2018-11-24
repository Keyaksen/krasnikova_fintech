package ru.fintech.lesson7;

import org.json.JSONObject;
import ru.fintech.request.RequestModel;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DataPreparation {

    //creating JSON object for POST query (TSC subscription)
    public static JSONObject createJSONObjectTSC(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("instrument_id", "TCS_SPBXM");
        requestParams.put("sec_name", "TCS");
        requestParams.put("sec_type","equity");
        requestParams.put("price_alert",15);
        return requestParams;
    }

    //creating JSON object for POST query (AAPL subscription)
    public static JSONObject createJSONObjectAAPL(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("instrument_id", "AAPL_SPBXM");
        requestParams.put("sec_name", "AAPL");
        requestParams.put("sec_type","equity");
        requestParams.put("price_alert",94);
        return requestParams;
    }

    //creating TSC subscription
    public static void createTSCSubscription(){
        given().spec(RequestModel.getRequestSpecification("create"))
                .body(createJSONObjectTSC())
                .post("/contacts/{siebel_id}/subscriptions");
    }

    //creating AAPL subscription
    public static void createAAPLSubscription(){
        given().spec(RequestModel.getRequestSpecification("create"))
                .body(createJSONObjectAAPL())
                .post("/contacts/{siebel_id}/subscriptions");
    }



    public static void deleteSubscription (String subscriptionId){
        given().spec(RequestModel.getRequestSpecification("delete"))
                .pathParam("subscription_id",subscriptionId)
                .delete("/contacts/{siebel_id}/subscriptions/{subscription_id}");

    }

    public static void deleteAllSubscriptions () {
        List<String> idSubs = given().spec(RequestModel.getRequestSpecification("delete"))
                .get("/contacts/{siebel_id}/subscriptions")
                .then()
                .extract()
                .jsonPath()
                .getList("id",String.class);
        for (String idCode1: idSubs) {
            deleteSubscription(idCode1);
        }
    }

}
