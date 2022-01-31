package testRunner;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	//	features = {".//Features/Customers.feature",".//Features/Login.feature"},
				features = ".//Features",
		glue ={"stepDefinitions"},
		dryRun=true,
	 //   tags={"@regression"},
	   tags={"@regression, @sanity"},
		monochrome=true,
		plugin= {"pretty",
				 "html:test-output"}
		)
public class TestRun {
	
	

}
