package uitests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.datasource.ReadProperties;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc = DesiredCapabilities.chrome();
        if(ReadProperties.readProperty("machine").equalsIgnoreCase("local")) {
            if(ReadProperties.readProperty("browser").equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
            }
            else if (ReadProperties.readProperty("browser").equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            else if (ReadProperties.readProperty("browser").equalsIgnoreCase("iexplorer")) {
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions IEOptions = new InternetExplorerOptions();
                IEOptions.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
                IEOptions.ignoreZoomSettings();
                IEOptions.introduceFlakinessByIgnoringSecurityDomains();
                IEOptions.enableNativeEvents();
                IEOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                //IEOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                IEOptions.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
                driver = new InternetExplorerDriver(IEOptions);
                driver.manage().window().maximize();
            }
        }else{
            driver = new RemoteWebDriver(new URL(ReadProperties.readProperty("remotepath")),dc);
        }
    }
    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }

    }

}
