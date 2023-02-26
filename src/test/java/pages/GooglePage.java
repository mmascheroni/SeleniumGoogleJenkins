package pages;

public class GooglePage extends BasePage {

    private String url = "https://www.google.com";
    private String criteria = "Google";
    private String inputSearch = "//input[@title='Buscar']";
    private String searchButton = "//input[@value='Buscar con Google']";

    public GooglePage() {
        super(driver);
    }

    public void navigateToGoogle() {
        navigateTo(url);
    }

    public void enterSearchCriteria() {
        enterKeys(inputSearch, criteria);
    }

    public void search() {
        clickElement(searchButton);
    }

}
