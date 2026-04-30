package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "StepDefinations","Hooks" },
plugin = {
	    "pretty",
	    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"} ,
monochrome = true)
public class CucumberRunner extends AbstractTestNGCucumberTests {
  
}
