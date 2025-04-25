package suharik.SQA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WaitTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void SetUp()
    {
        System.out.println("SetUp with Web_Driver");
        System.setProperty("webdriver.firefox.driver", "D:\\NewProjects\\IdeaProjects\\Test_Alikhan_IT-2303\\lib\\selenium-firefox-driver-4.30.0.jar");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void Wait() throws InterruptedException {
        driver.get("https://alhnk.github.io/Donernaya/codes/index.html");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn:nth-child(2)")));
        menuButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement discounts = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.navButtons:nth-child(5)")));
        discounts.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.animated-button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        Thread.sleep(500);
        Actions actions = new Actions(driver);
        actions.moveToElement(submit).click().perform();

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.alertIsPresent());

    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
