package d20_09_2022_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyBoxPage {

//	BuyBoxPage koja pribavlja:
//		input za kolicinu
//		select za velicinu
//		add to cart dugme
//		add to wishlist dugme
//		metodu koja vraca element boje. 
//		Metoda kao parametar prima naziv boje 
//		(npr: “Orange”, “Blue”) a vraca link koji ima atribut title
//		prema trazenoj vrednosti.
//		metodu koja skrola do ovog dela stranice


	private WebDriver driver;
	private WebDriverWait wait;

	public BuyBoxPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	 public WebElement getQuantityInput() {
		 return driver.findElement(By.id("quantity_wanted"));
	 }
	 public WebElement getSizeSelect() {
		 return driver.findElement(By.id("uniform-group_1"));
	 }
	 public WebElement getAddToCartButton() {
		 return driver.findElement(By.name("Submit"));
	 }
	 public WebElement getAddToWishlistButton() {
		 return driver.findElement(By.id("wishlist_button"));
	 }
	 public WebElement getElementByColorLink(String color) {
		 return driver.findElement(By.xpath("//*[@title='"+ color +"']"));
	 }

	public void getScrollToByBoxFormElement() {
		new Actions(this.driver).moveToElement(
				driver.findElement(By.id("buy_block"))).perform();
	}
	
	 
	 
	
	
}
