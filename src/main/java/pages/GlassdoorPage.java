package pages;

import common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Utilities;
import java.awt.*;

public class GlassdoorPage extends BaseClass {
    @FindBy(xpath = "//input[@id='inlineUserEmail']")
    WebElement enterEmail;

    @FindBy(xpath = "//span[@class='css-8zxfjs evpplnh1']")
    WebElement continueWithEmailButton;

    @FindBy(id = "inlineUserPassword")
    WebElement enterPassword;

    @FindBy(xpath = "//span[@class='css-8zxfjs evpplnh1']")
    WebElement signInButton;

    public void enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(enterEmail));
        wait.until(ExpectedConditions.elementToBeClickable(enterEmail));
        Utilities.highlightElement(driver, enterEmail);
        enterEmail.sendKeys(email);
    }

    public void clickContinueWithEmailButton(){
        wait.until(ExpectedConditions.visibilityOf(continueWithEmailButton));
        wait.until(ExpectedConditions.elementToBeClickable(continueWithEmailButton));
        Utilities.highlightElement(driver, continueWithEmailButton);
        continueWithEmailButton.click();
    }
    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(enterPassword));
        wait.until(ExpectedConditions.elementToBeClickable(enterPassword));
        Utilities.highlightElement(driver, enterPassword);
        enterPassword.sendKeys(password);
    }

    public void clickSignInButton(){
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        Utilities.highlightElement(driver, signInButton);
        signInButton.click();

    }
    @FindBy(xpath = "//input[@id='sc.keyword']")
    WebElement jobTitleTextBox;

    @FindBy(xpath = "//input[@id='sc.location']")
    WebElement location;

    @FindBy(xpath = "//span[contains(text(),'Search')]")
    WebElement searchButton;
    @FindBy(xpath = "//*[@id=\"scBar\"]/div/button/span/span")
    WebElement searchButton2;
    @FindBy(xpath = "//*[@id=\"scBar\"]/div/button/span")
    WebElement searchButton3;

    @FindBy(xpath = "//*[@id=\"JAModal\"]/div/div[2]/span")
    WebElement cancelButton;

    public void enterJobTitle(String jobTitle) throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(jobTitleTextBox));
        wait.until(ExpectedConditions.elementToBeClickable(jobTitleTextBox));
        Utilities.highlightElement(driver, jobTitleTextBox);
        jobTitleTextBox.clear();
        jobTitleTextBox.sendKeys(jobTitle);

    }

    public void enterLocation(String City) throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(location));
        wait.until(ExpectedConditions.elementToBeClickable(location));

        Utilities.highlightElement(driver, location);
        location.sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);

        location.sendKeys(City);
    }
    public void clickSearchButton(){
        try{
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        Utilities.highlightElement(driver, searchButton);
        searchButton.click();
        }catch (Exception e){
            try{
            wait.until(ExpectedConditions.visibilityOf(searchButton2));
            wait.until(ExpectedConditions.elementToBeClickable(searchButton2));
            Utilities.highlightElement(driver, searchButton2);
            searchButton2.click();
            }catch (Exception e1){
                        wait.until(ExpectedConditions.visibilityOf(searchButton3));
                        wait.until(ExpectedConditions.elementToBeClickable(searchButton3));
                        Utilities.highlightElement(driver, searchButton3);
                        searchButton3.click();
            }
        }

    }
    public void clickCancel(){
        wait.until(ExpectedConditions.visibilityOf(cancelButton));
        wait.until(ExpectedConditions.visibilityOf(cancelButton));
        Utilities.highlightElement(driver, cancelButton);
        cancelButton.click();
    }


    public void setJobByIndex(int index){
         WebElement jobByIndex= driver.findElement(By.xpath("//*[@id=\"MainCol\"]/div[1]/ul/li["+index+"]"));
        wait.until(ExpectedConditions.visibilityOf(jobByIndex));
        wait.until(ExpectedConditions.elementToBeClickable(jobByIndex));

        Utilities.highlightElement(driver, jobByIndex);
        Utilities.scrollToElement(driver, jobByIndex);
        jobByIndex.click();
    }

    @FindBy(xpath = "//*[@id=\"MainCol\"]/div[2]/div/div[1]/button[7]/span")
    WebElement clickNextPage;
    public void clickNextPage(){
        wait.until(ExpectedConditions.visibilityOf(clickNextPage));
        wait.until(ExpectedConditions.elementToBeClickable(clickNextPage));
        Utilities.highlightElement(driver, clickNextPage);
        Utilities.scrollToElement(driver, clickNextPage);
        clickNextPage.click();
    }


    @FindBy(xpath = "//span[contains(text(),'Apply Now')]")
    WebElement applyNow;

    public void clickApplyNow(){
        wait.until(ExpectedConditions.visibilityOf(applyNow));
        wait.until(ExpectedConditions.elementToBeClickable(applyNow));
        String text=applyNow.getText();
        if(text.equals("Apply Now")){
            Utilities.highlightElement(driver, applyNow);
            Utilities.scrollToElement(driver, applyNow);
            applyNow.click();
        }
    }

    @FindBy(xpath = "//*[@id=\"JDCol\"]/div/article/div/div[1]/div/div/div[1]/div[3]/div[1]/div[1]/div")
    WebElement companyName1;

    public String getCompanyName(){
        wait.until(ExpectedConditions.visibilityOf(companyName1));
        wait.until(ExpectedConditions.elementToBeClickable(companyName1));
        Utilities.scrollToElement(driver, companyName1);
        Utilities.highlightElement(driver, companyName1);
        String companyName=companyName1.getText();
        return companyName;
    }
    @FindBy(xpath = "//*[@id=\"JDCol\"]/div/article/div/div[1]/div/div/div[1]/div[3]/div[1]/div[2]")
    WebElement jobPostName;

    public String getJobPostName(){
        wait.until(ExpectedConditions.visibilityOf(jobPostName));
        wait.until(ExpectedConditions.elementToBeClickable(jobPostName));
        Utilities.scrollToElement(driver, jobPostName);
        Utilities.highlightElement(driver, jobPostName);
        String jobPost=jobPostName.getText();
        return jobPost;
    }
    @FindBy(xpath = "//*[@id=\"JDCol\"]/div/article/div/div[1]/div/div/div[1]/div[3]/div[1]/div[3]")
    WebElement getLocation;

    public String getJobLocation(){
        wait.until(ExpectedConditions.visibilityOf(getLocation));
        wait.until(ExpectedConditions.elementToBeClickable(getLocation));
        Utilities.scrollToElement(driver, getLocation);
        Utilities.highlightElement(driver, getLocation);
        return getLocation.getText();
    }
    @FindBy(xpath = "//*[@id=\"JDCol\"]/div/article/div/div[1]/div/div/div[1]/div[3]/div[1]/div[4]")
    WebElement salary;

    public String getApporxSalary(){
        String text=null;
        wait.until(ExpectedConditions.visibilityOf(salary));
        wait.until(ExpectedConditions.elementToBeClickable(salary));
        if(salary.isDisplayed() && salary.isEnabled()) {
            Utilities.scrollToElement(driver, salary);
            Utilities.highlightElement(driver, salary);
            text=salary.getText();
        }
        return text;
    }
}
