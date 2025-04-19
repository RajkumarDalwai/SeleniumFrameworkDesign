package ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {
	public class DemoTest {

	    static ExtentReports extent;

	    public static ExtentReports getReportObject() {
	        String path = System.getProperty("user.dir") + "\\reports\\index.html";
	        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	        reporter.config().setReportName("Web Automation Results");
	        reporter.config().setDocumentTitle("Test Results");

	        extent = new ExtentReports();
	        extent.attachReporter(reporter);
	        extent.setSystemInfo("Tester", "Rajkumar Dalwai");

	        return extent;
	    }

	    @Test
	    public void initialDemo() {
	        extent = getReportObject();
	        ExtentTest test = extent.createTest("Initial Demo");

	        WebDriver driver = new ChromeDriver();
	        driver.get("https://rahulshettyacademy.com");
	        System.out.println(driver.getTitle());
	        driver.close();

	        test.fail("Result do not match");

	        extent.flush();
	    }
	}
}