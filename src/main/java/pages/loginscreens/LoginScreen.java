package pages.loginscreens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.CommonFunctions;

import java.time.Duration;

public class LoginScreen {
    CommonFunctions functions;
    private WebDriver driver;
    private Logger log = LogManager.getLogger(this.getClass());


    // Locators
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By continueWithEmailButton = By.className("css-1bovbrj");

    public LoginScreen(WebDriver driver) {
        this.driver = driver;
    }

    public LoginScreen setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        return this;
    }
    public LoginScreen setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginScreen clickLoginButton() {
        driver.findElement(continueWithEmailButton).click();
        return this;
    }

    public void login(String email, String password) {
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.setEmail(email);
        log.info("Email set: " + email);
        loginScreen.setPassword(password);
        log.info("Password set: " + email);
        loginScreen.clickLoginButton();
    }
}