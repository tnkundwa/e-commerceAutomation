package cart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.*;

public class CartTests extends BaseTests{

    @Test
    public void testDelet(){
        MensPage men = homePage.clickMenLink();
        men.clickAddToCart(3, 1);
        window().goBack();
        CartPage cart = homePage.clickCart();
        String hold = cart.removeProduct(1);
        assertTrue("Wrong text!", cart.removeText().contains(hold));
    }
    
    @Test
    public void testUndo(){
        MensPage men = homePage.clickMenLink();
        men.clickAddToCart(3, 1);
        window().goBack();
        CartPage cart = homePage.clickCart();
        String hold = cart.removeProduct(1);
        cart.clickUndo();
        assertTrue("Not working!", cart.isElementBack(hold));
    }

    @Test
    public void workQuantity(){
        MensPage men = homePage.clickMenLink();
        men.clickAddToCart(3, 1);
        window().goBack();
        CartPage cart = homePage.clickCart();
        cart.adjustQuantityIncrease(1, 5);
        cart.adjustQuantityDecrease(1, 2);
        // cart.clearQuantity(1, 14);
        // cart.addToQuantity(1, 2);
        cart.clickUpdateCartBtn();
        double hold = (cart.getProductPrice(1) * cart.getSetQuantity(1));
        assertEquals("Not so fast", cart.getSubTotal(1), hold, 0.001);
    }
}
