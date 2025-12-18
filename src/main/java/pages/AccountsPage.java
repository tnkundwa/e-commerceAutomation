package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsPage {
    private WebDriver driver;
    private By username = By.id("username");
    private By passwordfield = By.id("password");
    private By loginBtn = By.name("login");
    private By title = By.tagName("h1");
    private By words = By.className("woocommerce-MyAccount-content");
    private By logOutLink = By.linkText("Log out");
    private By invalidData = By.cssSelector(".woocommerce-error li");

    public AccountsPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterEmail(String email){
        driver.findElement(username).sendKeys(email);
    }
    public void entePassword(String password){
        driver.findElement(passwordfield).sendKeys(password);
    }
    public void clickLogin(){
        driver.findElement(loginBtn).click();
    }
    public String pageTitle(){
        return driver.findElement(title).getText();
    }
    public String pageWords(){
        return driver.findElement(words).getText();
    }
    public void logOut(){
        driver.findElement(logOutLink).click();
    }
    public String invalidDataAlert(){
        return driver.findElement(invalidData).getText();
    }

}
