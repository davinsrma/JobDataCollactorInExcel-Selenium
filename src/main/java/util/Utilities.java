package util;

import common.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Utilities extends BaseClass {

    protected static JavascriptExecutor jse= (JavascriptExecutor) BaseClass.driver;
    public static void highlightElement(WebDriver driver, WebElement element) {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    public static void zoomControl(WebDriver driver,int zoomSize){
        jse = (JavascriptExecutor) driver;
        jse.executeScript("document.body.style.zoom='"+zoomSize+"%'");
    }
    public static void scrollToElement(WebDriver driver, WebElement element) {

        try {
            jse=(JavascriptExecutor) driver;
            jse.executeScript((ScriptKey) driver, "arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public static void takeScreenShot(WebDriver driver, String filename) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile,new File("./JobImages/"+filename+""+getCurrentDateTimeStamp()+".png"));
    }


    public static void clearDirectory(String Dir) throws NullPointerException {
        File file = new File(Dir);
        String[] myFiles;

        try {
            if (file.isDirectory()) {
                myFiles = file.list();
                //			for (int i = 0; i < myFiles.length; i++) {
                assert myFiles != null;
                for (String name : myFiles) {
                    File myFile = new File(file, name);
                    //noinspection ResultOfMethodCallIgnored
                    myFile.delete();
                }
            } else if (file.isFile()) {
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCount() throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("./Data/config.properties");
        prop.load(fis);
        int count= Integer.parseInt(prop.getProperty("howManyPageWeWantToSearch"));
        return count;
    }

    public static String getCurrentDateTimeStamp() {
        Date objDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MMM_dd_hh_mm_ss");
        objDate = new Date();
        // Current System Date and time is assigned to objDate
        return (sdf.format(objDate));
    }

    public static void sendEmailReport(String fileName, List<String> summary) {

        // Create object of Property file
        Properties props = new Properties();

        // this will set host of server- you can change based on your requirement
        props.put("mail.smtp.host", "smtp.gmail.com");

        // set the port of SMTP server
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        // set socket factory
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // set the authentication to true
//        props.put("mail.ssl.auth", "true");
        props.put("mail.smtp.auth", "true");


        // This will handle the complete authentication
        Session session = Session.getDefaultInstance(props,

                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("davinsrmaa@gmail.com", "depksbavxmgemsbi");
                    }

                });
        try {
            String SystemDetails;
            // Create object of MimeMessage class
            Message message = new MimeMessage(session);
            try {
                SystemDetails = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            // Set the from address
            message.setFrom(new InternetAddress("davinsrmaa@gmail.com"));
            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("davinsrma@gmail.com"));
//            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("davinsrma@gmail.com"));

            // Add the subject link
            message.setSubject("**** UI Automation Rep - Device: "+SystemDetails+" ReportId: " + getCurrentDateTimeStamp() + " On Browser: "+TestBedBrowser+ "****");

            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();
            String tmp ="";
            for (String s:summary
            ) {
                tmp = tmp +s+"\t";
            }
            String sMessage = tmp;
            // Set the body of email
            messageBodyPart1.setText(sMessage);
            // Create another object to add another content
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            // Mention the file which you want to send
//            String filename = System.getProperty("user.dir") + "/Results/emailable-report.html";

            // Create data source and pass the filename
            DataSource source = new FileDataSource(fileName);
            // set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));
            // set the file
            messageBodyPart2.setFileName(fileName);

            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart1);

            // set the content
            message.setContent(multipart);
            // finally send the email
            Transport.send(message);
//
            System.out.println("=====Email Sent=====");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
