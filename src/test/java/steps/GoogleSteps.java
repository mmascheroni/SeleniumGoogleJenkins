package steps;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Ejecutar en modo headless
        // options.addArguments("--no-sandbox"); // Deshabilitar el sandbox
        // options.addArguments("--disable-dev-shm-usage"); // Deshabilitar el uso de memoria compartida de dev

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));

        GooglePage googlePage = new GooglePage(driver, wait);
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
