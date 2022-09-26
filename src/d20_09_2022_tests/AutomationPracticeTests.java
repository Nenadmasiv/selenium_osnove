package d20_09_2022_tests;

import java.time.Duration;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import d20_09_2022_pages.BuyBoxPage;
import d20_09_2022_pages.HeaderPage;
import d20_09_2022_pages.LayerCartPage;
import d20_09_2022_pages.TopMenuPage;

public class AutomationPracticeTests {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "http://automationpractice.com/";
	private SoftAssert softAssert;
	
	private BuyBoxPage byBoxPage;
	private LayerCartPage layerCartPage;
	private TopMenuPage topMenuPage;
	private HeaderPage headerPage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); 
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		softAssert = new SoftAssert();
		byBoxPage = new BuyBoxPage(driver, wait);
		layerCartPage = new LayerCartPage(driver, wait);
		topMenuPage = new TopMenuPage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
		softAssert = new SoftAssert();
	}
	@BeforeMethod
	public void beforeMethod() {
		driver.get(baseUrl);
	}
//	@BeforeMethod
//	public void beforeMethod() {
//		driver.get(baseUrl);
//	}
	
	@Test (priority = 100)
	public void addingProductToTheCart() {		
//		Test #1:  Adding product to the cart
//		Ucitati stranicu /index.php?id_product=1&controller=product
//		Odskrolati do buy box-a
//		Postavite kolicinu na 3
//		Izaberite velicinu L
//		Izaberite plavu boju
//		Kliknite na dugme add to cart
//		Cekajte da dijalog layer cart bude vidljiv
//		Verifikovati da je kolicina iz layer cart dijalog 3
//		Verifikovati da je velicina L
//		Verifikovati da je izabran proizvod sa plavom bojom
//		Verifikovati da je total price 3 puta veci od cene proizvoda
//		Kliknite na dugme continue shopping
//		cekajte da dijalog layer cart postane nevidljiv
//		Izaberite novi proizvod sa kolicinom 1, velicinom S i bojom Organe
//		Kliknite na dugme add to cart
//		Cekajte da dijalog bude vidljiv
//		kliknite na dugme proceed to checkout
//		Verifikujte da je naslov stranice Order - My Store
		
		driver.get(baseUrl + "/index.php?id_product=1&controller=product");
		byBoxPage.scrollToByBoxFormElement();
		byBoxPage.getQuantityInput().clear();
		byBoxPage.getQuantityInput().sendKeys("3");
		
		Select selectSize = new Select(byBoxPage.getSizeSelect());
		selectSize.selectByVisibleText("L");
		
		byBoxPage.getElementByColorLink("Blue").click();
		byBoxPage.getAddToCartButton().click();
		
		layerCartPage.waitForElementInvisibility();
		
		Assert.assertEquals(
				layerCartPage.getProductQuaantityElement().getText(),
				"3", "Total quantity is not 3");
		Assert.assertTrue(
				layerCartPage.getProductAttributeElement().getText().contains("L"),
				"Product size is not L");
		Assert.assertTrue(
				layerCartPage.getProductAttributeElement().getText().contains("Blue"),
				"Product color is not Blue");

		layerCartPage.getContinueShoppingButton().click();
		layerCartPage.waitForElementVisibility();
		
		byBoxPage.getQuantityInput().clear();
		byBoxPage.getQuantityInput().sendKeys("1");
		
		selectSize.selectByVisibleText("L");
		
		byBoxPage.getElementByColorLink("Orange").click();
		byBoxPage.getAddToCartButton().click();
		
		layerCartPage.waitForElementVisibility();
		layerCartPage.getProcedToCheckoutButton().click();

		Assert.assertEquals(
				driver.getTitle(), "Order - My Store",
				"Page title is not right.");
		
	}
	
	@Test (priority = 200)
	public void topMenuMouseOver() {
//		Top menu mouse over
//		Predjite misem preko women linka. 
//		Koristan link kako da predjete misem preko nekog elementa link
//		Verifikujte da je podmeni za women deo vidljiv
//		Predjite misem preko dresses linka. 
//		Verifikujte da je podmeni za dresses deo vidljiv
//		Predjite misem preko t-shirts linka. 
//		Verifikujte podmeniji za womens i dresses nevidljivi
//		Ukoliko je potrebno ukljucite odgovarajuca cekanja, 
//		kojim bi se sacekalo da stranica dodje u odgovarajuce stanje
//		Provera preko za vidljivost preko soft assert-a
		
		new Actions(driver)
        .moveToElement(topMenuPage.getWomenLink())
        .perform();
		topMenuPage.waitWomanSubmenuVisibility();
		softAssert.assertTrue(
				topMenuPage.getWomenSubmenu().isDisplayed(),
				"Women submenu is not visible.");

		new Actions(driver)
        .moveToElement(topMenuPage.getDressesLink())
        .perform();
		softAssert.assertTrue(
				topMenuPage.getDressesSubmenu().isDisplayed(),
				"Dresses submenu is not visible.");

		new Actions(driver)
        .moveToElement(topMenuPage.getTShirtsLink())
        .perform();
		topMenuPage.waitDressesSubmenuVisibility();
		softAssert.assertFalse(
				topMenuPage.getWomenSubmenu().isDisplayed(),
				"Women submenu is visible.");
		softAssert.assertFalse(
				topMenuPage.getDressesSubmenu().isDisplayed(),
				"Dresses submenu is visible.");

		softAssert.assertAll();


	}
	
	@Test (priority = 300)
	public void phoneNumberVisibilityCheckOnResize() throws InterruptedException {
//		Test #3:  Phone number visibility check on resize
//		Maksimizujte prozor
//		Proverite da je element za broj telefona vidljiv
//		Smanjite dimenziju pretrazivaca na velicinu 767 x 700
//		Proverite element za broj telefona nije vidljiv
//		Promenite dimenziju pretrazivaca na 768 x 700
//		Proverite da je broj telefona vidljiv
//		Maksimizujte prozor
//		Provera preko soft asserta
		
		Thread.sleep(10000);
		softAssert.assertTrue(
				headerPage.getShopPhoneElement().isDisplayed(),
				"Phone number is not visible when maximized");

		Dimension dimension = new Dimension(767, 700);
		driver.manage().window().setSize(dimension);
		softAssert.assertTrue(
				headerPage.getShopPhoneElement().isDisplayed(),
				"Phone number is hidded on 767 x 700");

		dimension = new Dimension(768, 700);
		driver.manage().window().setSize(dimension);
		wait.until(ExpectedConditions.visibilityOf(headerPage.getShopPhoneElement()));
		softAssert.assertTrue(
				headerPage.getShopPhoneElement().isDisplayed(),
				"Phone is not visible on 768 x 700");

		driver.manage().window().maximize();

		softAssert.assertAll();
			
	}
	
	@Test (priority = 400)
	public void headerLinksCheck() {
//		Test #4:  Header links check
//		Kliknite na contact us link
//		Verifikujte da je naslov stranice Contact us - My Store
//		Kliknite na sign in link
//		Verifikujte da je naslov stranice Login - My Store
//		Provera preko soft asserta
		
		headerPage.getContactUsLink().click();
		softAssert.assertTrue(
				driver.getTitle().equals("Contact us - My Store"),
				"Title is not Contact us - My Store");

		headerPage.getSignInLink().click();
		softAssert.assertTrue(
				driver.getTitle().equals("Login - My Store"),
				"Title is not Login - My Store");

		softAssert.assertAll();


	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
