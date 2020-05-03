package com.neo.stepdefs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.neo.actions.BrowserActions;
import com.neo.base.CommonPage;
import com.neo.drivers.DriverManager;
import com.neo.utilities.ExtentReportUtility;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class CommonSteps extends CommonPage{
	
	@FindBy(className = "login")
	private WebElement signInLink;

	@FindBy(id = "email")
	private WebElement emailTxtField;

	@FindBy(id = "passwd")
	private WebElement passwdTxtField;
	
	@FindBy(name = "SubmitLogin")
	private WebElement submitLoginBtn;

		
	public void decorator() {
		PageFactory.initElements(DriverManager.get().getDriver(), this);
	}
	
	@Before
	public void beforeTest(Scenario scenario) {
	scenarioName = scenario.getName();
	ExtentReportUtility.get().createTest(scenario.getName());
	}
	
	@After
	public void aftertest() {
		BrowserActions.quitApplication();
	}
	
	@Given("The {string} launch the application")
	public void the_launch_the_application(String dataObject) {
		JSONObj = dataObject;
		initiateDriver();
		actions.LaunchApplication(getProperty("appURL"));
	}
	
	
	@When("the user log in to the application")
	public void the_user_log_in_to_the_application() {
		decorator();
		actions.click(signInLink, "Sign In Link");
		actions.sendKeys(emailTxtField, "Email text field", getTestJSONValue("email"));
		actions.sendKeys(passwdTxtField, "Password field", getTestJSONValue("password"));
		actions.click(submitLoginBtn, "Login Button");

	}
}
