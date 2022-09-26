package d20_09_2022_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenuPage {
	
//	TopMenuPage koja pribavlja:
//	metodu koja vraca WOMEN link iz glavnom menija
//	metodu koja vraca DRESSES link iz glavnom menija
//	metodu koja vraca T-SHIRTS link iz glavnom menija
//	metodu koja vraca podmeni za WOMEN
//	metodu koja vraca podmeni za DRESSES
	private WebDriver driver;
	private WebDriverWait wait;

	public TopMenuPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public WebElement getWomenLink() {
		return driver.findElement(By.linkText("Women"));
	}
	public WebElement getDressesLink() {
		return driver.findElement(By.linkText("Dresses"));
	}
	public WebElement getTShirtsLink() {
		return driver.findElement(By.linkText("T-shirts"));
	}
	public WebElement getWomenSubmenu() {
		return driver.findElement(By.xpath("//*[@title='Tops']/../.."));
	}
	public WebElement getDressesSubmenu() {
		return driver.findElement(By.xpath("//*[@title='Casual Dresses']/../.."));
	}
	public void waitWomanSubmenuVisibility() {
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@title='Tops']/../..")));
	}
	public void waitDressesSubmenuVisibility() {
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@title='Casual Dresses']/../..")));
	}

}
