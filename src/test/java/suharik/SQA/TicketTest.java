package suharik.SQA;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TicketTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void SetUp()
    {
        System.out.println("SetUp with Web_Driver");
        System.setProperty("webdriver.firefox.driver", "D:\\NewProjects\\IdeaProjects\\Test_Alikhan_IT-2303\\lib\\selenium-firefox-driver-4.30.0.jar");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void testFlightSearchOnAviasales() throws InterruptedException {
        driver.get("https://www.aviasales.kz");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));



        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Откуда']")));
        fromInput.click();
        fromInput.sendKeys("Астана");
        Thread.sleep(1000);
        fromInput.sendKeys(Keys.ENTER);

        WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Куда']")));
        toInput.click();
        toInput.sendKeys("Актобе");
        Thread.sleep(1000);
        toInput.sendKeys(Keys.ENTER);

        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.s__baueeRnAUu_J55n12MRS:nth-child(1)")));
        dateInput.click();

        WebElement day21 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".boundedFrom")));
        day21.click();

        WebElement day22 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.s__QIpl4HSgk6PrStnNFAwQ:nth-child(1) > div:nth-child(3) > div:nth-child(4) > div:nth-child(2)")));
        day22.click();
//
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='form-submit']")));
        search.click();




        Thread.sleep(2500);
        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertTrue(driver.getTitle().contains("Астана"), "not contain");
    }

    @AfterClass
    public void tearDown()
    {
        System.out.println("Tear down with Web_Driver");
        if(driver != null)
        {
            driver.quit();
        }
    }
}
