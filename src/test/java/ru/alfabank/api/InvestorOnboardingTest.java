package ru.alfabank.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import ru.alfabank.data.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class InvestorOnboardingTest {
    //private static final String URL = "https://investor-onboarding.dfa.internal.cbclusterint.alfaintra.net/";
    private static final String URL = "https://regress.in/";

    @Test
    public void checkTest() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
    }
}
