package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
    public WebDriver driver;
    public By cartIcon = By.className("ast-cart-menu-wrap");
    public By quickLinks = By.cssSelector(("#menu-quick-links a"));
    public By titlePages = By.cssSelector((".wp-block-cover__inner-container h1"));
    public By titleForMenandWomen = By.cssSelector(".woocommerce-products-header h1");
    public By forHerLinks = By.cssSelector("#menu-for-her a");
    public By forHimLinks = By.cssSelector("#menu-for-him a");
    
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public MensPage clickStoreLink(){
        clickLink("Store");
        return new MensPage(driver);
    }

    public MensPage clickMenLink(){
        clickLink("Men");
        return new MensPage(driver);
    }
    public CartPage clickCart(){
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
    }
    public AccountsPage clickAccount(){
        clickLink("Account");
        return new AccountsPage(driver);
    }
    public MensPage clickWomenLink(){
        clickLink("Women");
        return new MensPage(driver);
    }
    public MensPage clickAccessoriesLink(){
        clickLink("Accessories");
        return new MensPage(driver);
    }
    public AboutUsPage clickAboutLink(){
        clickLink("About");
        return new AboutUsPage(driver);
    }
    public ContactUsPage clickContactLink(){
        clickLink("Contact Us");
        return new ContactUsPage(driver);
    }
    public void addToCart(int index, int frequency){
        MensPage men = new MensPage(driver);
        men.clickAddToCart(index, frequency);
    }
    public String quickLinkText(int index){
        String specificLinkText = driver.findElements(quickLinks).get(index - 1).getText();
        return specificLinkText;
    }
    public String getTitlePage(int index){
        driver.findElements(quickLinks).get(index - 1).click();
        return driver.findElement(titlePages).getText();
    }
    public String forHerLinkText(int index){
        String specificLinkText = driver.findElements(forHerLinks).get(index - 1).getText();
        return specificLinkText;
    }
    public String getForHerTitle(int index){
        driver.findElements(forHerLinks).get(index - 1).click();
        return driver.findElement(titleForMenandWomen).getText();
    }
    public String forHimLinkText(int index){
        String specificLinkText = driver.findElements(forHimLinks).get(index - 1).getText();
        return specificLinkText;
    }
    public String getForHimTitle(int index){
        driver.findElements(forHimLinks).get(index - 1).click();
        return driver.findElement(titleForMenandWomen).getText();
    }

    private void clickLink(String link){
        driver.findElement(By.linkText(link)).click();
    }
}
