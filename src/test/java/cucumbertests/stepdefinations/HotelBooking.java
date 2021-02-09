package cucumbertests.stepdefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.util.List;
import java.util.Map;

public class HotelBooking {
    SoftAssertions softAssert;
    @Before
    public void beforeScenario() {
        softAssert = new SoftAssertions();
        System.out.println("----------------Before executing------------------");
    }

    @After
    public void afterScenario() {
        //softAssert.assertAll();
        System.out.println("----------------After executing-------------------");
    }

    @Given("^I live in ([a-zA-Z]{1,})$")
    public void i_live(String cityName) {
        softAssert.assertThat("A").isEqualTo("B");//fail
        System.out.println("I live in "+cityName);
    }

    @And("^I want to go on a holiday$")
    public void i_want() {
        System.out.println("I want to go on a holiday");
    }

    @And("^We are (\\d+) adults$")
    public void we_are(int adults,List<Map<String,String>> names) {
        softAssert.assertThat(false).isTrue();

        System.out.println("We are "+adults+" adults "+ names.toString());
    }

    @And("^We want to book from ([^\"]*) to ([^\"]*)$")
    public void we_want_to_book(String fromDate, String toDate) {
        //assertThat("A".equals("")).isTrue();
        //assertThat("A").isEqualTo("B");
        //fail("failure");
        System.out.println("x");


        System.out.println("We want to book from "+fromDate+" to "+toDate);
    }

    @When("^I go to travel agent$")
    public void i_go() {
        System.out.println("I go to travel agent");
    }

    @Then("^He should be able to help me in a budget of (\\d+) USD$")
    public void he_should(int budget) {
        System.out.println("He should be able to help me in a budget of "+budget+" USD");
    }

    @And("^He should provide me option to cancel$")
    public void he_should_provide() {
        System.out.println("He should provide me option to cancel");
    }

    @But("^He should not ask for advance more than (\\d+) USD$")
    public void he_should_not(int advance) {
        System.out.println("He should not ask for advance more than "+advance+" USD");
    }

    @And("^We want to book flight to ([a-zA-Z]{1,}) on ([^\"]*)$")
    public void want_flight(String destination , String fromDate) {
        System.out.println("We want to book flight to "+destination+" on "+fromDate);
    }

    @And("^Return Flight on ([^\"]*)$")
    public void returnFlight(String returnDate) {
        System.out.println("Return Flight on "+returnDate);
    }

    @Given("^I go to website www.ebay.com$")
    public void i_goto() {
        System.out.println("I go to website www.ebay.com");
    }
}
