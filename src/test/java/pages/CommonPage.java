package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CommonPage {

    public CommonPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(linkText = "Admin")
    public WebElement adminTab;

    @FindBy(linkText = "PIM")
    public WebElement pimTab;

    @FindBy(linkText = "Leave")
    public WebElement leaveTab;

    @FindBy(linkText = "Time")
    public WebElement timeTab;

    @FindBy(linkText = "Recruitment")
    public WebElement recruitmentTab;

    @FindBy(linkText = "My Info")
    public WebElement myInfoTab;

    @FindBy(linkText = "Performance")
    public WebElement performanceTab;

    @FindBy(linkText = "Dashboard")
    public WebElement dashboardTab;

    @FindBy(linkText = "Directory")
    public WebElement directoryTab;

    @FindBy(linkText = "Maintenance")
    public WebElement maintenanceTab;

    @FindBy(linkText = "Claim")
    public WebElement claimTab;

    @FindBy(linkText = "Buzz")
    public WebElement buzzTab;

    public void tabClick() {

    }




}
