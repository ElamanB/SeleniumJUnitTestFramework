package pages;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

public class PIMPage {

    public PIMPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
    public WebElement addEmployeeButton;

    @FindBy(name = "firstName")
    public WebElement firstNameInput;

    @FindBy(name = "lastName")
    public WebElement lastNameInput;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    public WebElement employeeIDInput;

    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    public WebElement saveButton;

    @FindBy(css = "h6[class='oxd-text oxd-text--h6 --strong']")
    public WebElement fullNameText;


    @FindBy(xpath = "//div[text()='Id']/following-sibling::div")
    public List<WebElement> listOfEmpID;

    @FindBy(css = "span[class='oxd-text oxd-text--span']")
    public WebElement textRecordsFound;

    Faker faker = new Faker();

    public String fName = faker.name().firstName();
    public String lName = faker.name().lastName();
    public String employeeId = faker.number().digits(2);


    public void addEmployee() {

        addEmployeeButton.click();
        firstNameInput.sendKeys(fName);
        lastNameInput.sendKeys(lName);
        employeeIDInput.sendKeys(employeeId);
        saveButton.click();

    }

    public void getListOfEmpId() {
        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath
                ("//div[text()='Id']/following-sibling::div")));
        System.out.println(listOfEmpID.size());
    }





}
