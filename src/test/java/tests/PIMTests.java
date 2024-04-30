package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CommonPage;
import pages.LoginPage;
import pages.PIMPage;
import utilities.Driver;
import utilities.SeleniumUtils;

public class PIMTests {

    LoginPage loginPage = new LoginPage();
    PIMPage pimPage = new PIMPage();
    CommonPage commonPage = new CommonPage();
    WebDriver driver = Driver.getDriver();

    @BeforeEach
    public void startPoint() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.login("Admin", "admin123");
        commonPage.pimTab.click();
    }

    @Test
    public void verifyAdminCanCreateEmployee() {

        pimPage.addEmployee();

        String expectedName = pimPage.fName + " " + pimPage.lName;

        SeleniumUtils.verifyTextInElement(driver, 20, pimPage.fullNameText, expectedName);
        commonPage.pimTab.click();

//        WebElement empIdInTable = driver.findElement(By.xpath("//div[text()='" + pimPage.employeeId + "']"));
//        WebElement firstNameInTable = driver.findElement(By.xpath("//div[text()='" + pimPage.employeeId + "']/following-sibling::div[1]"));
//        WebElement lastNameInTable = driver.findElement(By.xpath("//div[text()='" + pimPage.employeeId + "']/following-sibling::div[2]"));
//
//        SeleniumUtils.verifyTextInElement(driver, 20, empIdInTable, pimPage.employeeId);
//        SeleniumUtils.verifyTextInElement(driver, 20, firstNameInTable, pimPage.fName);
//        SeleniumUtils.verifyTextInElement(driver, 20, lastNameInTable, pimPage.lName);


//        pimPage.getListOfEmpId();
        System.out.println(pimPage.listOfEmpID.size());
    }

    @AfterEach
    public void endPoint() {

    }
}
