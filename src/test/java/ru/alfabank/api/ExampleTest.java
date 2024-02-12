package ru.alfabank.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.alfabank.data.UserData;

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
    }
}
