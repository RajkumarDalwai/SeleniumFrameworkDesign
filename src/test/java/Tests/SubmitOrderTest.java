package Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.OrderPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

    @Test(dataProvider="getData",groups= {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
       
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
    
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("dalwairajkumar22@gmail.com", "Rajkumar@25");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	 
	 @DataProvider
	 public Object[][] getData() throws IOException
	 {
	   List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/Data/PurchaseOrder.json");
	   return new Object[][]  {{data.get(0)}, {data.get(1) } };	
	}
	 
	 // Other Approaches for Data diven Testing
	 
	/* @DataProvider
	 public Object[][] getData()
	 {
	 return new Object[][]  {{"dalwairajkumar22@gmail.com","Rajkumar@25","ZARA COAT 3"}, {"dalwairajkumar25@gmail.com","Rajkumar@25","ADIDAS ORIGINAL" } };
	 }*/
	 
	/* @DataProvider
	 public Object[][] getData()
	 {
	        HashMap<String,String> map = new HashMap<String,String>();
			map.put("email", "dalwairajkumar22@gmail.com");
			map.put("password", "Rajkumar@25");
			map.put("product", "ZARA COAT 3");
			
			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("email", "dalwairajkumar25@gmail.com");
			map1.put("password", "Rajkumar@25");
			map1.put("product", "ADIDAS ORIGINAL");
			
			return new Object[][]  {{map}, {map1}};
	 }*/ 
	 
}
