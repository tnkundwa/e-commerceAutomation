package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    private WebDriver driver;
    private By firstName = By.id("billing_first_name");
    private By lastName = By.id("billing_last_name");
    private By companyName = By.id("billing_company");
    private By countrySelect = By.id("billing_country");
    private By streetAddress = By.id("billing_address_1");
    private By streetAddress2 = By.id("billing_address_2");
    private By city = By.id("billing_city");
    private By stateSelect = By.name("billing_state");
    private By ZIPcode = By.id("billing_postcode");
    private By phone = By.id("billing_phone");
    private By email = By.id("billing_email");
    private By orderComments = By.id("order_comments");
    private By placeOrderBtn = By.id("place_order");

    private By pageTitle = By.tagName("h1");
    private By orderReceived = By.xpath(".//p[contains(text(), 'Thank you')]");
    private By theWholeDiv = By.className("woocommerce-billing-fields__field-wrapper");
    // private By commonInput = By.cssSelector("span.woocommerce-input-wrapper > input");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterFirstName(String name){
        driver.findElement(firstName).sendKeys(name);
    }
    public void enterLastName(String name){
        driver.findElement(lastName).sendKeys(name);
    }
    public void entercompanyName(String CompanyName){
        driver.findElement(companyName).sendKeys(CompanyName);
    }
    public void enterCountry(String countryName){
        getSelector().selectByVisibleText(countryName);
    }
    public void enterStreetAddress(String StreetName){
        driver.findElement(streetAddress).sendKeys(StreetName);
    }
    public void enterStreetAddress2(String streetName2){
        driver.findElement(streetAddress2).sendKeys(streetName2);
    }
    public void enterCityName(String cityName){
        driver.findElement(city).sendKeys(cityName);
    }
    public void enterState(String stateName){
        driver.findElement(stateSelect).sendKeys(stateName);
    }
    public void enterZIPcode(String zip){
        driver.findElement(ZIPcode).sendKeys(zip);
    }
    public void enterPhone(String phoneNmbr){
        driver.findElement(phone).sendKeys(phoneNmbr);
    }
    public void enterEmail(String emailDomain){
        driver.findElement(email).sendKeys(emailDomain);
    }
    public void enterOrderComment(String comment){
        WebElement hold = driver.findElement(orderComments);
        hold.sendKeys(comment);
    }
    // public void waitForEverything(String name){
    //     driver.findElement(firstName).findElement(By.cssSelector("span.woocommerce-input-wrapper > input"));
    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    //     wait.until(ExpectedConditions.attributeContains(firstName, "value", name));
    // }
    public void clickPlaceOrder(){
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(ExpectedConditions.elementToBeClickable(driver.findElement(placeOrderBtn)));
        driver.findElement(placeOrderBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(theWholeDiv));
    }
    public Select getSelector(){
        return new Select(driver.findElement(countrySelect));
    }

    public String getTitle(){
        return driver.findElement(pageTitle).getText();
    }
    public String getText(){
        return driver.findElement(orderReceived).getText();
    }
}
