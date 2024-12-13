package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {
    WebDriver driver;

    @FindBy(xpath = "//h1[contains(@class, 'qa-page-header')]")
    WebElement title;

    Footer footer;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        footer = new Footer(driver);
        PageFactory.initElements(driver, this);
    }

    public void checkTitle(String expextedTitle) {
        Assertions.assertEquals(expextedTitle, title.getText());
    }

    public ShoppingBagPage addFirstItemToBag() {
        // need to implement
        return new ShoppingBagPage(driver);
    }
}
