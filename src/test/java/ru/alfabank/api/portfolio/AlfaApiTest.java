package ru.alfabank.api.portfolio;

import org.junit.jupiter.api.Test;
import ru.alfabank.api.Specifications;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class AlfaApiTest {
    private static final String URL = "https://baas-pl.moscow.alfaintra.net/";

    //@Test
    public void getPortfolioDataTest() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String pin = System.getProperty("user.pin");

        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOK());
        given()
                .when()
                .header("Authorization", "Bearer eyJraWQiOiJhbGZhLXRlc3Qtand0IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJmMTIzOTY1Ny0xMjVjLTQzNjgtYjIwNi01OTQ2YThmODQ2MTQiLCJhdWQiOiI5YmY3ZmMxOS1jNzQ3LTQ1MzAtODQxZC0zZjI5OGQwYzY1MDYiLCJpc3MiOiJodHRwczovL2lkLmFsZmFiYW5rLnJ1L29pZGMiLCJleHAiOjE5OTY0MTI4NTQsInNjb3BlX3NlcnZpY2VzIjpbImdldF9wb3J0Zm9saW9fcGVyc29uIiwiaW5pdGlhbC1wbGFjZW1lbnQiXSwiaWF0IjoxNjk2NDEyODU0LCJqdGkiOiI4YTZmZGViZC04M2Q2LTQwZmUtYTEwNC00OTA1Zjg0YjNlMTAiLCJzY29wZV9jbGFpbXMiOltdfQ.CNSx3EpSzzr6mDnYHfKlbsbut-d1wf1oNXi9iblTRgzvPD0tTyBbWAItN-ZYUEyMY3sO7M5o3opKy60YURa22Kg7bTtV0U-0nEssDuKN3-JIvVhBWFvBfBew-Q_JMr7lvX4ZsrQ8VCNIa2UZ5YtHFAl9lUKbYL5gN2V5AvJgqkZ1OWlzHWqssLgBn9yTyR-dmsndSLRamPRl9aRn9OpmUgGDNFybKJlaQmBiIt3sQK_ZcnQjw2UqezFUPaORcWThxiwBNTKaQviLncJ9cs2HQNXCgNZ5R3xy33UZ7536pRiB2tYIknAF6SCDDlddHferFIL_oRj7oXbnuLOUdUt60w")
                .get("api/dfa/gd/v1/dfa-profile/dfa-portfolio-list?pin={pin}", pin)
                .then().log().all()
                .extract().response();
    }

}
