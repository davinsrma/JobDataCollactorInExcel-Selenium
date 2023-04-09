package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import reports.ReportListener;
import reports.TestListener;
import util.Utilities;

import java.io.*;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.net.InetAddress.getLocalHost;

public abstract class BaseClass {
    public static final int TIMEOUT = 5;
    public  static String TestBedBrowser;
    private final String produrl = "https://www.glassdoor.co.in/index.htm";
    public static WebDriver driver;
    public static WebDriverWait wait;
    private final String extentReportName = "./Results"+"/Openings_" + Utilities.getCurrentDateTimeStamp() + ".html";
    ExtentSparkReporter spark;
    public static ExtentReports extent;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite: JobDataCollector");
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest(final ITestContext testContext) {
        System.out.println("BeforeTest: Testcase start time stamp: " + Utilities.getCurrentDateTimeStamp());
        extentConfig();
    }



    @Parameters("browser")
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browser) throws IOException {
        System.out.println("BeforeClass: Launching Web Browser:" + browser);
        TestBedBrowser = browser;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
//          Below line is useful to remote the exception of WebSocket issue
            options.addArguments("--remote-allow-origins=*");

//          If you want to operate your code without opening the chrome (Background)

//          options.addArguments("--headless");
//			options.addArguments("--disable-web-security");
//			options.addArguments("--allow-insecure-localhost");
//			options.addArguments("--ignore-urlfetcher-cert-requests");

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("safari")) {
            SafariOptions options = new SafariOptions();
            options.setCapability("safari.cleanSession", true);
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver(options);
            driver.manage().window().maximize();

        } else {
            Reporter.log("This Browser " + browser + "is not supported");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

    }



    @BeforeMethod
    public void beforeMethod()  {
        driver.get(produrl);

    }

    @AfterMethod
    public void tearDownMethod()  {


    }


    @AfterTest
    public void tearDownTest() {
        //to write or update test information to reporter
        System.out.println("AfterTest:");
        extent.flush();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        System.out.println("AfterClass:");
        if (driver!=null) {
            driver.quit();
        }
    }

//        @AfterSuite
    public void afterSuite(ITestContext itstCntxt){
            System.out.println("AfterSuite: Emailing Report");

            List<String> summary =new ArrayList<String>();

            summary.add("\n");
            summary.add("******************************");
            summary.add("\n");
            summary.add("** Test Execution Summary **");
            summary.add("\n");
            summary.add("******************************");
            summary.add("\n");
            summary.add("Pass : " + itstCntxt.getPassedTests().size());
            summary.add("\n");
            summary.add("Fail : " + itstCntxt.getFailedTests().size());
            summary.add("\n");
            summary.add("Skipped : " + itstCntxt.getSkippedTests().size());
            summary.add("\n");
            summary.add("******************************");
            summary.add("\n");
            summary.add("NOTE: This is system generated report. plz do not reply. If any questions please let me know at \n\ndavinsrma@gmail.com or Call +91 8877993131");
            summary.add("\n");
            Utilities.sendEmailReport(extentReportName , summary);

    }
    public void extentConfig() {
        spark = new ExtentSparkReporter(extentReportName);

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();

        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Data for Job");
        spark.config().setReportName("Extent Test Report");
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        spark.config().thumbnailForBase64();
        spark.config().setTimelineEnabled(true);

//        extent.setSystemInfo("Env URL:", produrl);
        extent.setSystemInfo("Executed On Date: ", String.valueOf(new Date()));
        extent.setSystemInfo("executed by: ", System.getProperty("user.name"));
        try {
//            extent.setSystemInfo("executed on Computer ip: ", getLocalHost().getHostAddress());
            extent.setSystemInfo("executed on Computer Name: ", getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        extent.attachReporter(spark);
    }

}