import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.ShoppingBagPage;

public class OrderSummaryTests {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @Test
    void checkTitleTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseWomenCategory();
        ShoppingBagPage shoppingBagPage = mainPage.addFirstItemToBag();

        shoppingBagPage.checkTitle("Shopping Bag");
    }
}
