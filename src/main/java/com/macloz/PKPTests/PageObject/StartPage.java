package com.macloz.PKPTests.PageObject;

import com.macloz.PKPTests.PageObject.PageObject;
import com.macloz.PKPTests.PageObject.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by maciek on 2017-05-09.
 */
public class StartPage extends PageObject {

    @FindBy(id="subtitle1")
    private WebElement timeTableButton;


    public StartPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage goToSearchPage(){
        timeTableButton.click();
        return new SearchPage(this.webDriver);
    }
}
