import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.SharedSteps;

import java.time.Duration;
import java.util.List;

import static constants.Constants.BASE_URL;

public class RegressionTests {
    WebDriver driver;
    SharedSteps sharedSteps;

    @BeforeEach
    void setup() {
        System.out.println("Browser started");
        driver = new ChromeDriver();
        sharedSteps = new SharedSteps(driver);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Browser closed");
        String html = driver.getPageSource();
        driver.quit();
    }

    @Test
    void openWomenCategoryTest() throws InterruptedException {
        //pre-requisites
        sharedSteps.openSiteWithAccept();
        Thread.sleep(2000);

        //actions
        WebElement womenCategory = driver.findElement(By.xpath("//a[@data-text = 'Women']"));
        new Actions(driver)
                .moveToElement(womenCategory)
                .perform();
        Thread.sleep(1000);
        WebElement viewAll = driver.findElement(By.xpath("//a[@href = '/us/en/c/women/womens?pagetype=clp']"));
        viewAll.click();
        Thread.sleep(1000);
        sharedSteps.closeBlackFriday();

        //assertions
        Assertions.assertEquals(BASE_URL + "us/en/c/women/womens?pagetype=clp", driver.getCurrentUrl());
    }

    @Test
    void addItemToBagTest() throws InterruptedException {
        //pre-requisites
        sharedSteps.openSiteWithAccept();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //actions
        WebElement womenCategory = driver.findElement(By.xpath("//a[@data-text = 'Women']"));
        new Actions(driver)
                .moveToElement(womenCategory)
                .perform();
        WebElement viewAll = longWait.
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href = '/us/en/c/women/womens?pagetype=clp']")));
        viewAll.click();
        List<WebElement> products = driver.findElements(By.xpath("//div[@data-product-id]"));
        Assertions.assertTrue(products.size() >= 10);
        WebElement firstProduct = products.get(0);
        new Actions(driver)
                .moveToElement(firstProduct)
                .perform();
        WebElement firstProductQuickShopButton = firstProduct.findElement(By.xpath("//a[text() = 'Quick Shop']"));
        firstProductQuickShopButton.click();
        driver.findElement(By.xpath("//h2[text() = 'Quick Shop']"));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@data-test-select-size]")).click();
        List<WebElement> allSizes = driver.findElements(By.xpath("//li[@data-value]"));
        for (WebElement size : allSizes) {
            if (!size.getAttribute("class").contains("visually-disabled")) {
                size.click();
                break;
            }
        }
        Thread.sleep(10000);
        try {
            WebElement parentForShadowRoot = driver.findElement(By.xpath("//div"));
            SearchContext shadowRoot = parentForShadowRoot.getShadowRoot();
            shadowRoot.findElement(By.cssSelector("button.close")).click();
        } catch (Exception ignore) {}
        driver.findElement(By.xpath("//button[@name = 'add-to-bag']")).click();
        WebElement title = longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-test-sidetray = 'added-to-bag']//h2")));

        //assertions
        Assertions.assertEquals("Added to bag!", title.getText());
    }

    @Test
    void openMenCategoryTest() throws InterruptedException {
        sharedSteps.openSiteWithAccept();

        WebElement womenCategory = driver.findElement(By.xpath("//a[@data-text = 'Men']"));
        new Actions(driver)
                .moveToElement(womenCategory)
                .perform();
        WebElement viewAll = new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//a[@href = '/us/en/c/men/mens?pagetype=clp']")));
        viewAll.click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//h1[contains(text(), 'Men')]")));
        sharedSteps.closeBlackFriday();

        Assertions.assertEquals(BASE_URL + "us/en/c/men/mens?pagetype=clp", driver.getCurrentUrl());
    }
}
