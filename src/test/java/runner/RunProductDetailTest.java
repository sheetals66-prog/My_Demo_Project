package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestNG Cucumber runner to execute only scenarios/features tagged with @ProductDetail.
 * Place this class in the same package as other runners so IDE/TestNG can discover it.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"StepDefinations", "Hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/productdetail-report.html"},
        monochrome = true,
        tags = "@ProductDetail"
)
public class RunProductDetailTest extends AbstractTestNGCucumberTests {
    // empty - inherits behavior from AbstractTestNGCucumberTests
}
