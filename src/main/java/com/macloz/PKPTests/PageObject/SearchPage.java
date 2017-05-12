package com.macloz.PKPTests.PageObject;

import com.macloz.PKPTests.PageObject.PageObject;
import com.macloz.PKPTests.PageObject.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by maciek on 2017-05-09.
 */
public class SearchPage extends PageObject {

    @FindBy(id="from-station")
    private WebElement fromStation;

    @FindBy(id="to-station")
    private WebElement toStation;

    @FindBy(id="hour")
    private WebElement time;

    @FindBy(id="singlebutton")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id=\"accordion\"]/div[3]/a")
    private WebElement transporterToggler;

    @FindBy(xpath = "//*[@id=\"collapseThree\"]/fieldset/div[1]/div/label[2]")
    private WebElement allProvidersUnchecker;

    @FindBy(xpath="//*[@id=\"collapseThree\"]/fieldset/div[10]/div/label[2]")
    private WebElement skmProviderChecker;

    String firstTime;
    String secondTime;


    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void enterStations(String fromStationName, String toStationName){
        this.fromStation.clear();
        this.fromStation.sendKeys(fromStationName);

        this.toStation.clear();
        this.toStation.sendKeys(toStationName);
    }

    public void enterTime(String firstTime, String secondTime){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
//        String startTimeString = dateFormat.format(startTime);
//        String endTimeString = dateFormat.format(endTime);
        this.firstTime = firstTime;
        this.secondTime = secondTime;

        this.time.clear();
        this.time.sendKeys(firstTime);
    }

    public void unrollTransporters(){
        transporterToggler.click();
    }

    public void uncheckAllProviders(){
        allProvidersUnchecker.click();
    }

    public void checkSkmProvider(){
        skmProviderChecker.click();
    }

    public ResultPage submit(){
        submitButton.click();
        return new ResultPage(this.webDriver, this.firstTime, this.secondTime);
    }
}
