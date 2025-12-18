package aboutUs;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.AboutUsPage;

public class AboutUsTests extends BaseTests{

    @Test
    public void testAbout(){
        AboutUsPage aboutUs = homePage.clickAboutLink();
        assertEquals("Wrong page!", "About Us", aboutUs.getTitle());
    }

}
