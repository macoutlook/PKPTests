package com.macloz.PKPTests;

import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.testng.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by maciek on 2017-05-08.
 */


public class SeleniumTestsRunner {
    
    public static void main(String[] args) {
        String testNGXmlPath = "testng.xml";
        List<XmlSuite> testSuiteList;

        try
        {
            testSuiteList = (List <XmlSuite>)(new Parser(testNGXmlPath).parse());
            TestNG testNG = new TestNG();
            testNG.setXmlSuites(testSuiteList);
            testNG.run();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
