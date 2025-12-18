package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;
import java.util.stream.*;

public class MensPage {
    private WebDriver driver;
    private By itemCount = By.className("count");
    private By addToCartLinks = By.xpath(".//a[text() = 'Add to cart']");
    private By itemBox = By.cssSelector("li.product.type-product");
    public String clickedItemName = "";
    private By slider = By.className("ui-slider-handle");
    private By productsDisplayed = By.cssSelector("[class='price'] bdi");
    private By pricesCanceled = By.cssSelector("del bdi");
    private By filterBtn = By.xpath(".//button[text() = 'Filter']");
    private By filterResults = By.className("price_label");

    private By dropDown = By.id("product_cat");
    private By titleSelected = By.className("woocommerce-products-header__title");
    private By itemsDiplayed = By.cssSelector(".products li");

    private By searchInput = By.id("woocommerce-product-search-field-0");
    private By searchBtn = By.cssSelector(".woocommerce-product-search button");
    private By searchCategory = By.cssSelector(".astra-shop-summary-wrap .ast-woo-product-category");
    private By searchResult = By.cssSelector(".astra-shop-summary-wrap .woocommerce-loop-product__title");
    private By noResults = By.cssSelector(".woocommerce-info.woocommerce-no-products-found");

    
    private By imageDiv = By.cssSelector(".products img");
    private By productName = By.cssSelector(".astra-shop-summary-wrap h2");

    public MensPage(WebDriver driver){
        this.driver = driver;
    }

    //Adding to cart functionality
    public int getItemCountBefore(){
        return Integer.parseInt(driver.findElement(itemCount).getText().trim());
    }
    public void clickAddToCart(int index, int clickMore){
        int countBefore = getItemCountBefore();
        WebElement specificAddToCart = driver.findElements(itemBox).get(index - 1);
        clickedItemName = specificAddToCart.findElement(By.tagName("h2")).getText();
        for (int i = 0; i < clickMore; i++){
            int countAfter = countBefore + i + 1;
            specificAddToCart.findElement(addToCartLinks).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.textToBe(itemCount, String.valueOf(countAfter)));
        }
    }



    //Moving the price slider
    public void moveSlider(int sliderL, int sliderR){
        WebElement sliderOne = driver.findElements(slider).get(0);
        WebElement sliderTwo = driver.findElements(slider).get(1);
        for (int i = 0; i < sliderL; i++){
            sliderOne.sendKeys(Keys.ARROW_RIGHT);
        }
        for (int i = 0; i < sliderR; i++){
            sliderTwo.sendKeys(Keys.ARROW_LEFT);  
        }
    }
    public List<Double> getArray(){
        List<WebElement> allItems = driver.findElements(productsDisplayed);
        List<WebElement> allItemsCancled = driver.findElements(pricesCanceled);
        List<WebElement> actualPriceElements = allItems.stream()
        .filter(element -> !allItemsCancled.contains(element))
        .collect(Collectors.toList());
        List<Double> hold = actualPriceElements.stream().map(e -> Double.parseDouble(e.getText().substring(1))).collect(Collectors.toList());
        return hold;
    }
    public void clicktoFilter(){
        driver.findElement(filterBtn).click();
    }
    public int[] filterRange(){
        int[] filterScope = new int[2];
        filterScope[0] = Integer.parseInt(driver.findElement(filterResults).findElement(By.className("from")).getText().substring(1));
        filterScope[1] = Integer.parseInt(driver.findElement(filterResults).findElement(By.className("to")).getText().substring(1));
        return filterScope;
    }
    public Boolean PriceWithinRange(){
        List<Double> itemPrices = getArray();
        int[] priceRange = filterRange();
        for (int i = 0; i < itemPrices.size(); i++){
            if (itemPrices.get(i) < priceRange[0] || itemPrices.get(i) > priceRange[1]){
                return false;
            }
        }
        return true;
    }



    //Browsing by Categories
    public Select dropDown(){
        return new Select(driver.findElement(dropDown));
    }

    public void selectFromDropDown(String option){
        dropDown().selectByVisibleText(option);
    }
    public List<String> getSelectedOption(){
        List<WebElement> allOptions = dropDown().getAllSelectedOptions();
        return allOptions.stream().map(e -> e.getText()).collect(Collectors.toList());
    }
    public String getTitle(){
        String apostrophe = driver.findElement(titleSelected).getText();
        if (apostrophe.contains("'")){
            apostrophe = apostrophe.replace("'", "’");
        }
        return apostrophe;
    }
    public String getItemCount(){
        return String.valueOf(driver.findElements(itemsDiplayed).size());
    }

    public void enterTextToSearch(String text){
        driver.findElement(searchInput).sendKeys(text);
    }
    public void clickSearchBtn(){
        driver.findElement(searchBtn).click();
    }
    public boolean getSearchResults(String text){
        boolean works = true;
        List<String> allCategories = driver.findElements(searchCategory).stream().map(e -> e.getText()).collect(Collectors.toList());
        List<String> allSearchResults = driver.findElements(searchResult).stream().map(e -> e.getText()).collect(Collectors.toList());
        if (allCategories.stream().allMatch(item -> item.contains(text)) || allSearchResults.stream().allMatch(item -> item.contains(text))){
            works = true;
        } else {
            works = false;
        }
        return works;
    }
    public String invalidSearchResults(){
        return driver.findElement(noResults).getText();
    }


    //Clicking on Product
    public ProductPage clickOnImage(int index){
        driver.findElements(imageDiv).get(index - 1).click();
        return new ProductPage(driver);
    }
    public List<String> allProductNames(){
        return driver.findElements(productName).stream().map(e -> e.getText()).collect(Collectors.toList());
    }
}