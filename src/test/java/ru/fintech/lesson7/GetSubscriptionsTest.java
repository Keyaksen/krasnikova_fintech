package ru.fintech.lesson7;

import io.qameta.allure.*;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import ru.fintech.entity.SubInfo;
import ru.fintech.helper.DataPreparation;
import ru.fintech.request.RequestModel;

import java.util.List;

import static io.restassured.RestAssured.given;
@Epic("Тесты подписки")
@Feature("Тесты GET запросов")
@Story("Получение подписок пользователя")
@DisplayName("Тесты для получения информации о подписках пользователя")
public class GetSubscriptionsTest {
    public List<String> idCode;

    @BeforeEach
    public void preparation(){
        DataPreparation.deleteAllSubscriptions();
        DataPreparation.createTSCSubscription();
        DataPreparation.createAAPLSubscription();
    }
    @Link("https://fintech-trading-qa.tinkoff.ru/v1/md/docs/#/Subscriptions/md-contacts-subscriptions-retrive")
    @Test
    @DisplayName("Get all subscriptions")
    @Description("Получение всех подписок пользователя по его siebel_id")
    @Tag("get")
    public void getSubscriptionsSimpleTest(){
        System.out.println("-----------\"GET\" SubscriptionsSimple test-----------");
        given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get("/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200);
    }

    @Issue("TST-101")
    @Test
    @DisplayName("Get all subscriptions to List")
    @Description("Получение всех подписок пользователя по его siebel_id в List")
    @Tag("get")
    public void getSubscriptionsExtractTest(){
        System.out.println("-----------\"GET\" SubscriptionsExtract test-----------");
        List<SubInfo> subInfo = given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get("/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".",SubInfo.class);
        System.out.println("Result: " + subInfo);
    }

    @TmsLink("123321")
    @Test
    @DisplayName("Get all subscriptions without parameters")
    @Description("Получение ошибки при передаче запроса о подписках без параметров")
    @Tag("get")
    public void getSubscriptionsWithoutParamsTest(){
        System.out.println("-----------\"GET\" SubscriptionsWithoutParams test-----------");
        given().spec(RequestModel.getRequestSpecification())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get("/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(400)
                .body("error", Matchers.equalTo("missing \"request_id\" in query"));
    }

    @Test
    @DisplayName("Get all id of subscriptions")
    @Description("Получение всех id всех подписок пользователя по его siebel_id")
    @Tag("get")
    public void getSubscriptionsIdCodeTest(){
        System.out.println("-----------\"GET\" SubscriptionsIdCode test-----------");
        idCode = given().spec(RequestModel.getRequestSpecification())
                .queryParam("request_id","84g5df1g-5fg6-7d5f-1e61-874d54tfb15")
                .queryParam("system_code","T-API")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get("/subscriptions")
                .then()
                .specification(RequestModel.getResponseSpecification())
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("subs_info.json"))
                .extract()
                .jsonPath()
                .getList("id",String.class);
        System.out.println("Result: " + idCode);
    }


}
