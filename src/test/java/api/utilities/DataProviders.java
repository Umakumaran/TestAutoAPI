package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	//DataProvider1
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//TestData//Userdata.xlsx";	 //taking xl file from testdata
		
		ExcelUtils xlutil=new ExcelUtils(path); //creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1); //Pass any rowno to count cells in that row
		
		String apidata[][]=new String[totalrows][totalcols]; //Created 2D array which can store the data from excel
		
		for(int i=1; i<totalrows;i++) //1   //read the data from xl storing in 2D array
		{
			for(int j=0;j<totalcols; j++) //0  //i is row j is col
			{
				apidata[i-1][j]=xlutil.getCellData("Sheet1",i,j); //1,0
			}
		}
		return apidata; //Returning 2D array
	}

//DataProvider2
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//TestData//Userdata.xlsx";	 //taking xl file from testdata
		
		ExcelUtils xlutil=new ExcelUtils(path); //creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		
		String apidata[]=new String[totalrows]; //Created 2D array which can store the data from excel
		
		for(int i=1; i<totalrows;i++) //1   //read the data from xl storing in 2D array
		{
				apidata[i-1]=xlutil.getCellData("Sheet1",i,1); //1,0
		}
		return apidata; //Returning 2D array
	}
}

//DataProvider3

//DataProvider4