package apitests;

import apilib.FootballApiConfig;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JwayVideoGameTests extends FootballApiConfig {

    @Test
    public void getAllGames(){
        String jsonRespone = given().when().get("competitions/2021/teams").asString();
        Map<String, ?> rootElement =JsonPath.read(jsonRespone, "$");
        System.out.println(rootElement.toString());
    }
    @Test
    public void getTotalteams(){
        String jsonResponse = given().when().get("competitions/2021/teams").asString();
        int count = JsonPath.read(jsonResponse,"$.count");
        System.out.println(count);
    }
    @Test
    public void printAllteams(){
        String jsonRespone = given().when().get("competitions/2021/teams").asString();
        List<LinkedHashMap<String, ?>> rootElement =JsonPath.read(jsonRespone, "$.teams");
        rootElement.stream().forEach(System.out::println);
    }
    @Test
    public void getFirstElement(){
        String jsonRespone = given().when().get("competitions/2021/teams").asString();
        Map<String, ?> firstElement =JsonPath.read(jsonRespone, "$.teams[0]");
        System.out.println(firstElement.toString());
    }
    @Test
    public void getLastElement(){
        String jsonRespone = given().when().get("competitions/2021/teams").asString();
        Map<String, ?> lastElement =JsonPath.read(jsonRespone, "$.teams[-1]");
        System.out.println(lastElement.toString());
    }
    @Test
    public void getSpecificName(){
        String jsonRespone = given().when().get("competitions/2021/teams").asString();
        ArrayList<String> name=JsonPath.read(jsonRespone, "$.teams[*].name");
        System.out.println(name.toString());
    }
    @Test
    public void getAllIds(){
        String jsonRespone = given().when().get("competitions/2021/teams").asString();
        ArrayList<String> id=JsonPath.read(jsonRespone, "$..[*].id");
        System.out.println(id.toString());
    }
    @Test
    public void getAllIdsUnderTeams(){
        String jsonRespone = given().when().get("competitions/2021/teams").asString();
        ArrayList<String> id=JsonPath.read(jsonRespone, "$.teams[*].id");
        System.out.println(id.toString());
    }
    @Test
    public void getAllTeamswhoseIdLessThan300(){
        String jsonRespone = given().when().get("competitions/2021/teams").asString();
        ArrayList<String> id=JsonPath.read(jsonRespone, "$.teams[?(@.id<300)].name");
        id.stream().forEach(System.out::println);
    }
}
