package mens;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.MensPage;

public class MensTests extends BaseTests{

    // @Test
    // public void testMens(){
    //     MensPage men = homePage.clickMenLink();
    //     int hold = men.getItemCountBefore();
    //     int clickMore = 3;
    //     men.clickAddToCart(3, clickMore);
    //     assertEquals("Wrong number!", 0, hold);
    //     assertEquals("Wrong number!", clickMore, men.getItemCountBefore());
    // }

    // @Test
    // public void testSlider(){
    //     MensPage men = homePage.clickMenLink();
    //     men.moveSlider(2, 1);
    //     men.clicktoFilter();
    //     assertTrue("Wrong", men.PriceWithinRange());
    // }

    @Test
    public void testCategory(){
        MensPage men = homePage.clickMenLink();
        men.selectFromDropDown("Men  (7)");
        assertEquals("Wrong number of selections", 1, men.getSelectedOption().size());
        assertTrue("Different title", men.getSelectedOption().get(0).contains(men.getTitle()));
        assertTrue("Different number", men.getSelectedOption().get(0).contains(men.getItemCount()));
    }

    // @Test
    // public void testSearch(){
    //     MensPage men = homePage.clickMenLink();
    //     men.enterTextToSearch("Menaskghs");
    //     men.clickSearchBtn();
    //     if(Integer.valueOf(men.getItemCount()) > 0){
    //         assertTrue("wrong!", men.getSearchResults("Mendhghfg"));
    //     } else {
    //         assertEquals("Wrong text!", "No products were found matching your selection.", men.invalidSearchResults());
    //     }
    // }
}
