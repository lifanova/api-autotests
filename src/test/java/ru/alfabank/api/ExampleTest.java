package ru.alfabank.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.alfabank.data.*;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ExampleTest {
    private static final String URL = "https://regres.in/";

    @Test
    public void firstTest() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.stream().forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith(".com")));
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

        for (int i = 0; i < avatars.size(); i++) {
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));
        }

    }

    @Test
    public void specTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.stream().forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith(".com")));
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

        for (int i = 0; i < avatars.size(); i++) {
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }

    @Test
    public void successRegTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());
        Integer id = 4;
        String token = "";
        Register user = new Register("", "");

        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(SuccessReg.class);

        Assertions.assertEquals(id, successReg.getId());
        Assertions.assertEquals(token, successReg.getToken());
    }

    @Test
    public void unSuccessRegTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec400());
        Integer id = 4;
        String token = "";
        Register user = new Register("", "");

        UnSuccessReg unSuccessReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(UnSuccessReg.class);

        Assertions.assertEquals("Missing password!", unSuccessReg.getError());

    }

    @Test
    public void sortedYearsTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());

        List<ColorsData> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());

        Assertions.assertEquals(sortedYears, years);
    }


    @Test
    public void deleteUserTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(204));
        //204 - No Content
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }

    @Test
    public void timeTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        UserData user = new UserData();

        UserData response = given()
                .body(user)
                .when()
                .put("api/user/2")
                .then().log().all()
                .extract().as(UserData.class);

        String regex = "(.{5}$)";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        Assertions.assertEquals(currentTime, response.getUpdatedAt().replaceAll(regex, ""));
    }
}
