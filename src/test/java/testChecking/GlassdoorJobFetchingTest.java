package testChecking;

import common.BaseClass;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.GlassdoorPage;
import util.Excel1;
import util.Utilities;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class GlassdoorJobFetchingTest extends BaseClass {
    GlassdoorPage loginPage;
    Properties properties=new Properties();
    FileInputStream fis=new FileInputStream("./Config/config.properties");
    int count=1;
    public GlassdoorJobFetchingTest() throws FileNotFoundException {
    }


    @Test
    public void enterJobAndLocationAndClickSearch() throws IOException,  AWTException {
        properties.load(fis);
        loginPage= PageFactory.initElements(BaseClass.driver, GlassdoorPage.class);

        loginPage.enterEmail(properties.getProperty("glassdoorEmail"));
        loginPage.clickContinueWithEmailButton();
        loginPage.enterPassword(properties.getProperty("glassdoorPassword"));
        loginPage.clickSignInButton();

        loginPage.enterJobTitle(properties.getProperty("job"));
        loginPage.enterLocation(properties.getProperty("location"));
        loginPage.clickSearchButton();
        try{
        loginPage.clickCancel();
        }catch (Exception e){}
        Utilities.clearDirectory(properties.getProperty("imageDirectory"));

        for(int i=1;i<=30;i++){
        String mainTab=driver.getWindowHandle();
        try{
                loginPage.setJobByIndex(i);

            }catch (Exception e){
                    System.out.println("All available job already captured");
                    i=31;
                }
            try {
            loginPage.clickApplyNow();
            String companyName=loginPage.getCompanyName();
            String postName=loginPage.getJobPostName();
            String jobLocation=loginPage.getJobLocation();
            String approxSalary=loginPage.getApporxSalary();



            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());


            driver.switchTo().window(tabs.get(1));

            Thread.sleep(2000);

                String directUrl=driver.getCurrentUrl();
                String []arr={companyName,postName,jobLocation,approxSalary,directUrl};
                Excel1.writeExcel("./JobDataExcel/JobData.xlsx", "Data", arr);
                Utilities.zoomControl(driver, 50);
                Utilities.takeScreenShot(driver,companyName+"_"+postName);
                driver.close();

                driver.switchTo().window(mainTab);
        }catch (Exception e){
            }
                if(i==30){
                loginPage.clickNextPage();
                i=1;
                count++;
                if(count>Utilities.getCount()){
                break;
                }
            }
        }
    }
}
