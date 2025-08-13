package com.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://tradgo.in/appapi4/"; // Change to your server URL
    }

    @Test
    public void testValidLogin() {
        given()
            .header("Content-Type", "application/json")
            .body("{\"mobile\": \"7041127517\"}")
        .when()
            .post("/Logincheckuser")
        .then()
            .statusCode(200)
            .body("status", equalTo("success"));
    }

    @Test
    public void testInvalidMobile() {
        given()
            .header("Content-Type", "application/json")
            .body("{\"mobile\": \"123\"}") // invalid
        .when()
            .post("/Logincheckuser")
        .then()
            .statusCode(400) // or whatever your CI3 returns
            .body("status", equalTo("error"));
    }
}
