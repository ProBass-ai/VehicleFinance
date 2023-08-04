package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestHelper;

import java.time.Duration;
import java.util.List;

public class VehicleFinancePage {
    private WebDriver driver;
    private By byVehiclePurchasePriceInput = By.xpath("//label[@class='input-field-label' and text()='Vehicle purchase price']/following-sibling::input");
    private By byDepositAmountDiv = By.xpath("//label[contains(text(), 'Deposit amount')]/following-sibling::div");
    private By byBalloonPaymentDiv = By.xpath("//label[contains(text(), 'Balloon payment')]/following-sibling::div");
    private By byDepositManualInput = By.xpath("//label[contains(text(), 'Deposit amount')]/following-sibling::div//following::div[@class='wol-manual-input']/input[@tabindex='0']");
    private By byCalculateDeposit = By.xpath("\"//label[contains(text(), 'Deposit amount')]/following-sibling::div//following::div[@class='wol-manual-input']/button[@tabindex='0']");
    private By byPaymentTermDropDown = By.xpath("//label[text()='Payment term']/following::select");
    private By byEstimatedMonthlyPayment = By.xpath("//h6[text()='Estimated monthly repayment*']//following-sibling::wb-number-display//following::div[@class='formatter price']");
    private By byAmountToFinance = By.xpath("//div[text()='AMOUNT TO FINANCE:']//following-sibling::div");




    public VehicleFinancePage(WebDriver driver){
        this.driver = driver;
    }

    public void setVehiclePrice(String price){
        TestHelper.sendKeys(driver ,byVehiclePurchasePriceInput, price);
    }

    public void setDeposit(String amount){
        TestHelper.click(driver, byDepositAmountDiv);
        TestHelper.sendKeys(driver, byDepositManualInput, amount);
    }

    public void setTerm(String term){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byBalloonPaymentDiv));
        Select select = new Select(element);
        select.selectByVisibleText(term);
    }

    public void setBalloonPayment(String payment){
        TestHelper.click(driver, byBalloonPaymentDiv);
//        TestHelper.sendKeys(driver, payment);
    }

    public String getEstimatedMontlyInstallment(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(byEstimatedMonthlyPayment));
        List<WebElement> elements = driver.findElements(byEstimatedMonthlyPayment);
        StringBuilder payment = new StringBuilder();

        for (WebElement element1: elements){
            payment.append(element1.getText().strip());
        }
        return payment.toString();
    }

    public String getAmountToFinance(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byEstimatedMonthlyPayment));
        return element.getText();
    }






}
