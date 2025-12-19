package products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.*;
import java.util.*;

public class ProductsTests extends BaseTests{

    @Test
    public void testProducts(){
        MensPage men = homePage.clickMenLink();
        List<String> hold = men.allProductNames();
        int index = 3;
        ProductPage product = men.clickOnImage(index);
        assertEquals("Wrong Product!", hold.get(index - 1), product.getTitle());
    }

    @Test
    public void testReviewLink(){
        MensPage men = homePage.clickMenLink();
        ProductPage product = men.clickOnImage(3);
        product.clickReviewsLink();
        assertTrue("Wrong link!", product.reviewsTitle().contains("Reviews"));
        assertEquals("Wrong text", "There are no reviews yet.", product.noReviews());
    }

    @Test
    public void testReviewNotSignedIn(){
        AccountsPage account = homePage.clickAccount();
        account.enterEmail("tnkundwa");
        account.entePassword("tnkundwa");
        account.clickLogin();
        window().goBack();
        MensPage men = homePage.clickMenLink();
        ProductPage product = men.clickOnImage(3);
        product.clickReviewsLink();
        product.leaveStar(3);
        String comment = "Man it’s so hard";
        product.leaveComment(comment);
        product.enterName("Kisekiseee");
        product.enterEmail("agasobanuye@gmail.com");
        product.clickSubmit();
        assertEquals("Not working!", comment, product.getReviewLeft());
    }
    
    @Test
    public void testReview(){
        AccountsPage account = homePage.clickAccount();
        account.enterEmail("tnkundwa");
        account.entePassword("tnkundwa");
        account.clickLogin();
        window().goBack();
        MensPage men = homePage.clickMenLink();
        ProductPage product = men.clickOnImage(3);
        product.clickReviewsLink();
        product.leaveStar(3);
        String comment = "Man it’s so hard";
        product.leaveComment(comment);
        product.enterName("Kisekiseee");
        product.enterEmail("agasobanuye@gmail.com");
        product.clickSubmit();
        assertEquals("Not working!", "Duplicate comment detected; it looks as though you’ve already said that!", product.sameCommentText());
    }
}
