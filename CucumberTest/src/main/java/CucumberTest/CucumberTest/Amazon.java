package CucumberTest.CucumberTest; 

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.firefox.FirefoxDriver; 

import cucumber.annotation.en.Given; 
import cucumber.annotation.en.Then; 
import cucumber.annotation.en.When; 

public class Amazon { 
   WebDriver driver = null; 
   //Element identification repository
   private static final By signIn = By.xpath("//*[@id='nav-link-yourAccount']/span[1]");
   private static final By userName = By.id("ap_email");
   private static final By password = By.id("ap_password");
   private static final By submit = By.id("signInSubmit");
   private static final By myAccount = By.xpath("//*[@id='nav-link-yourAccount']/span[2]");
   private static final By searchField = By.id("twotabsearchtextbox");
   private static final By firstItemInResults = By.xpath("//*[@id='result_0']/div/div[4]/div[1]/a/h2");
   private static final By addItemToChart = By.xpath("//*[@id='add-to-cart-button']");
   private static final By itemPriceFromDescription = By.xpath("//*[@id='priceblock_ourprice']");
   private static final By basket = By.xpath("//*[@id='nav-cart']/span[3]");
   private static final By priceFromBaskets = By.xpath("//*[@id='hlb-subcart']/div[1]/span/span[2]");
   public String priceFromProductDescription;
   public String priceFromBasket;
   
   
   @Given("^I have open the browser$") 
   public void openBrowser() { 
   //Initiating the browser
	   driver = new FirefoxDriver(); 
   } 
	
   @When("^I have opened \"([^\"]*)\" on my browser$") 
   public void openTheDNAOnBrowser(String dns) { 
	  openBrowser();
	  //switching to homepage
      driver.navigate().to("https://"+ dns+"/"); 
   } 
	
   @When("^I click Sign-in$") 
   public void iClickSignIn() { 
	   //clicking on signin button
	  driver.findElement(signIn).click();
   } 
  
   @When("^Enter valid \"([^\"]*)\" and \"([^\"]*)\" and click signin$") 
   public void enterUserNameAndPasword(String username, String passw0rd) { 
	  //Entering username
	  driver.findElement(userName).sendKeys(username);
	   //Entering password
	  driver.findElement(password).sendKeys(passw0rd);
	  //Clicking on submit
	  driver.findElement(submit).click();
   } 
   
   @When("^I am logged in$") 
   public void iAmLoggedIn() { 
     // Verifying login functionality
	  Boolean isPresent = driver.findElements(myAccount).size() > 0;
      if(driver.getPageSource().contains("Your Account")){
		  System.out.println("Managed to login");
		  }else{
		  System.out.println("Failed to login");
		  }
      driver.close(); 
   } 
   
   @Given("^\"([^\"]*)\" is open and I am logged in with \"([^\"]*)\" and \"([^\"]*)\"$") 
   public void opennedAmazonAndLoggedIn(String dns,String username, String passw0rd) { 
	   //creating wrapper for opening and logging into amazon
	   openTheDNAOnBrowser(dns);
	   enterUserNameAndPasword(username,passw0rd);
	   iAmLoggedIn();	   
   } 
    
   @Given("^When I search for \"([^\"]*)\"$") 
   public void searchingProduct(String product) { 
	   //searching for product Id
	   driver.findElement(searchField).sendKeys(product);	   
   } 
   
   @Then("^The first result has the word  \"([^\"]*)\" in it$") 
   public void productFromSite(String result) { 
	   //Capturing the first item in the results
	  String textFromApplication =  driver.findElement(searchField).getText();	  
	  assert(textFromApplication).equals(result);
   } 
   
   @Given("^I add \"([^\"]*)\" to my basket$") 
   public void addedProductToBasket(String product) { 
	   //selecting the first item in results list
	   driver.findElement(firstItemInResults).click();
	   priceFromProductDescription =  driver.findElement(itemPriceFromDescription).getText();	
	   driver.findElement(addItemToChart).click();
   } 
   
   @Given("^I check my basket total$") 
   public void checkMyBasketTotal(String product) { 
	   //selecting the first item in results list
	   driver.findElement(basket).click();
   } 
   
   @Then("^It should match the price of \"([^\"]*)\"$") 
   public void getPriceFromBasketAndComparewithItemDescriptionValue(String result) { 
	   //getting price from basket and comparing with product description
	  priceFromBasket =  driver.findElement(priceFromBaskets).getText();	  
	  assert(priceFromBasket).equals(priceFromProductDescription);
   }
}
