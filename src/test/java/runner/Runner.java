package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "StepDefinations","Hooks" }, plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", monochrome = true)
public class Runner extends AbstractTestNGCucumberTests {

}
