package cucumberRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/Features" }, glue = { "cucumberRunner" }, plugin = {
		"json:target/cucumber-report.json", "html:target/cucumber-report.html" }, monochrome = true)

public class TestRunner {
	public TestRunner() {
	}
}