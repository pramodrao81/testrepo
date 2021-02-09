package util.datasource;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelReader {

    static XSSFWorkbook book = null;
    static XSSFSheet sheet = null;
    static XSSFRow row = null;
    static XSSFCell cel = null;
    static Map<String, Object> map = null;
    static FileInputStream fileInputStream = null;
    static File file = null;
    static String path;

    public static Object[][] getExcelData(String fileName, String sheetName) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/" + fileName);
        fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(sheetName);
        row = sheet.getRow(0);
        String[] header = new String[row.getLastCellNum()];
        Object[][] data = new Object[sheet.getLastRowNum()][1];
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            cel = row.getCell(i);
            header[i] = cel.getStringCellValue();
        }

        for (int j = sheet.getFirstRowNum() + 1; j <= sheet.getLastRowNum(); j++) {
            row = sheet.getRow(j);
            map = new LinkedHashMap<String, Object>();

            for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                cel = row.getCell(k);

                switch (cel.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        map.put(header[k], cel.getStringCellValue());
                        break;

                    case XSSFCell.CELL_TYPE_NUMERIC:
                        map.put(header[k], cel.getNumericCellValue());
                        break;

                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        map.put(header[k], cel.getBooleanCellValue());
                        break;

                }
            }
            data[j - 1][0] = map;

        }

        return data;

    }

}
