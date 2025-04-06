package suharik.SQA;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class SearchClass {
    FirefoxDriver driver;
    @BeforeClass
    public void SetUp()
    {
        System.out.println("SetUp with Web_Driver");
        System.setProperty("webdriver.firefox.driver", "D:\\NewProjects\\IdeaProjects\\Test_Alikhan_IT-2303\\lib\\selenium-firefox-driver-4.30.0.jar");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openWookieePedia()
    {
        driver.get("https://starwars.fandom.com/wiki/Main_Page");
        String expectedTitle = "Wookieepedia | Fandom";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Wrong title");
    }
    @AfterMethod
    public void afterOpenTest(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println(result.getName() + " PASSED");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println(result.getName() + " FAILED");
        }
    }

    @Test
    public void searchAhsokaTano()
    {
        driver.get("https://starwars.fandom.com/wiki/Main_Page");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.className("search-app__input")));
            searchField.sendKeys("Ahsoka Tano");
            searchField.sendKeys(Keys.RETURN);

            List<WebElement> resultLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".unified-search__result__link")));

            // Iterate over the results and find the one that matches "Ahsoka Tano"
            // Iterate through the results and find the one that matches "Ahsoka Tano"
            for (WebElement resultLink : resultLinks) {
                String resultText = resultLink.getText().trim();

                if (resultText.contains("Ahsoka Tano")) {  // You can refine this to match exactly "Ahsoka Tano"
                    // Scroll to the result element before clicking it
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resultLink);

                    // Wait for the element to be clickable and click it
                    wait.until(ExpectedConditions.elementToBeClickable(resultLink)).click();

                    String pageTitle = driver.getTitle();
                    Assert.assertTrue(pageTitle.contains("Ahsoka"), "Title does not contain 'Ahsoka Tano'");

                    break;
                }
            }


        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for element: " + e.getMessage());
        }
    }

    @Test
    public void wrongSearch()
    {
        driver.get("https://starwars.fandom.com/wiki/Main_Page");
        boolean testPassed = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.className("search-app__input")));
            searchField.sendKeys("Qwedfhty");
            searchField.sendKeys(Keys.RETURN);

            List<WebElement> resultLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".unified-search__result__link")));
            if(resultLinks.size() > 0)
            {
                System.out.println("Results found, The test case failed");
                testPassed = false;
            }
            else {
                testPassed = true;
            }


        } catch (NoSuchElementException e) {
            testPassed = true;
            System.out.println("Element not found: Test case passed");
        } catch (TimeoutException e) {
            testPassed = true;
            System.out.println("Timeout waiting for element: " + e.getMessage());
        }

        Assert.assertTrue(testPassed, "Wrong search result");

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
