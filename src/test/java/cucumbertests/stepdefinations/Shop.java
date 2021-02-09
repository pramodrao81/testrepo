package cucumbertests.stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Shop {

	
	@And("I search for shirts")
	public void i_search() {
		System.out.println("I search for shirts");
	}
	
	@When("I add a shirt in cart")
	public void i_add() {
		System.out.println("I add a shirt in cart");
	}
	
	@Then("The cart item count should increase")
	public void cart_item() {
		System.out.println("The cart item count should increase");
		//assertThat("my home pageX").isEqualTo("my home page");
		//assertThat(isElementPresent("xxxxx")).isTrue();
		//fail("some reason to fail");
		
		// SoftAssertions
		
		
		
	}
}
