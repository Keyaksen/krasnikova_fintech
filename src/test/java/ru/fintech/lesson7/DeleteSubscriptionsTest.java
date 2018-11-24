package ru.fintech.lesson7;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.fintech.request.RequestModel;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DeleteSubscriptionsTest {
    public List<String> idCode;

    @BeforeAll
    static void preparation(){
        DataPreparation.deleteAllSubscriptions();
    }

    public void getIdCodeForDelete(){
        idCode = given().spec(RequestModel.getRequestSpecification("getId"))
                .get("/contacts/{siebel_id}/subscriptions")
                .then()
                .extract()
                .jsonPath()
                .getList("id",String.class);
    }
    @Test
    @DisplayName("Deleting last subscription")
    @Tag("delete")
    public void deleteSubscriptionsTest(){
        DataPreparation.createTSCSubscription();
        DataPreparation.createAAPLSubscription();
        getIdCodeForDelete();
        System.out.println("-----------\"DELETE\" test-----------");
        given().spec(RequestModel.getRequestSpecification("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15", "system_code", "T-API"))
                .pathParam("subscription_id",idCode.get(0))
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
        String idWrongCode = "123abc123";
        given().spec(RequestModel.getRequestSpecification("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15", "system_code", "T-API"))
                .pathParam("subscription_id",idWrongCode)
                .delete("/contacts/{siebel_id}/subscriptions/{subscription_id}")
                .then()
                .assertThat()
                .statusCode(500)
                .body("error", Matchers.equalTo("could not cancel subscription: pq: invalid input syntax for uuid: \"" + idWrongCode+"\""));
    }


}
