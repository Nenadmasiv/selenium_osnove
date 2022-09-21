package d19_09_2022;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class Domaci1 {

	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "https://s.bootsnipp.com";

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get(baseUrl);
	}

//	@Test(priority = 100)
	public void editRow() {
//		 Edit Row
//		 Podaci:
//		 First Name: ime polaznika
//		 Last Name: prezime polaznika
//		 Middle Name: srednje ime polanzika
//		 Koraci:
//		 Ucitati stranu /iframe/K5yrx
//		 Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//		 Klik na Edit dugme prvog reda
//		 Sacekati da dijalog za Editovanje bude vidljiv
//		 Popuniti formu podacima. 
//		 Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, 
//		 za to se koristi metoda clear. Koristan link
//		 Klik na Update dugme
//		 Sacekati da dijalog za Editovanje postane nevidljiv
//		 Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//		 Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//		 Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//		 Za sve validacije ispisati odgovarajuce poruke u slucaju greske

		driver.get(baseUrl + "/iframe/K5yrx");

		Assert.assertEquals(driver.getTitle(),
				"Table with Edit and Update Data - Bootsnipp.com",
				"Unexpected title.");
		
		driver.findElement(By.xpath("//*[@id='d1']/td/button")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.className("modal-content")));
		
		driver.findElement(By.id("fn")).clear();
		driver.findElement(By.id("fn")).sendKeys("Zdravko");
		driver.findElement(By.id("ln")).clear();
		driver.findElement(By.id("ln")).sendKeys("Colic");
		driver.findElement(By.id("mn")).clear();
		driver.findElement(By.id("mn")).sendKeys("Cola");
		driver.findElement(By.id("up")).click();
		
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(
						By.className("modal-content")));
		
		Assert.assertEquals(
				driver.findElement(By.id("f1")).getText(),
				"Zdravko", "Unexpeceted name.");
		
		Assert.assertEquals(
						driver.findElement(By.id("l1")).getText(),
						"Colic", "Unexpeceted last name.");
		
		Assert.assertEquals(
				driver.findElement(By.id("m1")).getText(),
				"Cola", "Unexpeceted middle name.");
		
	}
	
//	@Test (priority = 200)
	public void  deleteRow() {
//		 Delete Row
//		 Podaci:
//		 First Name: ime polaznika
//		 Last Name: prezime polaznika
//		 Middle Name: srednje ime polanzika
//		 Koraci:
//		 Ucitati stranu /iframe/K5yrx
//		 Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//		 Klik na Delete dugme prvog reda
//		 Sacekati da dijalog za brisanje bude vidljiv
//		 Klik na Delete dugme iz dijaloga 
//		 Sacekati da dijalog za Editovanje postane nevidljiv
//		 Verifikovati da je broj redova u tabeli za jedan manji
//		 Za sve validacije ispisati odgovarajuce poruke u slucaju greske

		driver.get(baseUrl + "/iframe/K5yrx"); 
		
		Assert.assertEquals(driver.getTitle(),
				"Table with Edit and Update Data - Bootsnipp.com",
				"Unexpected title.");
		
		driver.findElement(By.xpath("//*[@id='d1']/td/button")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.className("modal-content")));
		
		driver.findElement(By.id("fn")).clear();
		driver.findElement(By.id("fn")).sendKeys("Zdravko");
		driver.findElement(By.id("ln")).clear();
		driver.findElement(By.id("ln")).sendKeys("Colic");
		driver.findElement(By.id("mn")).clear();
		driver.findElement(By.id("mn")).sendKeys("Cola");
		driver.findElement(By.id("up")).click();
		
		driver.findElement(By.xpath("//button[@data-target='#delete']")).click();
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.id("del")));
		
		driver.findElement(By.id("del")).click();
		
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(
						By.id("del")));
		
		boolean oneRowLes = 
		driver.findElements(By.xpath("//*[@class='row']//tbody/tr")).size() == 1;
			Assert.assertTrue(oneRowLes, "Row is not deleted.");
		
	}
	
	@Test (priority = 300)
	public void takeAScreenshot() throws IOException {
//		 Take a Screenshot
//		 Koraci:
//		 Ucitati stranu  /iframe/K5yrx
//		 Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//		 Kreirati screenshot stranice. 
//		 Koristan link https://www.guru99.com/take-screenshot-selenium-webdriver.html
//		 Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. 
//		 Na putanji: src/paket_za_domaci/nazivslike.png
		
		driver.get(baseUrl + "/iframe/K5yrx");
		Assert.assertEquals(
				driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com");
		
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File("img/Screenshot2.png");
		FileHandler.copy(SrcFile, DestFile);
//		com.google.common.io.Files.copy(SrcFile, DestFile);
		
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
