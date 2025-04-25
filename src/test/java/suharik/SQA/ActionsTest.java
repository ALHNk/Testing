package suharik.SQA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActionsTest{
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://demo.automationtesting.in/Static.html");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"node\"]")));
        WebElement destiny = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"droparea\"]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(element, destiny).build().perform();
    }
//    @Test
//    public void black()  {
//        driver.get("https://alhnk.github.io/Donernaya/codes/orders.html?");
//        Actions actions = new Actions(driver);
//        actions.keyUp("CTRL");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////        actions.keyUp("CTRL");
////        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
