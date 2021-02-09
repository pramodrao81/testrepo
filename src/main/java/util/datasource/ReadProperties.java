package util.datasource;

//-----------------------------------------------------------------------------------------------------------
//Description    :   Read properties file
//Creator        :   Pramod
//Create         :
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    /**
     * This function will return the value for the provided property key
     *
     * @param propertyKey
     *            the key value from Testing.properties file
     * @return String containing the path mentioned in testing.properties for
     *         specific property key
     */

     public static String readProperty(String propertyKey) {
        Properties prop = new Properties();
        File dir1 = new File(".");
        String strBasePath = null;
        String val = null;
        try {
            // read the data from the properties file
            strBasePath = dir1.getCanonicalPath();
            prop.load(new FileInputStream(strBasePath +File.separator+"config"
                                                  + File.separator + "Testing.properties"));
            val = prop.getProperty(propertyKey);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in readProperty: " + e.getMessage());
        } catch (IOException e) {
            System.out.println(
                    "IOException in readProperty: " + e.getMessage());
        }
        return val;
    }
}