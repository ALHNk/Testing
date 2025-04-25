package suharik.SQA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SelectTest {
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
    public void selectTest()
    {
        driver.get("https://alhnk.github.io/Donernaya/codes/orders.html?");
        Select select = new Select(driver.findElement(By.id("resSel")));
        select.selectByVisibleText("Bahandi");
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
