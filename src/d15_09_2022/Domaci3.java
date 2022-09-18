package d15_09_2022;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Domaci3 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

//		Napisati program koji 
//		Ucitava https://seeds.sproutsocial.com/components/loader-button/
//		Odskrola do 
//		Loader buttons are used to display a loading indicator inside of a button. paragrafa. Koristan link
//		Klikce ne dugme 
//		Ceka da dugme zavrsi sa loadingom 
//		Ispisati poruku na ekranu
//		Zatvoriti pretrazivac
//		HINT: Koristite data-qa-button-isloading  atribut elementa za cekanje odredjenog stanja tog elementa

		driver.manage().window().maximize();
		driver.get("https://seeds.sproutsocial.com/components/loader-button/");
		Thread.sleep(5000);

		WebElement paragraf = driver.findElement(By.xpath("//*[text()='Click me to load!']"));
		new Actions(driver).scrollToElement(paragraf).perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='Click me to load!']")).click();

		boolean load = true;
		try {
			driver.findElement(By.xpath("//button[@data-qa-button-isloading='true']"));
		} catch (Exception e) {
			load = false;
		}

		if (load) {
			System.out.println("Dugme se ucitava.");
		} else {
			System.out.println("Dugme se ne ucitava.");
		}

		Thread.sleep(10000);
		try {
			driver.findElement(By.xpath("//button[@data-qa-button-isloading='false']"));
		} catch (Exception e) {
			load = false;
		}

		if (load) {
			System.out.println("Dugme zavrsilo ucitavanje.");
		} else {
			System.out.println("Dugme se i dalje ucitava.");
		}

	}

}
