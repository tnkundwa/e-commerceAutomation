package pages;

import java.time.Duration;
import java.util.List;
// import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private By cartItemRows = By.cssSelector("tr.woocommerce-cart-form__cart-item.cart_item");
    private By productName = By.cssSelector("td.product-name");
    private By productQuantity = By.cssSelector("td.product-quantity input[type='number']");
    private By productSubtotal = By.cssSelector("td.product-subtotal");
    private By productPrice = By.cssSelector("td.product-price");

    private By productRemove = By.cssSelector("td.product-remove a");
    private By confirmRemoveText = By.xpath(".//div[contains(text(), 'removed')]");
    private By undoBtn = By.xpath(".//a[text() = 'Undo?']");

    private By updateCartBtn = By.xpath(".//button[text() = 'Update cart']");
    private By confirmUpdateAlert = By.className("woocommerce-message");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public int getProductName(String name){
        List<WebElement> cartRows = driver.findElements(cartItemRows);
        String quantityValue = "";
        for (WebElement row : cartRows) {
            WebElement nameElement = row.findElement(productName);
            String cartItemName = nameElement.getText().trim();
            if (cartItemName.equals(name)){
                WebElement quantityInput = row.findElement(productQuantity);
                quantityValue = quantityInput.getAttribute("value");
            }
        }
        return Integer.parseInt(quantityValue);     
    }

    public String removeProduct(int index){
        String itemName = driver.findElements(cartItemRows).get(index - 1).findElement(productName).getText();
        driver.findElements(productRemove).get(index - 1).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(undoBtn));
        return itemName;
    }
    public String removeText(){
        return driver.findElement(confirmRemoveText).getText();
    }
    public void clickUndo(){
        driver.findElement(undoBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(undoBtn));
    }
    public boolean isElementBack(String text){
        return driver.findElements(productName).stream().map(e -> e.getText()).anyMatch(item -> item.contains(text));
    }



    public void adjustQuantityIncrease(int index, int increase){
        WebElement quantityInput = driver.findElements(cartItemRows).get(index - 1).findElement(productQuantity);
        for (int i = 0; i < increase; i++){
            quantityInput.sendKeys(Keys.UP);
        }
    }
    public void adjustQuantityDecrease(int index, int decrease){
        WebElement quantityInput = driver.findElements(cartItemRows).get(index - 1).findElement(productQuantity);
        for (int i = 0; i < decrease; i++){
            quantityInput.sendKeys(Keys.DOWN);
        }
    }

    public void clearQuantity(int index, int num){
        WebElement quantityField = driver.findElements(cartItemRows).get(index - 1).findElement(productQuantity);
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(num));
    }
    public void addToQuantity(int index, int num){
        driver.findElements(cartItemRows).get(index - 1).findElement(productQuantity).sendKeys(String.valueOf(num));
    }

    public double getSetQuantity(int index){
        return Double.parseDouble(driver.findElements(cartItemRows).get(index - 1).findElement(productQuantity).getAttribute("value"));
    }

    public double getSubTotal(int index){
        return Double.parseDouble(driver.findElements(cartItemRows).get(index - 1).findElement(productSubtotal).getText().substring(1));
    }
    public double getProductPrice(int index){
        return Double.parseDouble(driver.findElements(cartItemRows).get(index - 1).findElement(productPrice).getText().substring(1));
    }

    public void clickUpdateCartBtn(){
        driver.findElement(updateCartBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmUpdateAlert));
    }
    public CheckoutPage clickCheckout(){
        driver.findElement(By.cssSelector(".wc-proceed-to-checkout a")).click();
        return new CheckoutPage(driver);
    }
}
