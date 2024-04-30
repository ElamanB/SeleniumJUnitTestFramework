package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LeavePage {

    public LeavePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
