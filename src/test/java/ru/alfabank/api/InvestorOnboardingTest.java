package ru.alfabank.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.alfabank.data.InvestorDto;
import ru.alfabank.data.NotFoundInfo;

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

    @Test
    public void getInvestorByPinNotFoundTest() {
        String pin = "BCDBAL";
        String description = String.format("Not found investor by pin %s", pin);

        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecNotFound());
        NotFoundInfo info = given()
                .when()
                .get("api/v1/investor/pin/{pin}", pin)
                .then().log().all()
                .extract().response().as(NotFoundInfo.class);
        System.out.println(info.toString());

        Assertions.assertEquals(description, info.getDescription());
    }
}
