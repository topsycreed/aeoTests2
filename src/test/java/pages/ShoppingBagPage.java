package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingBagPage {
    WebDriver driver;

    @FindBy(xpath = "//h1[contains(@class, 'qa-page-header')]")
    WebElement title;

    public ShoppingBagPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ShoppingBagPage open() {
        driver.get("https://www.ae.com/us/en/cart");
        return new ShoppingBagPage(driver);
    }

    public void checkTitle(String expextedTitle) {
        Assertions.assertEquals(expextedTitle, title.getText());
    }
}
