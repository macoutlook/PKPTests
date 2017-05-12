package com.macloz.PKPTests; /**
 * Created by maciek on 2017-05-06.
 */

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//import static org.junit.Assert.*;
//import org.junit.Test;
import com.macloz.PKPTests.PageObject.ResultPage;
import com.macloz.PKPTests.PageObject.SearchPage;
import com.macloz.PKPTests.PageObject.StartPage;
import com.macloz.Utilities.PdfParser;
import com.macloz.Utilities.Utils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;

import java.util.List;


public class SeleniumTests {

    private String webDriverPath;
    private String resultsPath;
    private WebDriver webDriver;

    @Parameters({ "webDriverPath", "resultsPath" })
    @BeforeTest
    protected void setUp(String webDriverPath, String resultsPath){
        this.webDriverPath = webDriverPath;
        this.resultsPath = resultsPath;
        System.setProperty("webdriver.chrome.driver", this.webDriverPath);
    }

    @AfterTest
    protected void tearDown(){
        this.webDriver.manage().deleteAllCookies();
    }

    @BeforeMethod
    protected void beforeTestMethod(){
        this.webDriver = new ChromeDriver();
    }

    @AfterMethod
    protected void afterMethod(){
        this.webDriver.quit();
    }

    @Test
    public void testTakeTimeTable() {

        String resultFilePath = "C:\\Users\\maciek\\IdeaProjects\\PKPTests\\resources\\polaczenie_kolejowe.pdf";
        String parsedPdfInString;
        List<String> resultsTimesListFromInputFile = null;
        List<String> resultsTimesListFromTestExecution = null;

        parsedPdfInString = Utils.parsePdfFile(resultFilePath);
        resultsTimesListFromInputFile = Utils.extractTimePairsFromPdfOutput(parsedPdfInString,
                "17:00", "19:00");

        this.webDriver.get("http://www.pkp.pl");
        this.webDriver.manage().window().maximize();
        try {
            StartPage startPage = new StartPage(this.webDriver);

            SearchPage searchPage = startPage.goToSearchPage();
            searchPage.enterStations("Warszawa Służewiec", "Warszawa Lotnisko Chopina");
            searchPage.enterTime("17:00", "19:00");
            searchPage.unrollTransporters();
            searchPage.uncheckAllProviders();
            searchPage.checkSkmProvider();

            ResultPage resultPage = searchPage.submit();

            resultPage.iterateByClassElements();
            resultPage.printTimePairs();
            resultsTimesListFromTestExecution = resultPage.getTimeTableOutputInString();

            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (resultsTimesListFromTestExecution.isEmpty()){
            Assert.fail("resultsTimesListFromTestExecution was not initiated" );
        }
        for(int ind = 0; ind < resultsTimesListFromInputFile.size(); ind++) {
            Assert.assertEquals(resultsTimesListFromInputFile.get(ind), resultsTimesListFromTestExecution.get(ind),
                    "Result are in compliance with input file");
        }
    }


    @Test(expectedExceptions = AssertionError.class)
    public void testSimulateNegative() {

        String resultFilePath = "C:\\Users\\maciek\\IdeaProjects\\PKPTests\\resources\\polaczenie_kolejowe.pdf";
        String parsedPdfInString;
        List<String> resultsTimesListFromInputFile = null;
        List<String> resultsTimesListFromTestExecution = null;

        parsedPdfInString = Utils.parsePdfFile(resultFilePath);
        resultsTimesListFromInputFile = Utils.extractTimePairsFromPdfOutput(parsedPdfInString,
                "17:00", "19:00");

        this.webDriver.get("http://www.pkp.pl");
        this.webDriver.manage().window().maximize();
        try {
            StartPage startPage = new StartPage(this.webDriver);

            SearchPage searchPage = startPage.goToSearchPage();
            searchPage.enterStations("Warszawa Służewiec", "Warszawa Lotnisko Chopina");
            searchPage.enterTime("17:00", "19:00");
            searchPage.unrollTransporters();
            searchPage.uncheckAllProviders();
            searchPage.checkSkmProvider();

            ResultPage resultPage = searchPage.submit();

            resultPage.iterateByClassElements();
            resultPage.printTimePairs();
            resultsTimesListFromTestExecution = resultPage.getTimeTableOutputInString();

            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.webDriver.quit();

        if (resultsTimesListFromTestExecution.isEmpty()){
            Assert.fail("resultsTimesListFromTestExecution was not initiated" );
        }

        //Data is corrupted here for first element of results list
        resultsTimesListFromTestExecution.add(0, "12:00 15:00");

        Assert.assertEquals(resultsTimesListFromInputFile.get(0), resultsTimesListFromTestExecution.get(0),
                           "Compared results shouldn't be equal as the data was corrupted");
    }
}
