package com.testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resource/features/practice.feature" },
		// tags = "@Positive_UnFreezeNSDLMaker",
		glue = { "apphook", "com/stepdef" }, dryRun = false, plugin = { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, monochrome = true)
public class TestRunner {

	@Test
	public void sampleTest() {
		System.out.println("Test executed!");
	}
}