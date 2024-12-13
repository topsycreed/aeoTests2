package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SharedSteps {
    WebDriver driver;

    public SharedSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void openSiteWithAccept() throws InterruptedException {
        driver.get("https://www.ae.com/");
        //driver.manage().window().fullscreen();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id = 'onetrust-accept-btn-handler']"))).click();
    }

    public void closeBlackFriday() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> blackFridayClose = driver.findElements(By.xpath("//button[@aria-label = 'Close']"));
        if (blackFridayClose.isEmpty()) {
            System.out.println("BlackFriday popup not appeared after 5 seconds");
        } else {
            blackFridayClose.get(0).click();
        }
    }
}
