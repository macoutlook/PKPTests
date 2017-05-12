package com.macloz.PKPTests.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maciek on 2017-05-08.
 */
public class PageObject {

    protected WebDriver webDriver;


    public PageObject(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }
}

