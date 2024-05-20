package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage extends BasePage {

    public GooglePage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    private String url = "https://www.google.com";
    private String criteria = "Selenium";
    private String inputSearch = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea";
    private String searchButton = "//input[@value='Buscar con Google']";
    private String firstTitle = "//*[@id=\"rso\"]/div[1]/div/div/div/div/div/div/div/div[1]/div/span/a/h3";

    public void navigateToGoogle() {
        navigateTo(url);
    }

    public void enterSearchCriteria() {
        enterKeys(inputSearch, criteria);
    }

    public void search() {
        clickElement(searchButton);
    }

    public void verifyFirstSearchTitle() {
        verifyText(firstTitle, criteria);
    }

}
