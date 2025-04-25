package suharik.SQA;


import com.aventstack.extentreports.*;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;

public class UniversalLoginTester {

    private FirefoxDriver driver;
    private WebDriverWait wait;
    private final Logger logger = Logger.getLogger(UniversalLoginTester.class.getName());
    private static ExtentTest test;
    private static ExtentReports report;
    private static ExtentSparkReporter sparkReporter;
//    private static ExtentHtmlReporter htmlReporter;


    public String takeScreenShot(String testName) {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String destPath = "Screenshots/" + testName + System.currentTimeMillis() + ".png";
        File dest = new File(destPath);
        try{
            dest.getParentFile().mkdirs();
            java.nio.file.Files.copy(srcFile.toPath(),dest.toPath());
        }catch(Exception e){
            logger.warning("Could not save screenshot: " + e.getMessage());
        }
        return destPath;
    }

    @BeforeSuite
    public void beforeSuite() {
        logger.info("Starting universal login test");
        report = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("report.html");
        report.attachReporter(sparkReporter);
        test = report.createTest("Universal Login Test");

    }
    @BeforeClass
    public void setUp() {
        logger.info("Before class");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @BeforeTest
    public void beforeOpenTest() {
        logger.info("Before test");
//        report.setSystemInfo("Host", driver.getCurrentUrl());
//        report.setSystemInfo("Browser", driver.getCapabilities().getBrowserName());
    }
    @Test(priority = 1)
    @Parameters("url")
    public void openPage(String url) throws IOException {
        driver.get(url);
        if(driver.getTitle().equals("Fast-food online")) {
            test.log(Status.PASS, "Opened url: " + url);
        }
        else {
            test.log(Status.FAIL, "Opened url: " + url);
        }
        String screenShot = takeScreenShot("openPage");
        test.addScreenCaptureFromPath(screenShot);
    }
    @AfterTest
    public void afterOpenTest() {
        logger.info("After test");

    }
    @BeforeTest
    public void beforeRegisterTest() {
        logger.info("Before Register");
    }
    @Test(priority = 2)
    @Parameters({"register_url", "first_name", "password", "email", "city"})
    public void registerTest(String register_url, String first_name, String password, String email, String City) throws IOException {
        logger.info("Register");
        driver.get(register_url);
        WebElement name_input = driver.findElement(By.id("validationDefault01"));
        name_input.sendKeys(first_name);
        WebElement password_input = driver.findElement(By.id("passid"));
        password_input.sendKeys(password);
        WebElement email_input = driver.findElement(By.id("sobakaid"));
        email_input.sendKeys(email);
        WebElement city_input = driver.findElement(By.id("validationDefault03"));
        city_input.sendKeys(City);
        WebElement check = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-check-label")));
        check.click();
        WebElement submit_button = driver.findElement(By.cssSelector("div.col-12:nth-child(6) > button:nth-child(1)"));
        submit_button.click();

        if(driver.getTitle().equals("login")) {
            test.log(Status.PASS, "Registered");
        }
        else {
            test.log(Status.FAIL, "Registered");
        }
        String screenShot = takeScreenShot("register");
        test.addScreenCaptureFromPath(screenShot);
        Assert.assertTrue(driver.getTitle().equals("login"), "Registration Failed");
    }
    @AfterTest
    public void afterRegisterTest() {
        logger.info("After Register");
    }
//    @BeforeMethod
//    public void beforeLoginMethod() {
//        logger.info();("Before Method");
//    }
//    @AfterMethod
//    public void afterLoginMethod() {
//        logger.info();("After Method");
//    }

    @BeforeTest
    public void beforeLoginTest() {
        logger.info("Before Test");
    }

    @Test(priority = 3)
    @Parameters({"login_url","email", "password"})
    public void loginTest(String login_url, String email, String password) throws IOException {
        logger.info("Login");
        driver.get(login_url);
        WebElement email_input = driver.findElement(By.id("emailInput"));
        WebElement password_input = driver.findElement(By.id("passwordInput"));
        email_input.sendKeys(email);
        password_input.sendKeys(password);
        WebElement submit_button = driver.findElement(By.cssSelector("#loginForm > button:nth-child(3)"));
        if (driver.getTitle().equals("Fast-food online")) {
            test.log(Status.PASS, "Login");
        }
        else {
            test.log(Status.FAIL, "Login");
        }
        String screenShot = takeScreenShot("register");
        test.addScreenCaptureFromPath(screenShot);
        Assert.assertTrue(driver.getTitle().equals("Fast-food online"), "Login Failed");
    }
    @AfterTest
    public void afterLoginTest() {
        logger.info("After Login");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
        logger.info("After Class");
        report.flush();
    }
}
