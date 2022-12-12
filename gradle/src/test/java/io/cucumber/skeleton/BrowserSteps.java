package io.cucumber.skeleton;

import dev.failsafe.internal.util.Assert;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class BrowserSteps {
    private ChromeDriver driver;

    @When("I enter the username {string}")
    public  void iEnterTheUsername(String userName){

        var searchEle = driver.findElement(By.name("username"));
        searchEle.sendKeys(userName);
    }
    @When("I enter the password {string}")
    public  void iEnterThePassword(String password){

        var searchEle = driver.findElement(By.name("password"));
        searchEle.sendKeys(password);
    }


    @Given("I open a browser")
    public  void OpenBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));



    }


    @Given("I go to {string}")
    public  void goTo(String url ){
        System.out.println(url);
        driver.get(url);
           }

    @Then("I click the link {string}")
    public void iClickTheLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }


    @Then("I close a browser")
    public void iCloseABrowser() {
        driver.quit();
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        driver.findElement(By.tagName("button")).click();

    }

    @Then("I should see a success message")
    public void iShouldSeeASuccessMessage() {
        driver.findElement(By.className("success"));
    }

    @After
    public void afterScenario(){
        try {
            driver.quit();
        } catch (Exception err){


        }
        //    System.out.println("This will run after the Scenario");
    }

    @Then("I should see an error message, {string}")
    public void iShouldSeeAnErrorMessage(String arg0) {

       // String x = "foo bar";
       // Assert.assertTrue(x.contains("foo"));
        String onPageText = driver.findElement(By.className("error")).getText();
        // assertThat(person.getName(), containsString("myName"));
        assertThat(onPageText, containsString(arg0));

    }
}
