package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public static final Properties config = new Properties();
    protected static RequestSpecification httpRequest;

    public static void initialize() throws IOException {
        config.load(new FileInputStream("src/main/java/config/api-config.properties"));
        RestAssured.baseURI = config.getProperty("CIVI_BASE_URL");
        httpRequest = RestAssured.given();
    }

    protected String getApiEndpoint(String endpoint){
        return endpoint + "?key="+config.getProperty("API_KEY");
    }

}
