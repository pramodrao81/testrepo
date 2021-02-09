package apilib;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import util.datasource.ReadProperties;

public class FootballApiConfig {
    public static RequestSpecification football_requestSpec;
    public static ResponseSpecification football_responseSpec;
    @BeforeMethod
    public static void setup(){
        football_requestSpec = new RequestSpecBuilder()
                .setBaseUri(ReadProperties.readProperty("footballEndPoint"))
                .setBasePath("/v2")
                .addHeader("X-Auth-Token","ece91e3504824573b998f74de82dab38")
                .addHeader("X-Response-Control", "minified")
          /*      .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())*/
                .build();

        football_responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

                RestAssured.requestSpecification=football_requestSpec;
                RestAssured.responseSpecification = football_responseSpec;

    }
}
