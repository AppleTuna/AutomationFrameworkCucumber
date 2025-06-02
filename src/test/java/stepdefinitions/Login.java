package stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtils;



public class Login {
	
	WebDriver driver;
	private LoginPage loginPage;
	private AccountPage accountPage;
	private CommonUtils commonUtils;

	@Given("User navigates to login page")
	public void User_navigates_to_login_page() {
		
		driver = DriverFactory.getDriver();
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
		
	}
	
	@When("^User enters valid email address (.+) into email field$")
	public void user_enters_valid_email_address(String emailText) {
		 
		loginPage.enterEmailAddress(emailText);
		
	}

	@And("^User enters valid password (.+) into password field$")
	public void user_enters_valid_password(String passwordText) {
		
		loginPage.enterPassword(passwordText);
		
	}
	

	@And("User clicks on Login button")
	public void user_clicks_on_login_button() {
		
		accountPage = loginPage.clickOnLoginButton();
			
	}

	@Then("User should get successfully logged in")
	public void user_should_get_successfully_logged_in() {
		
		Assert.assertTrue(accountPage.displayStatusOfEditYourAccountInformationOption());

	}

	@When("User enters invalid email address into email field")
	public void user_enters_invalid_email_address_into_email_field() {
		
		commonUtils = new CommonUtils();
		loginPage.enterEmailAddress(commonUtils.getEmailWithTimeStamp());
		
	}

	@When("User enters invalid password {string} into password field")
	public void user_enters_invalid_password_into_password_field(String invalidPasswordText) {
		
		loginPage.enterPassword(invalidPasswordText);
		
	}

	@Then("User should get a proper warning message about credentials mismatch")
	public void user_should_get_a_proper_warning_message_about_credentials() {

		Assert.assertTrue(loginPage.getWarningMessageText().contains("Warning: No match for E-Mail Address and/or Password."));
		
	}


	@When("User doesnt enter any email address into email field")
	public void user_doesnt_enter_any_email_address_into_email_field() {
		
		loginPage.enterEmailAddress("");
		
	}

	@When("User doesnt enter password into password field")
	public void user_doesnt_enter_password_into_password_field() {

		loginPage.enterPassword("");
		
	}
		
}
