package com.instacart.crawler.tests;

import com.instacart.config.EndPoints;
import com.instacart.config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiCrawlerTest extends TestConfig {

    private static Logger Log = LogManager.getLogger(ApiCrawlerTest.class.getName());
    private String dayMonthYear;


    @BeforeTest
    public void setup() {
        SimpleDateFormat sDFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dayMonthYear = sDFormat.format(date);

    }

    @Test
    public void verifyResponseCodeForRetrieveCatalogAction() {
        Log.info("Verify the Response code for the catalog retrieve request");
        given()
                .spec(RetrieveCatalogRequestSpecificationWithoutAuth)
                .log()
                .ifValidationFails()
                .when().post(EndPoints.POST_REQUEST)
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .all();
    }


    @Test
    public void verifyResponseCodeForRetrieveBasicInfoAction() {
        Log.info("Verify the Response code for the basic info retrieve request");
        given()
                .spec(RetrieveBasicInfoRequestSpecificationWithoutAuth)
                .log()
                .ifValidationFails()
                .when().post(EndPoints.POST_REQUEST)
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void verifyErrorCodeForRetrieveBasicInfoAction() {
        Log.info("Verify the error code for the catalog retrieve request");
        given()
                .spec(RetrieveBasicInfoRequestSpecificationWithoutAuthForError)
                .log()
                .ifValidationFails()
                .when().post(EndPoints.POST_REQUEST)
                .then()
                .assertThat()
                .statusCode(500)
                .log()
                .all();
    }

    @Test
    public void verifyResponseBodyForRetrieveCatalogAction() {
        Log.info("Verify the Response body for the catalog retrieve request");
        Response response = given()
                .spec(RetrieveCatalogRequestSpecificationWithoutAuth)
                .log()
                .ifValidationFails()
                .when().post(EndPoints.POST_REQUEST)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        List<String> newlist = JsonPath.with(response.asInputStream()).get("$");
        Assert.assertEquals(newlist.get(0).toString(),"Kroger Large Eggs");
    }


}