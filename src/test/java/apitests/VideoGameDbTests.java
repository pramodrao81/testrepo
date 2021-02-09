package apitests;

import apiclass.VideoGame;
import apilib.VideoGameConfig;
import apilib.VideoGameEndpoints;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.*;

public class VideoGameDbTests extends VideoGameConfig {

    @Test
    public void getAllGames(){
        given().
                when().get(VideoGameEndpoints.ALL_VIDEO_GAMES).
                then();
    }
    @Test
    public void createNewGameByJson(){
        String gameBodyJson="{\n"
                                    + "        \"id\": 11,\n"
                                    + "        \"name\": \"EA Cricket 2000\",\n"
                                    + "        \"releaseDate\": \"1994-09-11\",\n"
                                    + "        \"reviewScore\": 88,\n"
                                    + "        \"category\": \"Sports\",\n"
                                    + "        \"rating\": \"Universal\"\n"
                                    + "    }";
        given()
                .body(gameBodyJson).
        when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES).
        then();
    }

    @Test
    public void createNewGameByXml(){
        String gameBodyXml=" <videoGame category=\"Shooter\" rating=\"Universal\">\n"
                                     + "    <id>12</id>\n"
                                     + "    <name>Resident Evil 5</name>\n"
                                     + "    <releaseDate>2005-10-01T00:00:00+05:30</releaseDate>\n"
                                     + "    <reviewScore>89</reviewScore>\n"
                                     + "  </videoGame>";
        given()
                .body(gameBodyXml)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml").
        when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES).
        then();

    }
    @Test
    public void updateGame(){
        String gameBodyJson="{\n"
                                    + "        \"id\": 11,\n"
                                    + "        \"name\": \"EA Cricket 2021\",\n"
                                    + "        \"releaseDate\": \"1994-09-11\",\n"
                                    + "        \"reviewScore\": 92,\n"
                                    + "        \"category\": \"Sports\",\n"
                                    + "        \"rating\": \"Universal\"\n"
                                    + "    }";
        given()
                .body(gameBodyJson).
        when()
                .put("videogames/11").
        then();
    }
    @Test
    public void deleteGame(){
       given()
       .when()
                .delete("videogames/12").
        then();
    }
    @Test
    public void getSingleGame(){
        given()
                .pathParam("videoGameId", 5).
        when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES).
        then();
    }
    @Test
    public void testVideoGameSerializationJson(){
        VideoGame videoGame = new VideoGame("90", "2020-04-04", "FIFA 21", "90", "13", "Sports");
          given().
                        body(videoGame).
                when().
                        post(VideoGameEndpoints.ALL_VIDEO_GAMES).
                then();
    }
    @Test
    public void testVideoGameSchemaXML(){
        given()
                .pathParam("videoGameId", 5)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml").
        when().
                get(VideoGameEndpoints.SINGLE_VIDEO_GAMES).
        then()
                .body(matchesXsdInClasspath("videoGameXSD.xsd"));
    }
    @Test
    public void testVideoGameSchemaJson(){
        given()
                .pathParam("videoGameId", 5).
        when().
                 get(VideoGameEndpoints.SINGLE_VIDEO_GAMES).
        then().
                 body(matchesJsonSchemaInClasspath("videoGameJsonSchema.json"));
    }

    @Test
    public void convertJSONtoPojo(){
        Response response =
                given()
                        .pathParam("videoGameId", 5).
                when()
                        .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES);
        VideoGame videoGame = response.getBody().as(VideoGame.class);
        System.out.println(videoGame);
    }
    @Test
    public void captureResponseTime(){
        long responseTime = get(VideoGameEndpoints.ALL_VIDEO_GAMES).time();
        System.out.println("Response Time in MS->"+responseTime);
    }
    @Test
    public void assertOnTime(){
        when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES).
        then().
                time(lessThan(1000L));


    }

}
