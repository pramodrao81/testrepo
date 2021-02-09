package uipages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.datasource.ReadProperties;

public class HotelPage extends BasePage{

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@class,'hotel')]")
    private WebElement btnHotel;

    @FindBy(xpath = "//div[contains(@class,'locationlistHotels')]")
    private WebElement clickDestination;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement inputDestination;

    @FindBy(xpath = " //ul[contains(@class,'result-sub')]")
    private WebElement selectDestination;

    @FindBy(css = "#checkin")
    private WebElement inputCheckIn;

    @FindBy(css = "#checkout")
    private WebElement inputCheckout;

    @FindBy(xpath = "(//input[@name='adults']/following::button[contains(@class,'touchspin-up')])[1]")
    private WebElement btnAdults;

    @FindBy(xpath = "(//input[@name='children']/following::button[contains(@class,'touchspin-up')])[1]")
    private WebElement btnKids;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSearch;

    @FindBy(xpath = "//button[text()='Modify Search']")
    private WebElement btnModifySearch;


    public HotelPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 60);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void goTo() {
        driver.get(ReadProperties.readProperty("appUrl"));
        this.wait.until((d)->btnHotel.isDisplayed());
    }
    public void selectDestinaton(String txtDestination){
        //this.wait.until((d)->clickDestination.isDisplayed());
        this.clickDestination.click();
        this.wait.until((d)->inputDestination.isDisplayed());
        this.inputDestination.sendKeys(txtDestination);
        this.wait.until((d)->this.selectDestination.isDisplayed());
        this.selectDestination.click();

    }
    public void enterCheckInDate(String date){
        this.inputCheckIn.clear();
        this.inputCheckIn.sendKeys(date);
    }
    public void enterCheckOutDate(String date){
        this.inputCheckout.clear();
        this.inputCheckout.sendKeys(date);
    }
    public void selectAdults(String number){
        for(int i=1;i<=Integer.valueOf(number);i++){
            this.btnAdults.click();
        }
    }
    public void selectKids(String number){
        for(int i=1;i<=Integer.valueOf(number);i++){
            this.btnKids.click();
        }
    }


    @Override
    public void displayResults() {
        this.btnSearch.click();
        this.wait.until((d)->this.btnModifySearch.isDisplayed());

    }

}
