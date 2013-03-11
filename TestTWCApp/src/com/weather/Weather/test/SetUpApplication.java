package com.weather.Weather.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.Constants.WeatherConstants;
import com.weather.Weather.Utility.UtilityClass;
import com.weather.Weather.activity.WeatherController;
import com.weather.Weather.R;

public class SetUpApplication extends ActivityInstrumentationTestCase2<WeatherController> {
	
	public Solo solo;
	UtilityClass utilObj = new UtilityClass();
	
	public SetUpApplication() {
		super(WeatherController.class);
	}

	/**
	 * This is the initial setup method that initializes the Solo object and also handles the initial pop-ups coming when the application is launched,
	 * enters city if not present.
	 */
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		Integer counter=handleInitialPopUps();
		if(counter!=0){
			enterCityInTextBox(WeatherConstants.CITY_ATLANTA);
		}
		
		handleDismissPopup(WeatherConstants.DISMISS_TEXT);
		checkForNoLocation();

	}

	/**
	 * Handles the initial pop-up coming when the applicaiton is launched.
	 * @return
	 */
	public Integer handleInitialPopUps(){
		int counter=0;
		if (solo.searchText(WeatherConstants.FIND_MY_LOCATION, true)) {
			solo.clickOnText(WeatherConstants.LATER_TEXT);
			counter++;
		}
		
		if (solo.searchText(WeatherConstants.APPLICATION_POLICY_POPUP, true)) {
			solo.clickOnText(WeatherConstants.AGREE_TEXT);
			counter++;
		}
		
		if (solo.searchText(WeatherConstants.LOCATION_ENABLE_POPUP, true)) {
			solo.clickOnText(WeatherConstants.DISABLE_TEXT);
			counter++;
		}
		
		if (solo.searchText(WeatherConstants.LOCATION_MISPLACE_POPUP, true)) {
			solo.clickOnText(WeatherConstants.OK_TEXT);
			counter++;
		}
		return counter;
	}
	
	/**
	 * Enters city in the text box.
	 * @param city
	 * @throws InterruptedException
	 */
	public void enterCityInTextBox(final String city) throws InterruptedException{
		utilObj.enterCity(city,solo);
		solo.clickOnScreen(100, 100);
		solo.sleep(3000);
		solo.clickOnScreen(50, 50);
	}

	
	
	public void handleDismissPopup(final String dismissPopup){
		if (solo.searchText(WeatherConstants.UPDATE_POPUP, true)) {
			solo.waitForText(dismissPopup, 1, 1000);
			solo.clickOnText(dismissPopup);
		}	
	}
	
	public void checkForNoLocation() throws InterruptedException{
		if (solo.searchText(WeatherConstants.NO_LOCATION_TEXT, true) || solo.searchText(WeatherConstants.NOT_APPLICABLE, true)){
			solo.clickOnActionBarItem(R.id.search);
			solo.sleep(1000);
			utilObj.enterCity(WeatherConstants.CITY_ATLANTA,solo);
		}
	}
	
	/**
	 * Tear down method will close the solo object and finishes all the activities.
	 */
	protected void tearDown() throws Exception {
		try {
			solo.finalize();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		getActivity().finish();

		super.tearDown();

	}

}
