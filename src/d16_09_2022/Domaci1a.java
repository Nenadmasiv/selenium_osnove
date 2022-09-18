package d16_09_2022;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Domaci1a {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("force-device-scale-factor=0.75");
		options.addArguments("high-dpi-support=0.75");
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();

//		Napisati program koji ima:
//			Podesava:
//			implicitno cekanje za trazenje elemenata od 10s
//			implicitno cekanje za ucitavanje stranice od 10s
//			eksplicitno cekanje podeseno na 10s
//			Podaci:
//			Potrebno je u projektu ukljuciti 4 slike.
//			Imenovanje slika neka bude po pravilu pozicija_ime_prezime_polaznika.ekstenzija
//			Npr: front_milan_jovanovic.jpg, left_milan_jovanovic.jpg …
//			Koraci:
//			Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//			Maksimizuje prozor
//			Selektuje Image - Front klikom na tu karticu u dnu ekrana
//			Klik na add photo iz levog navigacionog menia
//			Upload slike. Upload preko File objekta koristeci getAbsolutePath
//			Sacekati da broj prikazanih slika u donjem uglu navigacije bude za 1 veca
//			Kliknuti na poslednje dodatu sliku kako bi bila izabrana za postavljanje
//			Ceka da dijalog bude vidljiv
//			Klik na Use One Side Only dugme
//			Ceka da se pojavi dijalog sa slikom
//			Klik na Done
//			Ponoviti proces za Left, Right i Back
//			Zatim iz desnog gornjeg ugla izabrati random jedan od ponudjenih konfeta
//			Kliknuti na Add To Cart dugme
//			Verifikovati postojanje greske Oops! It looks like you`ve not added an text to this field, please add one before continuing.
//			Xpath: //*[@action='error']
//			Zatvorite pretrazivac

		driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");

		driver.findElement(By.xpath("//img[@alt='image 1']")).click();
		driver.findElement(By.xpath("//img[@alt='Front']")).click();
		driver.findElement(By.id("imageUpload")).sendKeys(new File("img/front_nenad_ilic.jpg").getAbsolutePath());
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@loading='lazy']")));
		driver.findElement(By.xpath("//img[@loading='lazy']")).click();
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'sc-gYMRRK gMENHs')]")));
		driver.findElement(By.xpath("//button[text()='Use One Side Only']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='container']")));
		driver.findElement(By.xpath("//button[text()='Done']")).click();

		for (int i = 2; i < 5; i++) {
			driver.findElement(By.xpath("//img[@alt='image " + i + "']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[text()='+ Add Image']")).click();
			driver.findElement(By.id("imageUpload")).sendKeys(new File("img/left_nenad_ilic.jpg").getAbsolutePath());
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@loading='lazy']")));
			driver.findElement(By.xpath("//img[@loading='lazy']")).click();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'sc-gYMRRK gMENHs')]")));
			driver.findElement(By.xpath("//button[text()='Use One Side Only']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='container']")));
			driver.findElement(By.xpath("//button[text()='Done']")).click();
		}

//		Zatim iz desnog gornjeg ugla izabrati random jedan od ponudjenih konfeta
//		Kliknuti na Add To Cart dugme
//		Verifikovati postojanje greske Oops! It looks like you`ve not added an text to this field, please add one before continuing.
//		Xpath: //*[@action='error']
//		Zatvorite pretrazivac

		
		Random rand = new Random();
		int random = rand.nextInt(4);
		driver.findElement(By.xpath("//div[@name='"+random+"']"));

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		boolean oops = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@action = 'error']")));
		} catch (Exception e) {
			oops = false;
		}

		if (oops) {
			System.out.println("Pojavio se Oops!");
		} else {
			System.out.println("Nema ga Oops!");
		}

		Thread.sleep(5000);
		driver.quit();

	}

}
