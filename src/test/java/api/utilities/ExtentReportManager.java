package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
public ExtentSparkReporter sparkReporter; //UI of the Report
public ExtentReports extent; //Populate Common info on the report
//Creating test case entries in the report and update status of the test methods
public ExtentTest test; 

String repName;

public void onStart(ITestContext context)
{	
	//Create Time stamp
			/*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt= new Date();
			String currentdatetimestamp=df.format(dt);*/
			
			String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			
			//Create report file in folder
			repName = "Test-Report-" + timeStamp + ".html";
			sparkReporter= new ExtentSparkReporter(".\\reports\\"+repName); //Location of the folder
		  
			sparkReporter.config().setDocumentTitle("opencart Automation Report"); //Title of report
			sparkReporter.config().setReportName("opencart Funactional Testing"); //Name of the Report
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent= new ExtentReports();
			extent.attachReporter(sparkReporter);
			
			extent.setSystemInfo("Application", "Pet Store Users API");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Tester name", "Uma");
			extent.setSystemInfo("os", System.getProperty("os.name"));
			extent.setSystemInfo("User name", System.getProperty("user.name"));
}

public void onTestSuccess(ITestResult result)
{
	test= extent.createTest(result.getTestClass().getName()); //Create a new entry in the report 
	test.assignCategory(result.getMethod().getGroups()); 	// update the category of groups
	test.createNode(result.getName());
	test.log(Status.PASS, "Test case PASSED is:"+result); //update status P/F/S
  }

public void onTestFailure(ITestResult result) 
{
	test= extent.createTest(result.getTestClass().getName()); //Create a new entry in the report
	test.assignCategory(result.getMethod().getGroups()); // update the category of groups
	test.createNode(result.getName());
	test.log(Status.FAIL, "Test case FAILED is:"+result); //update status P/F/S
	test.log(Status.INFO, "Test case FAILED cause is:"+result.getThrowable().getMessage());	//Reason for test failure
}

//After each test skipped
	public void onTestSkipped(ITestResult result) 
	{
		test= extent.createTest(result.getTestClass().getName()); //Create a new entry in the report
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case SKIPPED is:"+result);
		test.log(Status.INFO, "Test case FAILED cause is:"+result.getThrowable().getMessage());
	  }
	
	//After completion on all test
	public void onFinish(ITestContext context) 
	 {
		extent.flush(); //it will append all the data in HTML
		  }

}
