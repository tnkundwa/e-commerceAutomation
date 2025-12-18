package accounts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTests;
import pages.AccountsPage;

public class AccountTests extends BaseTests{

    @Test
    public void testAccont(){
        AccountsPage account = homePage.clickAccount();
        account.enterEmail("tnkundwa");
        account.entePassword("tnkundwa");
        account.clickLogin();
        assertEquals("Failed nigga!", "Account", account.pageTitle());
        String username = "tnkundwa";
        assertTrue("wrong text!", account.pageWords().contains("Hello " + username));
    }

    @Test
    public void testLogout(){
        AccountsPage account = homePage.clickAccount();
        account.enterEmail("tnkundwa");
        account.entePassword("tnkundwa");
        account.clickLogin();
        account.logOut();
        assertEquals("Failed nigga!", "Account", account.pageTitle());
    }

    @Test
    public void testInvalidUsername(){
        AccountsPage account = homePage.clickAccount();
        String username = "shehe";
        account.enterEmail(username);
        account.entePassword("tnkundwa");
        account.clickLogin();
        assertTrue("Invalid alert!", account.invalidDataAlert().contains(username + " is not registered"));
    }
    @Test
    public void testInvalidPassword(){
        AccountsPage account = homePage.clickAccount();
        String username = "tnkundwa";
        account.enterEmail(username);
        account.entePassword("inka");
        account.clickLogin();
        assertEquals("Invalid alert!", "Error: The password you entered for the username " + username + " is incorrect. Lost your password?", account.invalidDataAlert());
    }
}
