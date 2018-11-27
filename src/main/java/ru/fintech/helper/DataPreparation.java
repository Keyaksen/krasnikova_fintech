package ru.fintech.helper;

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
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .body(createJSONObjectTSC())
                .post("/subscriptions");
    }

    //creating AAPL subscription
    public static void createAAPLSubscription(){
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .body(createJSONObjectAAPL())
                .post("/subscriptions");
    }

    public static List<String> getIdCodeForDelete(){
        return given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .get("/subscriptions")
                .then()
                .extract()
                .jsonPath()
                .getList("id",String.class);
    }

    public static void deleteSubscription (String subscriptionId){
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .pathParam("subscription_id",subscriptionId)
                .delete("/subscriptions/{subscription_id}");

    }

    public static void deleteAllSubscriptions () {
        List<String> idSubs = given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .get("/subscriptions")
                .then()
                .extract()
                .jsonPath()
                .getList("id",String.class);
        for (String idCode1: idSubs) {
            deleteSubscription(idCode1);
        }
    }

}
