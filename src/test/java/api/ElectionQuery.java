package api;

import base.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.ElectionRoot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ElectionQuery extends BaseTest {

    private final String endpoint = "/elections";

    @BeforeMethod
    public void setup() throws IOException {
        initialize();
    }

    @Test
    public void validateValidStatus(){
        Response response = httpRequest.request(Method.GET, getApiEndpoint(endpoint));
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code is invalid!");
    }

    @Test
    public void validateResponseBody() throws JsonProcessingException {
        Response response = httpRequest.request(Method.GET, getApiEndpoint(endpoint));
        String responseBody = response.body().asString();
        ElectionRoot electionRoot = new ObjectMapper().readValue(responseBody, ElectionRoot.class);
        Assert.assertTrue(electionRoot.getKind().contains("electionsQueryResponse"));
        Assert.assertTrue(electionRoot.getElections().size() > 0);
    }

}
