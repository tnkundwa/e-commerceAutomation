package home;

import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTests;

public class HomePageTests extends BaseTests{

    @Test
    public void testQuickLinks(){
        String text = homePage.quickLinkText(5);
        String hold = homePage.getTitlePage(5);
        assertEquals("no", hold, text);
    }

}
