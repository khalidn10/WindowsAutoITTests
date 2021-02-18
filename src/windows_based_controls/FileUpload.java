package windows_based_controls;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUpload {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khalid\\Documents\\Documents\\Courses\\Selenium\\Apps\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://altoconvertpdftojpg.com/");
		driver.findElement(By.cssSelector(".g-form-group-title--desktop")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\Khalid\\Documents\\Documents\\Courses\\Selenium\\Section 38\\fileupload.exe");
		System.out.println("Upload successful");
	}

}
