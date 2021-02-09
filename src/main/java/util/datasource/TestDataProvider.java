package util.datasource;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestDataProvider {

    @DataProvider(name = "getExceldata")
    public Object[][] getTestData() throws IOException {
        return ExcelReader.getExcelData("TestData.xlsx", "Main");
    }
}






