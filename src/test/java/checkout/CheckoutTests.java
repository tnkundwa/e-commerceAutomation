package checkout;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.CartPage;
import pages.CheckoutPage;
import pages.MensPage;

public class CheckoutTests extends BaseTests{

    @Test
    public void testCheckout(){
        MensPage men = homePage.clickMenLink();
        men.clickAddToCart(3, 2);
        window().goBack();
        CartPage cart = homePage.clickCart();
        CheckoutPage checkout = cart.clickCheckout();
        checkout.enterFirstName("Rasta");
        checkout.enterLastName("Man");
        checkout.entercompanyName("The gym");
        checkout.enterCountry("Rwanda");
        checkout.enterStreetAddress("Kk 240 st");
        checkout.enterStreetAddress2("Apartmentations");
        checkout.enterCityName("Kigali");
        checkout.enterState("Kayonza");
        checkout.enterZIPcode("0000");
        checkout.enterPhone("+250799999999");  
        checkout.enterEmail("tnkundwa9@gmail.com");  
        checkout.enterOrderComment("Ambo sasa I have a killer dog");  
        checkout.clickPlaceOrder();
        assertEquals("wrong!", "Checkout", checkout.getTitle());
        assertEquals("wrong!", "Thank you. Your order has been received.", checkout.getText());
    }
}
