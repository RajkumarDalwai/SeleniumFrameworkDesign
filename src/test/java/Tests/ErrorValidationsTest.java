package Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        landingPage.loginApplication("dalwairajkumar22@gmail.com", "Rajkumar@2");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        System.out.println("Error Message: " + landingPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("dalwairajkumar22@gmail.com", "Rajkumar@25");
        
        List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
    }
}