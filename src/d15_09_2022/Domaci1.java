package d15_09_2022;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Domaci1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

//		Napisati program koji:
//			Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//			Klikce na svaki iks da ugasi obavestenje i proverava 
//			da li se nakon klika element obrisao sa stranice i ispisuje odgovarajuce poruke 
//			(OVO JE POTREBNO RESITI PETLJOM)
//			POMOC: Brisite elemente odozdo.
//			(ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo

		driver.manage().window().maximize();
		driver.get("https://s.bootsnipp.com/iframe/Dq2X");
		Thread.sleep(5000);

		for (int i = 5; i > 0; i--) {
			driver.findElement(By.xpath("//div[@class='col-md-12']/div/button[last()]")).click();

			boolean imaGa = true;
			try {
				driver.findElement(By.xpath("//div[@class='col-md-12']//div[" + i + "]"));

			} catch (Exception e) {
				imaGa = false;
			}

			if (imaGa) {
				System.out.println("Nije Obrisano.");
			} else {
				System.out.println("Obrisano.");
			}

		}

//		List<WebElement> listaElemenata = driver.findElements(By.className("close"));
//		boolean postoji = true;
//		for (int i = listaElemenata.size() - 1; i >= 0; i--) {
//
//			listaElemenata.get(i).click();
//
//			try {
//				driver.findElement(By.className("close"));
//			} catch (NoSuchElementException e) {
//				postoji = false;
//
//			}
//
//		}
//
//		if (postoji) {
//			System.out.println("Elementi se nisu obrisali");
//		} else {
//			System.out.println("Elementi su se obrisali");
//		}
//
//		Thread.sleep(5000);
//		driver.quit();
//		

	}

}
