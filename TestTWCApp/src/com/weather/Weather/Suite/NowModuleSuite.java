package com.weather.Weather.Suite;

import java.io.IOException;

import com.weather.Weather.test.NowModule;

public class NowModuleSuite extends  NowModule{

	/**
	 * This is a complete testSuite for Now module. This method should contain all the test cases that we want to run for Now module.
	 * In future implementation, the test cases that needs to be run will be coming for the DB.
	 * All the test cases will run in the order they are called.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void testSuite() throws InterruptedException, IOException {
		checkActivityOreintation();
		checkForAllLabelsAndValues();
		checkTempratureVisibleInCircle();
		checkTemparutreImageIcon();
		checkFeelsLikeValue();
		checkLastUpdatedTimeVisible();
		addNewLocationInDropDown();
		checkImageExpanded();
		checkImageContracted();
		launchBaseWeatherFragments();
		//checkTextOnSearchBox();
	}
}
