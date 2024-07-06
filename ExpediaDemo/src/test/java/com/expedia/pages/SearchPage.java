package com.expedia.pages;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.expedia.admin.ExpediaAllMethodsPage;
import com.expedia.admin.PackagesMethodPage;
import com.expedia.admin.StaysMethodPage;
import com.expedia.admin.StaysMethodPage2;
import com.expedia.admin.CarsMethodsPage;
import com.expedia.init.Base;
import com.expedia.init.Common;

public class SearchPage extends Base {
	Common common = new Common(driver);

	//	@Test()
	//	public void searchByKeywordAndVerify() throws InterruptedException {
	//		driver.get(configFileReader.getURL());
	//		expediaAllMethodsPage.searchByKeyword();
	//		expediaAllMethodsPage.verifySearchdKeywordPresentInPropertyDescription();
	//	}


	/**
	 * Methods of Stays, Flights, Cars, Packages
	 * @throws Exception 
	 */

	@Test(dataProvider = "Flights") 
	public void searchForFlights(String leavingfrom, String goTo) throws Exception 
	{		
		ExpediaAllMethodsPage.goToFlightsSearch(leavingfrom,goTo); 
	}

	@Test(dataProvider = "Cars") 
	public void searchForCars(String pickCar, String dropCar)
	{ 
		CarsMethodsPage.goToCarsSearch(pickCar, dropCar);
	}

	@Test(dataProvider = "Packages")  
	public void searchForPackages(String leavingfromPackInput, String goToPackInput) 
	{
		PackagesMethodPage.goToPackagsSearch(leavingfromPackInput,goToPackInput); 
	}


	@Test(dataProvider = "Stays")  
	public void searchForStays(String stayGoInput) 
	{
		StaysMethodPage2.goToStaysSearch(stayGoInput);
	}

	@DataProvider(name = "Flights")
	public Object[][] getFlightsDetails() {
		return testDatacsv("flights");
	}

	@DataProvider(name = "Packages")
	public Object[][] getPackageDetails() {
		return testDatacsv("packages");
	}

	@DataProvider(name = "Cars")
	public Object[][] getCarsDetails() {
		return testDatacsv("cars");
	}

	@DataProvider(name = "Stays")
	public Object[][] getStaysDetails() {
		return testDatacsv("stays");
	}

}
