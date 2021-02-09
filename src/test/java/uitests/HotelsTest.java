package uitests;

import org.testng.SkipException;
import uipages.HotelPage;
import org.testng.annotations.Test;
import util.datasource.TestDataProvider;

import java.util.Map;

public class HotelsTest extends BaseTest {

    
    @Test(dataProvider = "getExceldata", dataProviderClass = TestDataProvider.class)
    public void searchHotel(Map<String, Object> data){

        HotelPage hotelPage = new HotelPage(driver);
        if (data.get("RunMode").equals("Y") && data.get("TCID").equals("searchHotel")) {
            hotelPage.goTo();
            hotelPage.selectDestinaton(data.get("Destination").toString());
            hotelPage.enterCheckInDate(data.get("CheckInDate").toString());
            hotelPage.enterCheckOutDate(data.get("CheckOutDate").toString());
            hotelPage.selectAdults(data.get("Adults").toString());
            hotelPage.selectKids(data.get("Kids").toString());
            hotelPage.displayResults();
        }else{
            throw new SkipException("Test script RunMode is set to No");
        }
    }
}
