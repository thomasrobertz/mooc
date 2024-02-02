package runner;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

// This is a minimal TestRunner, it will pick up the features and run them together
// with associated steps. Can be thought of as an orchestrator, running a set of features.
// Note that this is a JUnit test, we could also run this as JUnit @Suite

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"features"},	// Where the features are kept.
									// Note that this resolves to cucumber/features in this project.

		glue = {"steps"},			// Where the steps (that actually run the tests) are kept.
									//     Note that this resolves to cucumber/src/test/java/steps

		// --- Optional properties (examples, there are more options available) ---

		plugin = {"pretty",			// Specifies the results output format
				"html:myReport.html"},

		dryRun = true,				// Can be true or false. If true, doesn't actually run
									// any code. Can be used for cucumber features/steps debugging.
									// Will also generate templates for any missing steps.

		tags = "@BDD1"				// Will restrict test runs to tagged Scenarios.
									// Also look at "name" option.

)
public class BasicTestRunner {
}
