package apitests;

import apilib.FootballApiConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FootballApiTests extends FootballApiConfig {

    @Test
    public void getDetailsofOneArea(){
        given()
                .queryParam("areas", "2255").
        when()
                .get("areas");
       }

    @Test
    public void getDateFounded(){
        given().
        when()
               .get("teams/57").
        then()
               .body("founded", equalTo(1886));
    }
    @Test
    public void getTeamName(){
        given().
        when()
               .get("competitions/2021/teams").
        then().
                body("teams.name[2]",equalTo("Chelsea FC"));
    }
    @Test
    public void getAllTeamData(){
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }
    @Test
    public void getAllTeamData_DoCheckFirst(){
        Response response =
                given().
                when()
                        .get("teams/57").
                then()
                        .contentType(ContentType.JSON)
                        .extract().response();
        String responseString = response.asString();
        System.out.println(responseString);
    }
    @Test
    public void getHeaders(){
        Response response =
                given().
                        when()
                       .get("teams/57").
                        then()
                       .contentType(ContentType.JSON)
                       .extract().response();
       Headers headers = response.getHeaders();
       String contentType = response.getHeader("Content-Type");
       System.out.println(contentType);
    }

    @Test
    public void getFirstTeamName(){
        String firstTeamName = get("competitions/2021/teams").jsonPath().getString("teams.name[0]");
        System.out.println(firstTeamName);
    }

    @Test
    public void extratcAllTeamNames(){
        Response response =
                given().
                        when().get("competitions/2021/teams").
                        then().extract().response();

        List<String> teamNames = response.path("teams.name");
        int counter =1;
        for(String teamName : teamNames){
            System.out.println(counter+"."+teamName);
            counter++;
        }
    }

}
