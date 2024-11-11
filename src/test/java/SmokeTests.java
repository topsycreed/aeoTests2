import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SmokeTests {

    @Test
    public void firstTest() {
        System.out.println("Hello ");
        System.out.println("Hello1 ");
        System.out.println("Hello2 ");
    }

    @Test
    void openPageTest() {
        //setup or prerequisites
        WebDriver driver = new ChromeDriver();

        //actions
        driver.get("https://www.ae.com/");

        //assertions
        System.out.println(driver.getCurrentUrl());
        Assertions.assertEquals("https://www.ae.com/us/en", driver.getCurrentUrl());
    }
}