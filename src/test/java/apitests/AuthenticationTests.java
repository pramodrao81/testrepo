package apitests;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTests {

    @Test
    public void basicPreemptivetest(){
        given()
                .auth().preemptive().basic("username", "password").
        when().
                get("http://localhost:8080/someEndpoint");

    }
    @Test
    public void basicChallengetest(){
        given()
                .auth().basic("username", "password").
        when().
                get("http://localhost:8080/someEndpoint");

    }
    @Test
    public void oAuth1Test(){
        given()
                .auth().oauth("consumerKey","consumerSecret", "accessToken","secretToken").
        when().
                 get("http://localhost:8080/someEndpoint");

    }
    @Test
    public void oAuth2Test(){
        given()
                .auth().oauth2("accessToken").
        when().
                 get("http://localhost:8080/someEndpoint");

    }
    @Test
    public void relaxedHttpsTest(){
        given()
                .relaxedHTTPSValidation().
         when().
                 get("http://localhost:8080/someEndpoint");

    }
    @Test
    public void keyStoreTest(){
        given()
                .keyStore("/pathToKS", "password").
        when().
                get("http://localhost:8080/someEndpoint");

    }
}

