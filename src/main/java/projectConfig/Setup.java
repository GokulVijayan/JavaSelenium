package projectConfig;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.xml.sax.SAXException;

import Reports.Report;
import Reports.ReportModifier;
import Reports.TestReportSteps;
import Utilities.ConfigFile;
import Utilities.Constant;


public class Setup {
	public static WebDriver driver;
    public static List<TestReportSteps> finalreport=new ArrayList<TestReportSteps>();
    public static List<String> screenshotList = new ArrayList<String>();
    public static String testObjective;
    public static String scriptName;

    public static JSONArray inputjson;
    @BeforeTest
	public static void BeforeEachTest() throws Exception
	{
		  Constant.SetConfig();
		  driver= ConfigFile.Init();
	}
	
	
	@AfterTest
	public static void AfterEachTest() throws Exception
	{
    Report.WriteResultToHtml(driver, finalreport, screenshotList, testObjective, scriptName,ReportModifier.scriptCount);
    driver.quit();
    finalreport.clear();
    screenshotList.clear();
	}

	@BeforeSuite(alwaysRun=true)
	public static void GetScriptCount() throws SAXException, ParserConfigurationException
	{
		ReportModifier.setUpConfiguration();
	}
}
