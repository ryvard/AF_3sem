/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import io.restassured.RestAssured;

import io.restassured.parsing.Parser;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

/**
 *
 * @author Jmach
 */
public class FlightsIntegrationTest {

    public FlightsIntegrationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("BeforeClass");
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8084;
        RestAssured.basePath = "/AF_3sem";
        RestAssured.defaultParser = Parser.JSON;

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of flightsParam method, of class Flights.
     */
    @Test
    public void serverIsRunning() {
        given().
                when().get("http://localhost:8084/AF_3sem/").
                then().
                statusCode(200);
    }

    @Test
    public void invalidUrl() {
        given().when().get("http://localhost:8084/AF_3sem/api/flight/BCN/2017-01-18/1")
                .then().statusCode(404);

    }

    @Test
    public void noContentFromParameter() {
        given().when().get("http://localhost:8084/AF_3sem/api/flights/xxx/2017-01-18/1")
                .then().statusCode(204);

    }

    @Test
    public void containsAirlineName() {
        given().when().get("http://localhost:8084/AF_3sem/api/flights/BCN/2017-01-18/1").then()
                .body(containsString("AngularJS Airline"));

    }

    @Test
    public void verifyNameOfAirline() {
        given().when().get("http://localhost:8084/AF_3sem/api/flights/BCN/2017-01-18/1").then()
                .body("airline", equalTo("AngularJS Airline"));
    }
    
    @Test
    public void addReservationStatus200() {
        given().when().get("http://localhost:8084/AF_3sem/api/flights/reservation").then()
                .statusCode(200);
    }
    
    @Test
    public void addReservationAssertTrue() {
        
        String json = "{\"airline\":\"Adventure flights\","
                + "\"flightID\":\"100\","
                + "\"flightNumber\":\"100\","
                + "\"origin\":\"CPH\","
                + "\"date\":\"18-02-2017\","
                + "\"traveltime\":\"1\","
                + "\"flightTime\":\"20.00\","
                + "\"seats\":3,"
                + "\"totalPrice\":\"200\","
                + "\"firstName\":\"Emma\","
                + "\"lastName\":\"Blomsterberg\"}";
        
        given().contentType("application/json").body(json)
                .when().post("/api/flights/reservation")
                .then().statusCode(200);
    }

}
