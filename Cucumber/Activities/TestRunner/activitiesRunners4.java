package TestRunner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.platform.engine.Constants;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("Features")   // folder inside src/test/resources

@ConfigurationParameter(
    key = Constants.GLUE_PROPERTY_NAME,
    value = "steps"   // package where step definitions are present
)

// 👉 Optional: use only if you have tags in feature file
@ConfigurationParameter(
    key = Constants.FILTER_TAGS_PROPERTY_NAME,
    value = "@smoke"   // change to your tag OR remove this block
)

public class activitiesRunners4 {
}