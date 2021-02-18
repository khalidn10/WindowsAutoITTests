package windows_based_controls;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FileDownload {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String downloadPath = System.getProperty("user.dir");
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		//prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", prefs);
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khalid\\Documents\\Documents\\Courses\\Selenium\\Apps\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://altoconvertpdftojpg.com/");
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(".g-form-group-title--desktop")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec(downloadPath + "/fileupload.exe");
		System.out.println("Pdf file upload successful");
		
		WebDriverWait eWait = new WebDriverWait(driver, 10);
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Convert Now!']")));
		System.out.println("Waiting for Convert Now! button...");
		Thread.sleep(10000);
		System.out.println("10 sec wait finished");*/
		
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofSeconds(10)) // Set timeout to 10 seconds
		        .pollingEvery(Duration.ofSeconds(1)) // Poll every second
		        .ignoring(NoSuchElementException.class);
		
		WebElement confirmNowButton = fWait.until(new Function<WebDriver, WebElement>()
	    {
	    	// Customised function for checking if "Confirm Now!" button has been displayed 
	    	public WebElement apply(WebDriver driver)
	    	{
	    		// Create WebElement instance for "Confirm Now!" button
	    		List<WebElement> cnb = driver.findElements(By.cssSelector(".g-btn.g-btn--primary.g-btn--medium"));
	    		
	    		// If cnb List contains one element, i.e. "Confirm Now!" button is displayed
	    		if (cnb.size() == 1)
	    		{
	    			// Scroll down to the "Confirm Now!" button
	    			JavascriptExecutor js = (JavascriptExecutor) driver;
	    			String jSScript =	"var element = document.querySelector(\".g-btn.g-btn--primary.g-btn--medium\");" +
	    							"element.scrollIntoView();";
	    			js.executeScript(jSScript);
	    			//System.out.println("Confirm Now! button now displayed");
	    			return cnb.get(0);
	    		}
	    		// Otherwise
	    		else
	    		{
	    			//System.out.println("Confirm Now! button still not displayed");
	    			// Return null
	    			return null;
	    		}
	    	}
	    });
		
		confirmNowButton.click();
		//driver.findElement(By.xpath("//*[text()='Convert Now!']")).click();
		//System.out.println("Convert Now! button pressed");
		eWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download Now")));
		//System.out.println("Waiting for Download Now button...");
		driver.findElement(By.linkText("Download Now")).click();
		//System.out.println("Download Now button pressed");
		Thread.sleep(3000);
		//System.out.println("Waiting for 3 seconds...");
		String downloadFileName = "/" + driver.findElement(By.cssSelector(".output-result__file-name")).getText() + ".jpg";
		File downloadFile = new File(downloadPath + downloadFileName);
		
		if (downloadFile.exists())
		{
			System.out.println("Jpg file download successful");
			Thread.sleep(1000);
			downloadFile.delete();
			System.out.println("Jpg download file deleted");
		}
		else
		{
			Assert.assertTrue(downloadFile.exists(), "Jpg file download unsuccessful");
		}
	}

}
