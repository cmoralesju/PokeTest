package stepDefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "json:target/cucumber.json" }, 
tags = {},
features = {"src/test/resources"},
glue = {},
monochrome =false)
public class RunCakeTest {
}
