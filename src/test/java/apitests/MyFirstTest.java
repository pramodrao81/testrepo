package apitests;

import apilib.VideoGameConfig;
import apilib.VideoGameEndpoints;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;


public class MyFirstTest extends VideoGameConfig {

    @Test
    public void myFirstTest(){
        given()
                .log().all().
        when().get("videogames").
        then()
                .log().all();
    }

    @Test
    public void myFirstTestWithEndpoints(){
        get(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then().log().all();
    }
}
