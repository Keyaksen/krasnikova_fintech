package ru.fintech.lesson7;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.fintech.helper.DataPreparation;
import ru.fintech.request.RequestModel;


import static io.restassured.RestAssured.given;

public class DeleteSubscriptionsTest {

    @BeforeAll
    static void preparation(){
        DataPreparation.deleteAllSubscriptions();
        DataPreparation.createTSCSubscription();
        DataPreparation.createAAPLSubscription();
    }


    @Test
    @DisplayName("Deleting last subscription")
    @Tag("delete")
    public void deleteSubscriptionsTest(){
        System.out.println("-----------\"DELETE\" Subscriptions test-----------");
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .pathParam("subscription_id",DataPreparation.getIdCodeForDelete().get(0))
                .delete("/subscriptions/{subscription_id}")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Deleting nonexistent subscription")
    @Tag("delete")
    public void deleteSubscriptionsErrorTest(){
        System.out.println("-----------\"DELETE\" SubscriptionsError test-----------");
        String idWrongCode = "123abc123";
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .pathParam("subscription_id",idWrongCode)
                .delete("/subscriptions/{subscription_id}")
                .then()
                .assertThat()
                .statusCode(500)
                .body("error", Matchers.equalTo("could not cancel subscription: pq: invalid input syntax for uuid: \"" + idWrongCode+"\""));
    }


}
