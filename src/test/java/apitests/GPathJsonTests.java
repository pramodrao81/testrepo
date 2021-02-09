package apitests;

import apilib.FootballApiConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJsonTests extends FootballApiConfig {

    @Test
    public void extractMapOfElementsWithFind(){
        Response response = get("competitions/2021/teams");
        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find {it.name=='Chelsea FC'}");
        System.out.println(allTeamDataForSingleTeam);
    }
    @Test
    public void extractSingleValueWithFind(){
        Response response = get("/teams/57");
        String certainPlayer = response.path("squad.find {it.shirtNumber == 10}.name");
        System.out.println(certainPlayer);
    }
    @Test
    public void extractSingleValueWithFindAll(){
        Response response = get("/teams/61");
        List<String> playerNames = response.path("squad.findAll {it.position == 'Midfielder'}.name");
        System.out.println(playerNames);
    }
    @Test
    public void extractSingleValueWithHighestNumber(){
        Response response = get("/teams/61");
        String playerName = response.path("squad.max{it.id}.name");
        System.out.println(playerName);
    }
    @Test
    public void extractSingleValueWithSmallestNumber(){
        Response response = get("/teams/61");
        String playerName = response.path("squad.min{it.id}.name");
        System.out.println(playerName);
    }
    @Test
    public void extractMultiplevaluesSumthem(){
        Response response = get("/teams/61");
        int sumOfId = response.path("squad.collect{it.id}.sum()");
        System.out.println(sumOfId);
    }
    @Test
    public void extractMapOfObjectsWithFindAndFindAll(){
        Response response = get("/teams/61");
        Map <String, ?> player = response.path("squad.findAll{it.position=='Defender'}.find{it.nationality=='England'}");
        System.out.println(player);
    }
    @Test
    public void extractMapOfObjectsWithFindAndFindAllWithParameters(){
        String position = "Defender";
        String nationality = "England";
        Response response = get("/teams/61");
        Map <String, ?> player = response.path("squad.findAll{it.position=='%s'}.find{it.nationality=='%s'}",position, nationality);
        System.out.println(player);
    }
    @Test
    public void extractMultiplePlayersWithParameters() {
        String position = "Midfielder";
        String nationality = "England";
        Response response = get("/teams/61");
        ArrayList<Map<String, ?>> allplayers = response.path("squad.findAll{it.position=='%s'}.findAll{it.nationality=='%s'}", position, nationality);
        System.out.println(allplayers);
    }
}
