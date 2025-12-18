package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {
    private WebDriver driver;
    private By pageTitle = By.cssSelector(".wp-block-cover__inner-container h1");

    public ContactUsPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(){
        return driver.findElement(pageTitle).getText();
    }
}
