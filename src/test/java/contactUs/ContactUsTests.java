package contactUs;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.ContactUsPage;

public class ContactUsTests extends BaseTests{

    @Test
    public void testAbout(){
        ContactUsPage contactUs = homePage.clickContactLink();
        assertEquals("Wrong page!", "About Us", contactUs.getTitle());
    }
}
