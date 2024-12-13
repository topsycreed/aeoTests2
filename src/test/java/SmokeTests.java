import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static constants.Constants.BASE_URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SmokeTests {
    WebDriver driver;

    @BeforeEach
    void setup() {
        System.out.println("Browser started");
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Browser closed");
        driver.quit();
    }

    @Test
    void openPageTest() {
        //actions
        driver.get(BASE_URL);

        //assertions
        System.out.println(driver.getCurrentUrl());
        Assertions.assertEquals(BASE_URL + "us/en", driver.getCurrentUrl());
    }

    @Test
    void closePopupTest() throws InterruptedException {
        //actions
        driver.get("https://www.ae.com/");

        Thread.sleep(5000);
        //1st
        //driver.findElement(By.xpath("//button[@id = 'onetrust-accept-btn-handler']")).click();
        //2st
        WebElement acceptButton = driver.findElement(By.xpath("//button[@id = 'onetrust-accept-btn-handler']"));
        //String buttonText = acceptButton.getText();
        acceptButton.click();
    }
}