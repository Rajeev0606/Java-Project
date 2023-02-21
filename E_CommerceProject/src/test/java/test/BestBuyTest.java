package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BestBuyTest {

	public WebDriver driver;

	@BeforeMethod
	public void StartUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.bestbuy.com/");
		driver.findElement(By.xpath("//a[@class='us-link']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@Test(priority = 0)
	public void Test1() {

		if (driver.findElement(By.tagName("body")).getText().contains("404")) {
			System.out.println("Broken link");
		} else {
			System.out.println("Valid link");
		}
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());

	}

	@Test(priority = 1)
	public void signUpAndLoginTest() throws InterruptedException {

		driver.findElement(By.xpath("//span[@class='v-p-right-xxs line-clamp']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@data-lid='ubr_mby_signin_b']")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("fld-e")).sendKeys("jxqwnw@dishcatfish.com");
		driver.findElement(By.id("fld-p1")).sendKeys("Rajeevraj@1234");
		driver.findElement(By.xpath("//button[@data-track='Sign In']")).click();
		Thread.sleep(5000);

	}

	@Test(priority = 2)
	public void BestBuyNavigationTest() throws InterruptedException {

		WebElement mainMenu = driver.findElement(By.xpath("//button[@aria-label='Menu']"));
		for (WebElement menu : mainMenu.findElements(By.xpath("//div[@class='hamburger-menu-flyout']//li"))) {
			menu.click();

			// Validate the title of the page
			String pageTitle = driver.getTitle();
			System.out.println("Title of the page: " + pageTitle);
		}
	}

	@Test(priority = 3)
	public void BottomLinksValidation() throws InterruptedException {

		WebElement footerLinks = driver
				.findElement(By.xpath("//ul[@class='icon-navigation']//a[@class='icon-navigation-link']"));
		Thread.sleep(3000);

		List<WebElement> links = footerLinks.findElements(By.tagName("a"));

		for (WebElement link : links) {
			String href = link.getAttribute("href");

			if (href == null || href.isEmpty()) {
				System.out.println("FAILED: Link with text \"" + link.getText() + "\" does not have an href attribute");
			}

			else if (!href.startsWith("http") || !href.contains(".")) {
				System.out.println("FAILED: Link with text \"" + link.getText() + "\" has an invalid URL: " + href);
			}

			else {
				System.out.println("PASSED: Link with text \"" + link.getText() + "\" has a valid URL: " + href);
			}
		}

	}

	@Test(priority = 4)
	public void BestBuyShoppingCart() throws InterruptedException {

		WebElement searchBox = driver.findElement(By.name("st"));
		searchBox.sendKeys("Samsung TV");
		Thread.sleep(3000);

		WebElement searchButton = driver.findElement(By.xpath("//button[@title='submit search']"));
		searchButton.click();
		Thread.sleep(3000);

		WebElement firstProduct = driver
				.findElement(By.xpath("//div[@class='list-item lv'][1]//a[@class='image-link']"));
		firstProduct.click();
		Thread.sleep(3000);

		WebElement addToCartButton = driver.findElement(By.xpath(
				"//button[@class='c-button c-button-primary c-button-lg c-button-block c-button-icon c-button-icon-leading add-to-cart-button']"));
		addToCartButton.click();
		Thread.sleep(3000);
		System.out.println(addToCartButton.getText());
	}

	@Test(priority = 5)
	public void BestBuyDemo() throws InterruptedException {

		WebElement menu = driver.findElement(By.xpath("//button[@aria-label='Menu']"));
		menu.click();
		Thread.sleep(3000);

		WebElement computersLink = driver.findElement(By.xpath("//button[text()='Computers & Tablets']"));
		computersLink.click();
		Thread.sleep(3000);

		WebElement firstItem = driver.findElement(By.xpath("//button[text()='Laptops & Desktops']"));
		firstItem.click();
		Thread.sleep(3000);

		WebElement firstItem1 = driver.findElement(By.xpath("//a[text()='Laptops']"));
		firstItem1.click();
		Thread.sleep(3000);

		WebElement selectMackBooks = driver.findElement(By.xpath("//a[text()='MacBooks']"));
		selectMackBooks.click();
		Thread.sleep(3000);

		WebElement addToCartButton = driver
				.findElement(By.xpath("(//div[@class='fulfillment-add-to-cart-button']/div)[2]"));
		addToCartButton.click();
		Thread.sleep(3000);
		System.out.println(addToCartButton.getText());

	}

	@Test(priority = 6)
	public void BestBuyAddToCart() throws InterruptedException {

		WebElement menu = driver.findElement(By.xpath("//button[@aria-label='Menu']"));
		menu.click();
		Thread.sleep(3000);

		// find the "Brands" menu and click on it
		WebElement brandsMenu = driver.findElement(By.xpath("//button[text()='Brands']"));
		brandsMenu.click();
		Thread.sleep(3000);

		// select a brand from the menu
		WebElement brand = driver.findElement(By.xpath("//a[text()='Apple']"));
		brand.click();
		Thread.sleep(3000);

		WebElement brandModel = driver.findElement(By.xpath(
				"//a[@data-track='[context:widgetType=secondaryNavigation,linkContent=iPhone,linkRegion=Apple Navigation,linkPlacement=c1w1i2]']"));
		brandModel.click();
		Thread.sleep(3000);

		WebElement modelID = driver.findElement(By.linkText("iPhone 14"));
		modelID.click();
		Thread.sleep(3000);

		WebElement addToCartButton = driver
				.findElement(By.xpath("(//div[@class='fulfillment-add-to-cart-button']/div)[1]"));
		addToCartButton.click();
		Thread.sleep(3000);

	}

	@Test(priority = 7)
	public void BestBuyCheckout() throws InterruptedException {
		
		WebElement searchBox = driver.findElement(By.name("st"));
		searchBox.sendKeys("Samsung TV");
		Thread.sleep(3000);

		WebElement searchButton = driver.findElement(By.xpath("//button[@title='submit search']"));
		searchButton.click();
		Thread.sleep(3000);

		WebElement firstProduct = driver
				.findElement(By.xpath("//div[@class='list-item lv'][1]//a[@class='image-link']"));
		firstProduct.click();
		Thread.sleep(3000);
		
		WebElement saveButton = driver.findElement(By.xpath(
				"(//div[@class='shop-save-for-later']/button)[1]"));
		saveButton.click();
		Thread.sleep(7000);
		
		// Click on the cart icon
		WebElement cartIcon = driver.findElement(By.xpath("//span[@class='cart-label']"));
		cartIcon.click();
		Thread.sleep(5000);

		WebElement addToCartButton = driver.findElement(By.xpath(
				"(//div[@class='fulfillment-add-to-cart-button']/div)[1]"));
		addToCartButton.click();
		Thread.sleep(7000);
		
		//WebElement goToCartIcon = driver.findElement(By.xpath("//div[@class='go-to-cart-button']"));
		//goToCartIcon.click();
		//Thread.sleep(5000);

		WebElement checkoutButton = driver.findElement(By.xpath("//button[@data-track='Checkout - Top']"));
		checkoutButton.click();
		Thread.sleep(5000);
		
		WebElement ContinueasGuest = driver.findElement(By.xpath("//button[text()='Continue as Guest']"));
        ContinueasGuest.click();
        Thread.sleep(3000);
        
        WebElement eMail = driver.findElement(By.xpath("//input[@id='user.emailAddress']"));
        eMail.sendKeys("jxqwnw@dishcatfish.com");
        Thread.sleep(3000);
        
        WebElement phoneNumber = driver.findElement(By.xpath("//input[@id='user.phone']"));
        phoneNumber.sendKeys("904 793 3720");
        Thread.sleep(3000);
        
        WebElement continueAsPaymentButton = driver.findElement(By.xpath("//button[@class='btn btn-lg btn-block btn-secondary']"));
        continueAsPaymentButton.click();
        Thread.sleep(7000);


		WebElement creditCardNumber = driver.findElement(By.xpath("//input[@id='optimized-cc-card-number']"));
		creditCardNumber.sendKeys("4111111111111111");
		Thread.sleep(3000);

		WebElement expirationMonth = driver.findElement(By.xpath("//select[@id='credit-card-expiration-month']"));
		expirationMonth.sendKeys("01");
		Thread.sleep(3000);

		WebElement expirationYear = driver.findElement(By.xpath("//select[@id='credit-card-expiration-year']"));
		expirationYear.sendKeys("2024");
		Thread.sleep(3000);

		WebElement securityCode = driver.findElement(By.xpath("//input[@id='credit-card-cvv']"));
		securityCode.sendKeys("123");
		Thread.sleep(3000);

		// Fill out the checkout form with dummy payment information
		WebElement firstName = driver.findElement(By.xpath("//input[@name='firstName']"));
		firstName.sendKeys("Raj");
		Thread.sleep(3000);

		WebElement lastName = driver.findElement(By.xpath("//input[@name='lastName']"));
		lastName.sendKeys("Raj");
		Thread.sleep(3000);

		WebElement Address = driver.findElement(By.xpath("//input[@name='street']"));
		Address.sendKeys("123 Main St.");
		Thread.sleep(3000);

		WebElement city = driver.findElement(By.xpath("//input[@name='city']"));
		city.sendKeys("Anytown");
		Thread.sleep(3000);

		WebElement state = driver.findElement(By.xpath("//select[@name='state']"));
		state.sendKeys("CA");
		Thread.sleep(3000);

		WebElement zipCode = driver.findElement(By.xpath("//input[@name='zipcode']"));
		zipCode.sendKeys("12345");
		Thread.sleep(3000);

		WebElement placeOrderButton = driver
				.findElement(By.xpath("//button[@data-track='Place your Order - Contact Card']"));
		placeOrderButton.click();
		Thread.sleep(3000);

		// page for the order confirmation message
		WebElement confirmationMessage = driver
				.findElement(By.xpath("//h3[text()='Hmmm... we can’t confirm this address.']"));
		if (confirmationMessage.isDisplayed()) {
			System.out.println("Address is not filled correctly");
		} else {
			System.out.println("Order successfully Placed");
		}

	}

	@AfterMethod
	public void closeUp() {
		driver.close();
	}

}
