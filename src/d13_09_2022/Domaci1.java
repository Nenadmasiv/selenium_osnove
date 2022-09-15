package d13_09_2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Domaci1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		Maksimizirati prozor
//		Ucitati stranicu https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
//		Prijavite se na sistem 
//		Username: Admin
//		Password: admin123
//		Cekanje od 5s
//		U input za pretragu iz navigacije unesite tekst Me
//		Kliknite na prvi rezultat pretrage (to ce biti Time)
//		Cekanje od 1s
//		Kliknite u headeru na svog avatara (to ce biti na ime: Paul Collings)
//		Klinkite na logout
//		Cekanje od 5s
//		Zatvorite pretrazivac

		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(5000);

//		WebElement a = driver.findElement(By.xpath("//input[contains(@name,'username')]"));
		WebElement a = driver.findElement(By.name("username"));
		a.sendKeys("Admin");
//		driver.findElement(By.xpath("//input[contains(@name,'username')]")).sendKeys("Admin");
//		driver.findElement(By.className("oxd-input oxd-input--active")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[contains(@name,'password')]")).sendKeys("admin123");
		driver.findElement(By.xpath(
				"//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]"))
				.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("Me");
		driver.findElement(By.xpath("//a[contains(@class,'oxd-main-menu-item')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//header//*[text()='Paul Collings']")).click();
		driver.findElement(By.linkText("Logout")).click();

		Thread.sleep(5000);
		driver.quit();

	}

}
