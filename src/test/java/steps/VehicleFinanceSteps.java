package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.VehicleFinancePage;

import static junit.framework.TestCase.assertEquals;

public class VehicleFinanceSteps {
    private WebDriver driver;
    private VehicleFinancePage vehicleFinancePage;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //the sandbox removes unnecessary privileges from the processes that don't need them in Chrome, for security purposes. Disabling the sandbox makes your PC more vulnerable to exploits via webpages, so Google don't recommend it.
        options.addArguments("--no-sandbox");
        //"--disable-dev-shm-usage" Only added when CI system environment variable is set or when inside a docker instance. The /dev/shm partition is too small in certain VM environments, causing Chrome to fail or crash.
        options.addArguments("--disable-dev-shm-usage");
        if(!System.getProperty("os.name").contains("Windows")){
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        vehicleFinancePage = new VehicleFinancePage(driver);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("the user is on the vehicle finance page")
    public void theUserIsOnTheVehicle() {
        driver.get("https://www.wesbank.co.za/home/calculate");
    }


    @Given("the vehicle purchase is {string}")
    public void theVehiclePurchaseIs(String amount) {
        vehicleFinancePage.setVehiclePrice(amount);
    }


    @And("the deposit is {string}")
    public void theDepositIs(String amount) {
        vehicleFinancePage.setDeposit(amount);
    }

    @And("balloon payment is {string}")
    public void balloonPaymentIs(String pecerntage) {
        vehicleFinancePage.setBalloonPayment(pecerntage);
    }

    @And("the payment term is {string}")
    public void thePaymentTermIs(String term) {
        vehicleFinancePage.setTerm(term);
    }


    @And("interest rate is {string}")
    public void interestRateIs(String percentage) {
    }

    @When("the user calculates")
    public void theUserCalculates() {

    }

    @Then("the amount to finance is {string}")
    public void theAmountToFinanceIs(String amount) {
        assertEquals(amount, vehicleFinancePage.getAmountToFinance());
    }
}
