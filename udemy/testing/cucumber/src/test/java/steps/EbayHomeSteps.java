package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EbayHomeSteps {

	// Steps are used in Scenarios and run the actual tests.
	// Should be self-explanatory (in conjunction with the feature files).

	@Given("I am on Ebay Home Page")
	public void i_am_on_ebay_home_page() {
		System.out.println("I am on Ebay Home Page");
	}

	@When("I click on Advanced Link")
	public void i_click_on_advanced_link() {
		System.out.println("I click on Advanced Link");
	}

	@Then("I navigate to Advanced Search page")
	public void i_navigate_to_advanced_search_page() {
		System.out.println("I navigate to Advanced Search page");
	}
}
