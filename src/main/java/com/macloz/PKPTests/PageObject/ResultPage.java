package com.macloz.PKPTests.PageObject;

import com.macloz.PKPTests.PageObject.PageObject;
import com.macloz.Utilities.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by maciek on 2017-05-09.
 */

public class ResultPage extends PageObject {
    List<WebElementsPair>webElementsPairList;
    String classNamePattern = "focus_guiVCtrl_connection_detailsOut_select_C0-";
    String xpathPattern = "//*[@id=\"'className'\"]/td[4]/p['number']/span'spanNumber'/span[3]";
    String firstTime;
    String secondTime;


    public ResultPage(WebDriver driver, String firstTime, String secondTime) {
        super(driver);
        this.firstTime = firstTime;
        this.secondTime = secondTime;
        webElementsPairList = new ArrayList<WebElementsPair>();
    }

    public void iterateByClassElements(){
        int index = 0;
        String newXpathForDep;
        String newXpathForArr;
        WebElement webElementDep;
        WebElement webElementArr;
        WebElementsPair webElementsPair;

        do{
            try {
                newXpathForDep = xpathPattern.replace("'className'", classNamePattern + String.valueOf(index));
                newXpathForDep = newXpathForDep.replace("'number'", "1");
                newXpathForDep = newXpathForDep.replace("'spanNumber'", "");

                newXpathForArr = xpathPattern.replace("'className'", classNamePattern + String.valueOf(index));
                newXpathForArr = newXpathForArr.replace("'number'", "2");
                newXpathForArr = newXpathForArr.replace("'spanNumber'", "[1]");

                webElementDep = (new WebDriverWait(this.webDriver, 5))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(newXpathForDep)));
                webElementArr = (new WebDriverWait(this.webDriver, 5))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(newXpathForArr)));

                if (!Utils.timeComparison(webElementDep.getText(), this.firstTime) &&
                     Utils.timeComparison(webElementArr.getText(), this.secondTime)) {

                    webElementsPair = new WebElementsPair(webElementDep, webElementArr);
                    this.webElementsPairList.add(webElementsPair);
                }
                else if (Utils.timeComparison(this.secondTime, webElementDep.getText())) {
                    break;
                }
                index++;
            }
            catch (Exception e){
                break;
            }
        }while(true);
    }

    public void printTimePairs(){
        for(WebElementsPair elementPair : this.webElementsPairList){
            System.out.println(elementPair.webElementDep.getText() + ' ' + elementPair.webElementArr.getText());
        }
    }

    public List<String> getTimeTableOutputInString(){
        String timeDepAndArr;
        List<String> timesList = new ArrayList<String>();

        for(WebElementsPair elementPair : this.webElementsPairList){
            timeDepAndArr = elementPair.webElementDep.getText() + ' ' + elementPair.webElementArr.getText();
            timesList.add(timeDepAndArr);
        }
        return timesList;
    }

    private class WebElementsPair {
        WebElement webElementDep;
        WebElement webElementArr;

        private WebElementsPair(WebElement webElementDep, WebElement webElementArr){
            this.webElementDep = webElementDep;
            this.webElementArr = webElementArr;
        }
    }
}
