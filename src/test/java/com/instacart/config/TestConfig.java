package com.instacart.config;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;


import static com.instacart.utils.Properties.*;
import static com.instacart.utils.PropertiesUtils.getProperty;
//import static io.restassured.matcher.RestAssuredMatchers.*;

public class TestConfig {


    public static RequestSpecification RequestSpecification;
    public static  RequestSpecification RetrieveCatalogRequestSpecificationWithoutAuth;
    public static  RequestSpecification RetrieveBasicInfoRequestSpecificationWithoutAuth;
    public static  RequestSpecification RetrieveBasicInfoRequestSpecificationWithoutAuthForError;


    @BeforeClass
    public void setUp() {

        /* sample code when authorization is introduced
        PreemptiveBasicAuthScheme authForAPIService = new PreemptiveBasicAuthScheme();
        authForAPIService.setUserName(getProperty(TEST_AUTHREADAPIUSERNAME));
        authForAPIService.setPassword(getProperty(TEST_AUTHREADAPIPASSWORD));*/



        /*sample code when authorization is introduced
        RequestSpecification = new RequestSpecBuilder()
                .setBaseUri("")
                .setBaseUri(getProperty(TEST_BASEURI))
                .setBasePath(getProperty(TEST_BASEPATHREADAPI))
                .addHeader("Content-Type", "application/json")
                .setRelaxedHTTPSValidation()
                .setAuth(authForAPIService)
                .build();*/

        RetrieveCatalogRequestSpecificationWithoutAuth = new RequestSpecBuilder()
                .setBaseUri(getProperty(TEST_BASEURI))
                .setContentType("multipart/form-data")
                .addMultiPart("store",getProperty(TEST_STORE))
                .addMultiPart("session_cookie",getProperty(TEST_COOKIE))
                .addMultiPart("action","retrieve-catalog")
                .setRelaxedHTTPSValidation()
                .build();

        RetrieveBasicInfoRequestSpecificationWithoutAuth = new RequestSpecBuilder()
                .setBaseUri(getProperty(TEST_BASEURI))
                .setContentType("multipart/form-data")
                .addMultiPart("store",getProperty(TEST_STORE))
                .addMultiPart("session_cookie",getProperty(TEST_COOKIE))
                .addMultiPart("action","retrieve-basic-info")
                .setRelaxedHTTPSValidation()
                .build();


        RetrieveBasicInfoRequestSpecificationWithoutAuthForError = new RequestSpecBuilder()
                .setBaseUri(getProperty(TEST_BASEURI))
                .setContentType("multipart/form-data")
                .addMultiPart("store",getProperty(TEST_STORE))
                .addMultiPart("session_cookie","GarbageValue")
                .addMultiPart("action","retrieve-basic-info")
                .setRelaxedHTTPSValidation()
                .build();
    }
}

