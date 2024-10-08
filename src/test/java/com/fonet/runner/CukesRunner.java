package com.fonet.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/fonet/step_defs",
        dryRun = false,
        plugin = "html:cucumberReport.html",
        publish = false,
        tags = ""
)
public class CukesRunner {
}
