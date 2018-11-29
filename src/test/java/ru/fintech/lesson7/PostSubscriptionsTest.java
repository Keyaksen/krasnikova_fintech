package ru.fintech.lesson7;

import io.qameta.allure.*;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.fintech.helper.DataPreparation;
import ru.fintech.request.RequestModel;

import static io.restassured.RestAssured.given;
@Epic("Тесты подписки")
@Feature("Тесты POST запросов")
@Story("Создание подписок пользователя")
@DisplayName("Тесты для создания подписок пользователя по siebel_id")
public class PostSubscriptionsTest {

    @BeforeEach
    public void preparation(){
        DataPreparation.deleteAllSubscriptions();
    }

    @Link("https://fintech-trading-qa.tinkoff.ru/v1/md/docs/#/Subscriptions/md-contacts-subscription-create")
    @Test
    @DisplayName("Creating price-alert subscription")
    @Description("Создание подписки APPL и проверка её создания")
    @Tag("post")
    public void postSubscriptionsTest(){
        System.out.println("-----------\"POST\" Subscriptions test-----------");
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(DataPreparation.createJSONObjectAAPL())
                .post("/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200)
                .body("instrument_id", Matchers.equalTo("AAPL_SPBXM"))
                .body("price_alert", Matchers.equalTo(94))
                .body("ticker", Matchers.equalTo("AAPL"));
    }

    @TmsLink("321123")
    @Test
    @DisplayName("Creating duplicate subscription")
    @Description("Получение ошибки при создании повторяющейся подписки")
    @Tag("post")
    public void postDuplicateSubscriptionsTest(){
        DataPreparation.createTSCSubscription();
        System.out.println("-----------\"POST\" DuplicateSubscriptions test-----------");
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(DataPreparation.createJSONObjectTSC())
                .post("/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(409)
                .body("error", Matchers.equalTo("could not create subscription: subscription constraint violated"));
   }

}
