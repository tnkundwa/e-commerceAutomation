package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class ProductPage {
    private WebDriver driver;
    private By title = By.tagName("h1");
    private By description = By.id("tab-title-description");
    private By additionalInfo = By.id("tab-title-additional_information");
    private By reviews = By.id("tab-title-reviews");
    private By tableInAdditionalLink = By.cssSelector("table th");
    private By comments = By.id("comments");
    private By stars = By.cssSelector(".stars a");
    private By commentField = By.name(("comment"));
    private By submitBtn = By.id("submit");
    private By commentLeft = By.className("comment_container");
    private By reviewLeft = By.cssSelector(".description p");
    private By author = By.name("author");
    private By emails = By.name("email");

    private By addToCartBtn = By.xpath(".//button[text() = 'Add to cart']");
    public By addToCartText = By.className("woocommerce-message");
    private By sameComment = By.cssSelector(".wp-die-message p");

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(){
        return driver.findElement(title).getText();
    }
    public void clickdescriptionLink(){
        driver.findElement(description).click();
    }
    public void clickAdditionalInfoLink(){
        driver.findElement(additionalInfo).click();
    }
    public void clickReviewsLink(){
        driver.findElement(reviews).click();
    }
    public List<String> tableInAdditionalLink(){
        return driver.findElements(tableInAdditionalLink).stream().map(e -> e.getText()).collect(Collectors.toList());
    }
    public String reviewsTitle(){
        return driver.findElement(comments).findElement(By.tagName("h2")).getText();
    }
    public String noReviews(){
        return driver.findElement(comments).findElement(By.tagName("p")).getText();
    }
    public void leaveStar(int numOfStars){
        driver.findElements(stars).get(numOfStars - 1).click();
    }
    public void leaveComment(String comment){
        driver.findElement(commentField).sendKeys(comment);
    }
    public void enterName(String name){
        if(!driver.findElements(author).isEmpty()){
            driver.findElement(author).sendKeys(name);
        }
    }
    public void enterEmail(String email){
        if(!driver.findElements(emails).isEmpty()){
            driver.findElement(emails).sendKeys(email);
        }
    }
    public void clickSubmit(){
        driver.findElement(submitBtn).click();
        if (!driver.findElements(sameComment).isEmpty()){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(sameComment));
        } else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(commentLeft));
        }
    }
    public String getReviewLeft(){
        return driver.findElement(reviewLeft).getText();
    }
    public String getTextAlert(){
        driver.findElement(submitBtn).click();
        return driver.switchTo().alert().getText();
    }


    public void addToCartBtn(){
        driver.findElement(addToCartBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(addToCartText));
    }
    public String getAlertTextBtn(){
        return driver.findElement(addToCartText).getText();
    }
    public String sameCommentText(){
        return driver.findElement(sameComment).getText();
    }

}
