package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class TimePage {

    public TimePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
