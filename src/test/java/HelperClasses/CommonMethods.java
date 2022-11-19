package HelperClasses;

import groovy.json.JsonException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CommonMethods {

    Map<String, String> getRequestHeaders;
    Map<String, String> getRequestParameters;
    public static HashMap<String, String> headers;
    HashMap<String, String> values;
    ResultSet resultSet;

    public CommonMethods() {

        getRequestHeaders = new HashMap<>();
        getRequestParameters = new HashMap<>();

        values = new HashMap<>();
        headers = new HashMap<>();
        headers.put("content-type", "application/json");

    }


    /**
     * common method to get the size of the object in the response
     *
     * @param response:     response object
     * @param responsePath: JSON path of the object whose size needs to be fetched
     * @return size of the object
     */
    public static int getJSONResponseSize(Response response, String responsePath) {
        return response.body().path(responsePath + ".size()");
    }


    public static void verifyNull(String actual, String message) {

        try {
            Assert.assertNull(message, actual);
        } catch (AssertionError e) {
            throw e;
        }

    }


    public static void verifyNotNull(Object actual, String message) {
        try {
            Assert.assertNotNull(actual, message);
        } catch (AssertionError e) {
            throw e;
        }

    }


    public static void verifyEquals(Object actual, Object expected, String message) {

        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            throw e;
        }
    }


    public static void verifyNotEquals(Object actual, Object unexpected, String message) {
        try {
            Assert.assertNotEquals(unexpected, actual, message);
        } catch (AssertionError e) {
            throw e;
        }

    }

    public static void verifyTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
        } catch (AssertionError e) {
            throw e;
        }

    }

}