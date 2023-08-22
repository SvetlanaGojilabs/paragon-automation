package tests;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import config.DriverConfig;
import pages.loginscreens.LoginScreen;

import java.time.Duration;

@Listeners(TestNGListener.class)
public class LoginTests extends DriverConfig {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log = LogManager.getLogger(this.getClass());

    /**
     * initializes driver
     */
    @BeforeTest
    public void setup() {
        driver = initializeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * testNG data provider for tests
     */
    @DataProvider(name = "loginData")
    public Object[][] provideLoginData() {
        return new Object[][] {
                {"invalid@gojilabs.com", "password", "Wrong email or password", "Invalid Login"},
                {"", "", "Enter valid email", "Empty Fields Login"},
                {"nikita@gojilabs.com", "password", "", "Successful Login"}
        };
    }


    /**
     * launches home page URL
     *
     */
    @BeforeMethod
    public void launchURL() {
        String url = getUrl("url");
        log.info("Launching URL: " + url);
        driver.get(url);
    }

    @Feature("Login")
    @Description("Login with invalid data, empty data and valid data")
    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedMessage, String testName) {

        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.login(username, password);

        if (!expectedMessage.isEmpty()) {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MuiTypography-p3medium")));
            Assert.assertEquals(errorMessage.getText(), expectedMessage);
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.css-10l5cj1[href='/clients']")));
            Assert.assertFalse(driver.getCurrentUrl().contains("login"));
            Assert.assertTrue(driver.getCurrentUrl().equals("https://staging-app.sswmeetings.com/"));

        }
        log.info("Test Case: " + testName + " - Passed");
    }


        /**
         * closes driver
         */
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            log.debug("End of Test. Closing driver" + System.lineSeparator());
            driver.close();
        }
    }

}
