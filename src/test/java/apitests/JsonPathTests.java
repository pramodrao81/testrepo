package apitests;

import apilib.FootballApiConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class JsonPathTests extends FootballApiConfig {

    @Test
    public void getCount(){
        Response response = given().when().get("competitions/2021/teams").then().extract().response();
        int count = response.path("count");
        System.out.println(count);

    }
    @Test
    public void getFirstTeamName(){
        Response response = given().when().get("competitions/2021/teams").then().extract().response();
        String name = response.path("teams[0].name");
        System.out.println(name);

    }
    @Test
    public void getFirstTeamNameArea(){
        Response response = given().when().get("competitions/2021/teams").then().extract().response();
        String nameArea = response.path("teams[0].area.name");
        System.out.println(nameArea);
    }
    @Test
    public void getDetailsFromId(){
        Response response = given().when().get("competitions/2021/teams").then().extract().response();
        Map<String,?> details= response.path("teams.find{it.id==57}");
        System.out.println(details);
    }
    @Test
    public void getEmailAddress(){
        Response response = given().when().get("competitions/2021/teams").then().extract().response();
        String address= response.path("teams.find{it.id==57}.email");
        System.out.println(address);
    }
}
