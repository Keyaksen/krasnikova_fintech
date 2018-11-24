package ru.fintech.lesson7;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.fintech.request.RequestModel;

import static io.restassured.RestAssured.given;

public class PostSubscriptionsTest {

    @BeforeEach
    public void preparation(){
        DataPreparation.deleteAllSubscriptions();
    }

    @Test
    @DisplayName("Creating price-alert subscription")
    @Tag("post")
    public void postSubscriptionsTest(){
        System.out.println("-----------\"POST\" Subscriptions test-----------");
        given().spec(RequestModel.getRequestSpecification("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15", "system_code", "T-API"))
                .body(DataPreparation.createJSONObjectAAPL())
                .post("/contacts/{siebel_id}/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200)
                .body("instrument_id", Matchers.equalTo("AAPL_SPBXM"))
                .body("price_alert", Matchers.equalTo(94))
                .body("ticker", Matchers.equalTo("AAPL"));
    }

    @Test
    @DisplayName("Creating duplicate subscription")
    @Tag("post")
    public void postDuplicateSubscriptionsTest(){
        DataPreparation.createTSCSubscription();
        System.out.println("-----------\"POST\" DuplicateSubscriptions test-----------");
        given().spec(RequestModel.getRequestSpecification("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15", "system_code", "T-API"))
                .body(DataPreparation.createJSONObjectTSC())
                .post("/contacts/{siebel_id}/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(409)
                .body("error", Matchers.equalTo("could not create subscription: subscription constraint violated"));
   }

}
