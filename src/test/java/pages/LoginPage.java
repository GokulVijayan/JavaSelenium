package pages;





import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import GenericComponents.ReusableComponents;
import Reports.CaptureScreenshot;
import Reports.TestReportSteps;
import Utilities.ConfigFile;



public class LoginPage {
	public static JSONObject jObject;
    public static List<String> screenshotList = new ArrayList<String>();

	 
	
	 public  List<TestReportSteps> LoginToApplication(WebDriver driver,JSONObject inputjson) throws Exception
		{
		 List<TestReportSteps> listOfReport=new ArrayList<TestReportSteps>();
         screenshotList.clear();
         int step = 0;
         String objective = "To verify that user is able to login to the application";
         jObject = ConfigFile.RetrieveUIMap("LoginPageSelector.json");

         try
         {
             //Enter username
             listOfReport.add(ReusableComponents.GenerateReportSteps("Enter username","username", objective, step));
             ReusableComponents.SendKeys(driver, "Id", jObject.get("username").toString(), inputjson.get("username").toString());
 	         listOfReport.get(step++).actualResultFail = "";

             
             //Enter password
             listOfReport.add(ReusableComponents.GenerateReportSteps("Enter password"+" Capture Screenshot.", "password", objective, step));
             ReusableComponents.SendKeys(driver, "Id", jObject.get("password").toString(), inputjson.get("password").toString());
 	         listOfReport.get(step++).actualResultFail = "";
             screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "EnterPassword"));

  	       
  	        //Click on the 'Login' button
             listOfReport.add(ReusableComponents.GenerateReportSteps("Click on the Login button"+" Capture Screenshot.", "", objective, step));
             ReusableComponents.Click(driver, "XPath", jObject.get("loginButton").toString());
 	         listOfReport.get(step++).actualResultFail = "";
             screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "LoginSuccess1"+ ConfigFile.GetCurrentDateTime()));

             
         }
         catch (Exception e)
         {
             System.out.println("LoginToApplication failed at step: "+(step+1)+" "+e);
             if(!listOfReport.get(step).GetStepDescription().toLowerCase().contains("screenshot"))          
             listOfReport.get(step).stepDescription=listOfReport.get(step).stepDescription.concat(" Capture Screenshot.");           
             screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "Error"+ ConfigFile.GetCurrentDateTime()));

         }

         return listOfReport;
		
		}
	 
	 
	 public List<String> getFilePath()
		{
			return screenshotList;
		}
	 
	 
	 
}
