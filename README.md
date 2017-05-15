------------------------------------------------------------------------
							PKPTests Project v1.0
------------------------------------------------------------------------

Project realizes automatic test cases implemented in selenium with TestNG framework support. Project is based on maven. Configuration file for dependencies building is named pom.xml, and you can find that in main project directory.
Project is implemented under java 1.8

------------------------------------------------------------------------
							Requirements
------------------------------------------------------------------------

For using application with test cases there is need to install:
1) Web driver for executing tests. For instance chromedriver:
	For Windows:
	https://chromedriver.storage.googleapis.com/2.29/chromedriver_win32.zip
	For Linux:
	https://chromedriver.storage.googleapis.com/2.29/chromedriver_linux64.zip
2) Java Development Kit. Java 8 is strongly recommended:
	For Windows:
	http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-windows-x64.exe
	For Linux:
	http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz
3) Python 2.7 interpreter:
	For Windows:
	https://www.python.org/ftp/python/2.7/python-2.7.amd64.msi
	For Linux:
	On every linux distribution Python 2.7 interpreter is available by default.
4) Web browser for executing tests.
Recommended version of Chrome is above 56.0.0
	
------------------------------------------------------------------------
							Configuration
------------------------------------------------------------------------

The file which is used for configuring project is testng.xml
Before running application 'webDriverPath' has to be specified:
	<parameter name="webDriverPath" value="proper_path_for_webdriver" />
	(Currently implementation allows only for running tests on Chrome. In near future range of web drivers will be extended)
Addtionally these parameters can be defined depending on needs:
	<parameter name="inputResults" value="resources\\polaczenie_kolejowe.pdf" /> --> Actually in repo that file is defined for subsequent parameters
    <parameter name="firstTime" value="17:00" />
    <parameter name="secondTime" value="19:00" />
    <parameter name="departureStation" value="Warszawa Służewiec" />
    <parameter name="arrivalStation" value="Warszawa Lotnisko Chopina" />
	
------------------------------------------------------------------------
							How to run
------------------------------------------------------------------------

There are at least two ways of running project:
1) Import maven project by choosing proper directory in your IDE. Maven project will be automaticaly loaded.
All dependencies from testng.xml file will be binded.
Run PKPTests\src\test\java\com\macloz\PKPTests\SeleniumTestsRunner.java or testng.xml directly from IDE.
2) Run run.py file. Program will be started. Script is universal for Windows and Linux.
3) ./run_bash.sh directly command in Linux terminal.
4) Run run_bat.bat in Windows command line.
