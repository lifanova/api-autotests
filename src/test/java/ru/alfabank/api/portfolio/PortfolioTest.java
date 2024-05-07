package ru.alfabank.api.portfolio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.alfabank.api.Specifications;
import ru.alfabank.data.InvestorDto;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PortfolioTest {
    private static final String URL = "https://dfa-portfolio.dfa.internal.cbclusterint.alfaintra.net/";

    /**
     * Тесты на портфель физика
     */
    @Test
    public void getPersonPortfolioTest() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String smartAccountId = System.getProperty("user.investor.smartAccountId");

        // https://testdfa.alfabank.ru/retail-investor/webview/proxy/dfa-portfolio-api/v1/portfolio/person/list?smartAccountId=GyfzXjRm1Rfskzcx6iodG6ZmQeoYqRuQeRzLKn1FuvGX&page=0&size=100&sort=
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());
        given()
                .when()
                .get("api/v1/portfolio/person/list?smartAccountId={smartAccountId}", smartAccountId)
                .then().log().all()
                .extract().response();
    }

    /**
     * Тесты на портфель юрика
     */
    @Test
    public void getCompanyPortfolioTest() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String smartAccountId = System.getProperty("company.investor.smartAccountId");

        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());
        given()
                .when()
                .get("api/v1/portfolio/company/balances?smartAccountId={smartAccountId}", smartAccountId)
                .then().log().all()
                .extract().response();
    }


    /*
    Тесты на списки размещений
    {
  "dfaName": "string",
  "dfaRedemptionDate": "2024-05-06",
  "dfaParValue": 0,
  "dfaType": "string",
  "dfaRateAmount": "string",
  "ogrn": "string",
  "companyIssuerName": "string",
  "pin": "string",
  "customerType": "PERSON"
}
     */
    @Test
    public void getInitialPlacementListTest() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String smartAccountId = System.getProperty("company.investor.smartAccountId");
        String customerType = "PERSON";

        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());
        given()
                .when()
                .get("api/v1/portfolio/initial-placement/all?customerType={customerType}", customerType)
                .then().log().all()
                .extract().response();
    }

    /**
     * Тесты на заявки инвесторов
     */
    @Test
    public void getPersonStatementsTest() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String smartAccountId = System.getProperty("user.investor.smartAccountId");

        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());
        given()
                .when()
                .get("api/v1/portfolio/statement-list/{smartAccountId}", smartAccountId)
                .then().log().all()
                .extract().response();
    }

    @Test
    public void getCompanyStatementsTest() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String smartAccountId = System.getProperty("company.investor.smartAccountId");

        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());
        given()
                .when()
                .get("api/v1/portfolio/statement-list/{smartAccountId}", smartAccountId)
                .then().log().all()
                .extract().response();
    }


}
