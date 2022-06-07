package runner;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty"},
    features= {"../Assignment_Modulr/Featurefiles/Login.feature"},
    glue = {"../Assignment_Modulr/Featurefiles/Login.feature"}
    
    )



public class runner {

}
