package d20_09_2022_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LayerCartPage {

//	LayerCartPage koja pribavlja:
//	continue shopping dugme
//	proced to checkout dugme
//	element koji vraca atrubute proizvoda (sa slike je to desno od devojke)
//	element koji cuva quantity (takodje desno od devojke)
//	element koji cuva total price
//	metodu koja ceka da dijalog bude vidljiv
//	metodu koja ceka da dijalog bude nevidljiv

	
	private WebDriver driver;
	private WebDriverWait wait;

	public LayerCartPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public WebElement getContinueShoppingButton() {
		return driver.findElement(By.xpath("//*[@title='Continue shopping']"));
	}
	public WebElement getProcedToCheckoutButton() {
		return driver.findElement(By.xpath("//*[@title='Proceed to checkout']"));
	}
	public WebElement getProductAttributeElement() {
		return driver.findElement(By.id("product attribute"));
	}
	public WebElement getProductQuaantityElement() {
		return driver.findElement(By.id("layer_cart_product_quantity"));
	}
	public WebElement getProductTotalPriceElement() {
		return driver.findElement(By.id("layer_cart_product_price"));
	}
	public void waitForElementVisibility() {
		this.wait.until(ExpectedConditions.visibilityOf(getProductAttributeElement()));
	}
	public void waitForElementInvisibility() {
		this.wait.until(ExpectedConditions.invisibilityOf(getProductAttributeElement()));
	}

	
}
