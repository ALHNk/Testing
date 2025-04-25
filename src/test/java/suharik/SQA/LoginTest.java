package suharik.SQA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    @BeforeClass
    public void SetUp()
    {
        System.out.println("SetUp with Web_Driver");
        System.setProperty("webdriver.firefox.driver", "D:\\NewProjects\\IdeaProjects\\Test_Alikhan_IT-2303\\lib\\selenium-firefox-driver-4.30.0.jar");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginTest()
    {
        driver.get("https://nodedatabase.onrender.com/users");
        WebElement userField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        userField.sendKeys("alikhan12");
        passwordField.sendKeys("Alikhan12!");
        passwordField.submit();
//        driver.findElement(By.id("submit")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleIs("Dashboard"));;
        String title = driver.getTitle();
        Assert.assertEquals(title, "Dashboard", "Not logged in");
    }

    @Test
    public void wrongLoginTest()
    {
        driver.get("https://nodedatabase.onrender.com/users");
        WebElement userField = driver.findElement(By.id("name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        userField.sendKeys("");
        passwordField.sendKeys("12w");
        passwordField.submit();
//        driver.findElement(By.id("submit")).click();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Login", "SomeHow Logged in");
    }
    @Test
    public void logoutTest()
    {
        driver.get("https://nodedatabase.onrender.com/dashboard");
        driver.findElement(By.id("logout")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleIs("Login"));;

        String title = driver.getTitle();
        Assert.assertEquals(title, "Login", "Not logged out");
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
