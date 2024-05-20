package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GooglePage;

public class GoogleSteps {
    private WebDriver driver;
    ChromeOptions options;

    @Before
    public void setUp() {
        options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*"); // Nueva opción agregada
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        GooglePage googlePage = new GooglePage(driver, null);
        googlePage.setup();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the Google search page")
    public void navigateToGoogle() {
        GooglePage googlePage = new GooglePage(driver, null);
        googlePage.navigateToGoogle();

    }

    @When("I enter a search criteria")
    public void searchCriteria() {
        GooglePage googlePage = new GooglePage(driver, null);
        googlePage.enterSearchCriteria();
    }

    @And("Click on the search button")
    public void clickSearchButton() {
        GooglePage googlePage = new GooglePage(driver, null);
        googlePage.search();
    }

    @Then("The results match the criteria")
    public void compareResults() {
        GooglePage googlePage = new GooglePage(driver, null);
        googlePage.verifyFirstSearchTitle();
    }
}
