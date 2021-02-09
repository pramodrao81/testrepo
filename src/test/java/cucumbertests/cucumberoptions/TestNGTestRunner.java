package cucumbertests.cucumberoptions;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false,
        monochrome = false,
        features = {"src/test/java/cucumbertests/features"},
        glue={"cucumbertests/stepdefinations"},
        plugin = {
                "pretty",
                "html:target/site/cucumber-html",
                "json:target/cucumber1.json"
        },
        tags=  "@BookHotel"

)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
