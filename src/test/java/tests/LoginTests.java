package tests;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utilities.Driver;

public class LoginTests {

    WebDriver driver;
    LoginPage loginPage;


    @BeforeEach
    public void before() {
        driver = Driver.getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage();
    }


    @Test
    public void testSuccessfulLogin() {

        loginPage.login("Admin", "admin123");
        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));

    }

    @Test
    public void testUnsuccessfulLogin() {
        loginPage.login("Admin123", "admin");
        Assertions.assertTrue(loginPage.invalidLoginMessage.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        // Optionally clear session data or navigate back if needed
        driver.manage().deleteAllCookies(); // Clears cookies to reset session
    }



}
