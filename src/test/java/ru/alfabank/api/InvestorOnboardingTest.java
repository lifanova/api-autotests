package ru.alfabank.api;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.alfabank.data.InvestorDto;
import ru.alfabank.data.UserData;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class InvestorOnboardingTest {
    private static final String URL = "https://investor-onboarding.dfa.internal.cbclusterint.alfaintra.net/";

    @Test
    public void getInvestorByPinTest() {
        String pin = "BDP7YC";
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());
        InvestorDto info = given()
                .when()
                .get("api/v1/investor/pin/{pin}", pin)
                .then().log().all()
                .extract().response().as(InvestorDto.class);
        System.out.println(info.toString());

        Assertions.assertEquals(pin, info.getPin());
    }
}
