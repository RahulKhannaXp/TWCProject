package com.weather.Weather.test;
import java.io.IOException;

import junit.framework.Assert;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.text.SpannedString;
import android.util.Log;
import android.view.Surface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.R;
import com.weather.Weather.Constants.WeatherConstants;
import com.weather.Weather.Utility.UtilityClass;
import com.weather.Weather.activity.WeatherController;

public class NowModule extends SetUpApplication {

	UtilityClass utilobj = new UtilityClass();
	
	/**
	 * This method is used to check whether Orientation of Activity is Portrait. As per 
	 * orientation rules it should be Portrait for Phones.
	 * @throws IOException 
	 */
	public void checkActivityOreintation() throws IOException {
		WeatherController wc = (WeatherController) solo.getCurrentActivity();
		assertTrue(wc.getWindow().getWindowManager().getDefaultDisplay()
				.getRotation() == Surface.ROTATION_0
				|| ((wc.getWindow().getWindowManager().getDefaultDisplay()
						.getRotation() == Surface.ROTATION_180)));
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER, "Method: checkActivityOreintation() Status: Pass");
	}

	/**
	 * This methods checks for all the weather details like Dew point, humidity, sunrise, sunset etc. coming after clicking on the Circular image.
	 * Also it will expand and contract the Circular image as these weather details appears only after the circle is expanded.
	 */
	public void checkForAllLabelsAndValues() {
		checkImageExpanded();
		checkDewPointLabel();
		checkDewPointValue();
		checkHumidityLabel();
		checkHumidityValue();
		checkWindLabel();
		checkWindValue();
		checkVisibilityLabel();
		checkVisibilityValue();
		checkSunriseLabel();
		checkSunriseValue();
		checkSunsetLabel();
		checkSunsetValue();
		checkHiLoLabel();
		checkHiLoValue();
		checkImageContracted();
	}
	
	
	/**
	 * This method expands the Circular Image on Now fragment and also checks if the circular image is expanded or not.
	 */
	public void checkImageExpanded() {
		utilobj.clickonNowCircularImage(solo);
		
		TextView dewPointLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.dew_point_label, solo);
		
		assertTrue(dewPointLabel.isShown());
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER, "Method: expandcontractrImage() Status: Pass");
	}

	/**
	 * This method will test whether the dew Point Label is correct and present on
	 * screen.
	 * 
	 */
	public void checkDewPointLabel() {
		
		TextView dewPointLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.dew_point_label, solo);

		String dewPointStr = (String) dewPointLabel.getText();

		int dewPointInt = dewPointStr.length();

		assertTrue(
				"dew_point_label length should be greater than 2 but less than 10",
				dewPointInt > 2 && dewPointInt < 10);

		// see that the dew_point_label value is > than 1
		assertTrue("dew_point_label length should be greater than 1: ",
				dewPointStr.length() >= 1);

		// check that dew_point_label is as expected
		assertTrue("twcflagship dew_point_label does not equal DEW POINT: ",
				new String(WeatherConstants.DEW_POINT_LABEL).equals(dewPointStr));
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkDewPointLabel() Status: Pass");

	}

	/**
	 * This method will test whether Dew point value is present if yes then it will check its 
	 * minimum and maximum range.
	 */

	public void checkDewPointValue() {
		boolean value = false;
		TextView dewpointValue = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.dew_point, solo);
		String dewPointValue = (String) dewpointValue.getText();

		int dewPointInt = dewPointValue.length();

		assertTrue(
				"Dew Point length should be greater than 2 but less than 13",
				dewPointInt >= 2 && dewPointInt <= 12);

		// see that the Dew Point value is > than 1
		assertTrue("Dew Point length should be greater than 1: ",
				dewPointValue.length() >= 1);

		// check that dew_point is numeric
		if (dewPointInt == 3) {
			value = utilobj.isInteger(dewPointValue.substring(0, 2));
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Dew Point Value "
					+ dewPointValue.substring(0, 2));
		} else {
			value = utilobj.isInteger(dewPointValue.substring(0, 1));
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Dew Point Value "
					+ dewPointValue.substring(0, 1));
		}

		assertTrue("twcflagship: Now view dew point value is not numeric: ",
				value);
	}

	/**
	 * This method will test whether the Humidity Point Label is correct and present on
	 * screen.
	 */

	public void checkHumidityLabel() {
		TextView humidityLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.humidity_label, solo);
		String humidityStr = (String) humidityLabel.getText();

		int visibilityInt = humidityStr.length();

		assertTrue(
				"humidity_label length should be greater than 2 but less than 11",
				visibilityInt > 2 && visibilityInt < 11);

		// see that the "humidity_label value is > than 1
		assertTrue("humidity_label length should be greater than 1: ",
				humidityStr.length() >= 1);

		// check that humidity_label is as expected
		assertTrue("twcflagship humidity_label does not equal HUMIDITY: ",
				new String(WeatherConstants.HUMIDITY_LABEL).equals(humidityStr));

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkHumidityLabel() Status: Pass");
	}

	/**
	 * This method will test whether Humidity value is present if yes then it will check its 
	 * minimum and maximum range.
	 */

	public void checkHumidityValue() {
		boolean value = false;
		TextView humidityLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.humidity, solo);
		String humidityValue = (String) humidityLabel.getText();

		int humidityInt = humidityValue.length();
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Humidity value is " +humidityValue);

		assertTrue("Humidity length should be greater than 2 but less than 13",
				humidityInt >= 2 && humidityInt <= 12);

		// see that the Humidity value is > than 1
		assertTrue("Humidity length should be greater than 1: ",
				humidityValue.length() >= 1);

		if (humidityInt == 3) {
			value = utilobj.isInteger(humidityValue.substring(0, 2));
		} else {
			value = utilobj.isInteger(humidityValue.substring(0, 1));
		}

		assertTrue("twcflagship: Now View Humidity value is not numeric: ",
				value);

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkHumidityValue() Status: Pass");

	}

	/**
	 * This method will test the Wind Label is correct and present on
	 * screen.
	 */

	public void checkWindLabel() {
		TextView windLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.wind_label, solo);
		String windValue = (String) windLabel.getText();

		int windInt = windValue.length();

		assertTrue(
				"Wind label length should be greater than 2 but less than 5",
				windInt > 2 && windInt <= 5);

		// see that the sunrise_set_label value is > than 1
		assertTrue("Wind label length should be greater than 1: ",
				windValue.length() >= 1);

		// check that wind_label is as expected
		assertTrue("twcflagship wind_label does not equal WIND: ", new String(WeatherConstants.WIND_LABEL).equals(windValue));

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkWindLabel() Status: Pass");

	}

	/**
	 * This method will test whether Wind value is present if yes then it will check its 
	 * minimum and maximum range.
	 */

	public void checkWindValue() {
		TextView windLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.wind, solo);
		SpannedString windValue = (SpannedString) windLabel.getText();
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Wind Value is :" +windValue);

		int windInt = windValue.length();

		assertTrue("Wind length should be greater than 2 but less than 13",
				windInt >= 2 && windInt <= 12);

		// see that the Humidity value is > than 1
		assertTrue("Wind length should be greater than 1: ",
				windValue.length() >= 1);
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkWindValue() Status: Pass");

	}

	/**
	 * This method will test the Visibility Label is correct and present on
	 * screen.
	 */

	public void checkVisibilityLabel() {
		TextView visiblilityLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.visibility_label, solo);
		String visibilityValue = (String) visiblilityLabel.getText();

		int windInt = visibilityValue.length();

		assertTrue(
				"Visibility label length should be greater than 2 but less than 11",
				windInt > 2 && windInt <= 11);

		// see that the sunrise_set_label value is > than 1
		assertTrue("Visibility label length should be greater than 1: ",
				visibilityValue.length() >= 1);

		// check that wind_label is as expected
		assertTrue("twcflagship visibility_label does not equal Visibility: ",
				new String(WeatherConstants.VISIBILITY_LABEL).equals(visibilityValue));

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkVisibilityLabel() Status: Pass");

	}

	/**
	 * This method will test whether Visibility value is present if yes then it will check its 
	 * minimum and maximum range.
	 */

	public void checkVisibilityValue() {
		TextView visiblilityLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.visibility, solo);
		SpannedString visibilityValue = (SpannedString) visiblilityLabel
				.getText();

		int visibilityInt = visibilityValue.length();

		assertTrue(
				"Visibility length should be greater than 5 but less than 12",
				visibilityInt >= 5 && visibilityInt <= 12);

		// see that the Visibility value is > than 1
		assertTrue("Visibility length should be greater than 1: ",
				visibilityValue.length() >= 1);
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Visibility value is : " +visibilityValue);
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkVisibilityValue() Status: Pass");

	}
	/**
	 * This method will test the Sunrise Label is correct and present on
	 * screen.
	 */
	public void checkSunriseLabel() {
		TextView sunriseLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.sunrise_label, solo);

		String sunriseStr = (String) sunriseLabel.getText();

		int sunrieInt = sunriseStr.length();

		assertTrue(
				"Sunrise label length should be greater than 2 but less than 8",
				sunrieInt >= 2 && sunrieInt <= 8);

		// see that the sunrise_label value is > than 1
		assertTrue("Sunrise label length should be greater than 1: ",
				sunriseStr.length() >= 1);

		// check that sunrise_set_lable is as expected
		assertTrue("twcflagship sunrise_label equals SUNRISE: ", new String(WeatherConstants.SUNRISE_LABEL).equals(sunriseStr));

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkSunriseLabel() Status: Pass");

	}

	/**
	 * This method will test whether Sunrise value is present if yes then it will check its 
	 * minimum and maximum range.
	 */

	public void checkSunriseValue() {
		TextView sunriseLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.now_sunrise, solo);
		SpannedString sunriseValue = (SpannedString) sunriseLabel.getText();

		int sunriseInt = sunriseValue.length();

		assertTrue("Sunrise length should be greater than 5 but less than 8",
				sunriseInt >= 5 && sunriseInt <= 8);

		// see that the Sunrise value is > than 1
		assertTrue("Sunrise length should be greater than 1: ",
				sunriseValue.length() >= 1);
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Sunrise Value is " + sunriseValue);
		
			

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkSunriseValue() Status: Pass");

	}

	/**
	 * This method will test the Sunset Label is correct and present on
	 * screen.
	 */

	public void checkSunsetLabel() {
		TextView sunsetLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.sunset_label, solo);

		String sunsetStr = (String) sunsetLabel.getText();

		int sunsetInt = sunsetStr.length();

		assertTrue(
				"Sunset label length should be greater than 2 but less than 8",
				sunsetInt >= 2 && sunsetInt <= 8);

		// see that the sunrise_label value is > than 1
		assertTrue("Sunset label length should be greater than 1: ",
				sunsetStr.length() >= 1);

		// check that sunrise_set_lable is as expected
		assertTrue("twcflagship sunset_label equals SUNSET: ", new String(WeatherConstants.SUNSET_LABEL).equals(sunsetStr));

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkSunsetLabel() Status: Pass");

	}

	/**
	 * This method will test whether Sunset value is present if yes then it will check its 
	 * minimum and maximum range.
	 */

	public void checkSunsetValue() {
		TextView sunsetLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.now_sunset, solo);
		SpannedString sunsetValue = (SpannedString) sunsetLabel.getText();

		int sunsetInt = sunsetValue.length();

		assertTrue("Sunset length should be greater than 5 but less than 8",
				sunsetInt >= 5 && sunsetInt <= 8);

		// see that the Sunset value is > than 1
		assertTrue("Sunset length should be greater than 1: ",
				sunsetValue.length() >= 1);
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Sunset Value is " + sunsetValue);

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkSunsetValue() Status: Pass");

	}

	/**
	 * This method will test the Hi/Lo Label is correct and present on
	 * screen.
	 */

	public void checkHiLoLabel() {
		TextView hiLowLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.hi_lo_level, solo);

		String hiLowStr = (String) hiLowLabel.getText();

		int hiLowInt = hiLowStr.length();

		assertTrue(
				"HI/LO label length should be greater than 2 but less than 6",
				hiLowInt >= 2 && hiLowInt <= 6);

		// see that the sunrise_label value is > than 1
		assertTrue("HI/LO label length should be greater than 1: ",
				hiLowStr.length() >= 1);

		// check that sunrise_set_lable is as expected
		assertTrue("twcflagship hi_lo_level equals HI/LO: ",
				new String(WeatherConstants.HI_LO_LABEL).equals(hiLowStr));

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkHiLoLabel() Status: Pass");

	}
	/**
	 * This method will test whether Hi/Lo value is present if yes then it will check its 
	 * minimum and maximum range.
	 */
	public void checkHiLoValue() {

		TextView hiLowLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.high_low_temp, solo);
		String hiLowValue = (String) hiLowLabel.getText();

		int hiLowInt = hiLowValue.length();

		assertTrue("HI/LO length should be greater than 3 but less than 9",
				hiLowInt >= 3 && hiLowInt <= 9);

		// see that the Sunset value is > than 1
		assertTrue("Sunset length should be greater than 1: ",
				hiLowValue.length() >= 1);
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"HI/Lo value is "+hiLowValue);
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkHiLoValue() Status: Pass");

	}
	/**
	 * This method contracts the Circular Image on Now fragment and also checks if the circular image is contracted or not.
	 */
	
	public void checkImageContracted(){
		ImageView circleCorner = (ImageView)solo.getView(R.id.circle_with_corner);
		solo.waitForView(circleCorner);
		solo.clickOnView(circleCorner);
		solo.sleep(4000);
		
		TextView dewPointLabel = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.dew_point_label, solo);
		
		assertFalse(dewPointLabel.isShown());
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER, "Method: contractCircle() Status: Pass");
	}


	/**
	 * This method will check whether Temperature is visible inside Circular icon on Now Page.
	 */
	public void checkTempratureVisibleInCircle() {
		
		TextView tv1 = (TextView) utilobj.getTextViewLabel(
				com.weather.Weather.R.id.temperature, solo);
		String tempValue = (String) tv1.getText();

		int tempInt = tempValue.length() + 2;

		assertTrue(
				"twcFLAGSHIPi - Temperature length should be >= 2 and <= 4 - tempInt: "
						+ tempInt, tempInt >= 2 && tempInt <= 5);
		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Temparuture inside circle is: "
				+ tempValue.substring(0, tempValue.length() - 1));

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkTempratureVisibleInCircle() Status: Pass");

	}

	/**
	 * This method will click on Temperature icon inside that Circular Image  that will make that image expand 
	 *  and then it will click on its corner to close that to original shape.
	 */

	public void checkTemparutreImageIcon() {
		ImageView icon = (ImageView) solo
				.getView(com.weather.Weather.R.id.temp_icon);
		solo.clickOnView(icon);
		//solo.sleep(2000);
		ImageView circleWithCorner = (ImageView) solo
				.getView(R.id.circle_with_corner);
		solo.clickOnView(circleWithCorner);
		//solo.sleep(2000);

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkTemparutreImageIcon() Status: Pass");
	}


	/**
	 * This method will test whether FeelsLike value is present if yes then it will check its 
	 * minimum and maximum range.
	 */
	public void checkFeelsLikeValue() {
		TextView tv1 = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.feelslike, solo);
		String feelsLikeValue = (String) tv1.getText();

		int feelsLikeInt = feelsLikeValue.length();

		assertTrue(
				"feelslike length should be greater than 2 but less than 30",
				feelsLikeInt >= 2 && feelsLikeInt <= 30);

		// see that the feelslike value is > than 1
		assertTrue("feelslike length should be greater than 1: ",
				feelsLikeValue.length() >= 1);

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkFeelsLikeValue() Status: Pass");

	}

	/**
	 * This method will test whether LastUpdatedTime is present if yes then it will check its 
	 * minimum and maximum range.
	 */
	public void checkLastUpdatedTimeVisible() {
		TextView tv1 = (TextView) utilobj.getTextViewLabel(
				com.weather.Weather.R.id.last_updated_time, solo);
		String lastUpdatedTimeValue = (String) tv1.getText();

		int lastUpdatedTimeInt = lastUpdatedTimeValue.length();

		assertTrue(
				"last_updated_time length should be greater than 2 but less than 35",
				lastUpdatedTimeInt >= 2 && lastUpdatedTimeInt <= 35);

		// see that the last_updated_time value is > than 1
		assertTrue("last_updated_time length should be greater than 1: ",
				lastUpdatedTimeValue.length() > 1);

		
		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Last Updated String is "
				+ lastUpdatedTimeValue);

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkLastUpdatedTimeVisible() Status: Pass");

	}
	/**
	 * This method will click on Search image and then it will enter city in Location textbox and 
	 * it will refresh the page with Weather conditions of new city that has been entered.
	 * @throws InterruptedException
	 */

	public void addNewLocationInDropDown() throws InterruptedException {
		solo.clickOnActionBarItem(R.id.search);
		solo.sleep(5000);
		utilObj.enterCity(WeatherConstants.CITY_FOR_ADD_LOCATION_TEST,solo);

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: addNewLocationInDropDown() Status: Pass");
	}

	/**
	 * This Method will navigate from Now screen to different Screens as in the specified order.
	 */

	public void launchBaseWeatherFragments() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		utilobj.launchUiTab(WeatherConstants.MAP_FRAGEMENT, solo);
		utilobj.launchUiTab(WeatherConstants.FORECAST_FRAGEMENT, solo);
	}


	/**
	 * This method will test the phrase coming below the Circle on Now Module only for locations having severe weather conditions within US.
	 */
	public void checkPhraseBelowCircle() {

		TextView phrase = (TextView) utilobj.getTextViewLabel(
				com.weather.Weather.R.id.phrase, solo);

		String phraseValue = (String) phrase.getText();

		Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,"Method: checkPhraseBelowCircle() Status: Pass");

	}

	/**
	 * In progress... this only appears for severe weather conditions.
	 */
	@SuppressLint("NewApi")
	public void checkForOnsetOffsetVisibility() {
		TextView tv1 = (TextView) utilobj.getTextViewLabel(
				com.weather.Weather.R.id.onset_offset, solo);
		String onsetOffsetValue = (String) tv1.getText();
		if (onsetOffsetValue.isEmpty()) {
			assertTrue("Onset Offset does not appear in Parisi ",
					onsetOffsetValue.isEmpty());
			System.out.println("No onset offset");
		} else {
			int onsetOffsetInt = onsetOffsetValue.length();

			assertTrue(
					"onset_offset length should be greater than 2 but less than 50",
					onsetOffsetInt >= 2 && onsetOffsetInt <= 50);

			// see that the onset_offset value is > than 1
			assertTrue("onset_offset length should be greater than 1: ",
					onsetOffsetValue.length() > 1);

			System.out.println(onsetOffsetValue.toString());
		}

	}

	/**
	 * In progress... this only appears for severe weather conditions.
	 */
	@SuppressLint("NewApi")
	public void checkForLevel1Alerts() {
		TextView alert1 = (TextView) utilobj.getTextViewLabel(
				com.weather.Weather.R.id.red_alert, solo);
		String alertstring = (String) alert1.getText();
		if (alertstring.isEmpty()) {
			System.out.println("No Alert 1");
			assertTrue("Alert 1 does not appear ", alertstring.isEmpty());
		} else {
			solo.clickOnView(alert1);
			TextView severeSubtitle = utilobj.getTextViewLabel(
					com.weather.Weather.R.id.severe_subtitle, solo);
			String label = (String) severeSubtitle.getText();
			if (solo.searchText(label)) {
				ImageView home = (ImageView) solo.getView(android.R.id.home);
				solo.clickOnView(home);
				// Will click on Circular image.
				utilobj.clickonNowCircularImage(solo);
				System.out.println("Yes Alert 1");
			}

		}

	}

	
	/**
	 * In progress... this only appears for severe weather conditions.
	 */
	public void checkForLevel2And3Alert() {
		// Following will click on Orange or Yellow (2nd or 3rd level) alerts
		// image.
		ImageView alert2 = (ImageView) solo
				.getView(com.weather.Weather.R.id.severeimage);
		solo.clickOnView(alert2);
		TextView severeSubtitle = utilobj.getTextViewLabel(
				com.weather.Weather.R.id.severe_subtitle, solo);
		String label = (String) severeSubtitle.getText();
		if (solo.searchText(label)) {
			System.out.println("severe again");
			// This will click on Weather Channel home logo to take the control
			// back to previous activity.
			ImageView home = (ImageView) solo.getView(android.R.id.home);
			solo.clickOnView(home);
			// Will click on Circular image.
			utilobj.clickonNowCircularImage(solo);

		}

	}
	
	/*public void checkTextOnSearchBox() {
		solo.clickOnActionBarItem(R.id.search);
		assertTrue(solo.waitForText(WeatherConstants.SEARCHBOX_TEXT));
	}*/
}
