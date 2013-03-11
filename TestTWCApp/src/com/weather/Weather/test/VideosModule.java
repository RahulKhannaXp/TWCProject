package com.weather.Weather.test;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.Surface;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.R;
import com.weather.Weather.Constants.WeatherConstants;
import com.weather.Weather.Objects.VideoModuleObjets;
import com.weather.Weather.Utility.UtilityClass;
import com.weather.Weather.activity.WeatherController;
import com.weather.Weather.video.VideoMessage;
import com.weather.Weather.view.VideoViewWithMidpoint;

public class VideosModule extends SetUpApplication {
	UtilityClass utilobj = new UtilityClass();
	private List<VideoMessage> emptyList = new ArrayList<VideoMessage>();
	private List<VideoMessage> playingVideos = emptyList;
	 

	VideoModuleObjets videosObj= new VideoModuleObjets();

	public void launchVideosTab(){
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
	}

	public void clickonMustSee(){
		solo.clickOnView(videosObj.getMustSeeButton(solo));
		solo.sleep(3000);
		solo.clickOnView(videosObj.getLocalUsButton(solo));
		solo.sleep(2000);

	}


	public void checkVideoIsPlaying() {
		int flagVideoLoaded = 0;

		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		VideoViewWithMidpoint videoView = videosObj.getVideoView(solo);
		solo.waitForView(videoView);

		ImageView playButton = videosObj.getPlayButtonImage(solo);
		assertTrue(playButton.isShown());
		solo.sleep(5000);
		solo.clickOnView(playButton);
		solo.sleep(1000);
		
		TextView videoLoading =videosObj.getLoadingVideoText(solo);
		
		for (int i = 0; i < 30; i++) {
			if (!(videoLoading.isShown())) {
				flagVideoLoaded = 1;
				break;
			} else {
				solo.sleep(2000);
			}
		}

		if (flagVideoLoaded == 1) {
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Flag is now 1");
			solo.sleep(2000);
			assertTrue(videoView.isPlaying());
			//videoView.pause();
			solo.sleep(2000);
		} else {
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Video is talking more than expected time to load.");
		}

	}
	
	public void playVideo() {
		int flagVideoLoaded = 0;
		
		solo.waitForView(videosObj.getVideoView(solo));
		
		if((videosObj.getImageViewPlayButton(solo)).isShown()){
			solo.clickOnView(videosObj.getImageViewPlayButton(solo));
			
			for (int i = 0; i < 30; i++) {
				if (!(videosObj.getTextViewVideoLoading(solo).isShown())) {
					flagVideoLoaded = 1;
					break;
				} else {
					solo.sleep(2000);
				}
			}
		} else {
			videosObj.getVideoView(solo).start();
			flagVideoLoaded = 1;
		}
		
		
		if (flagVideoLoaded == 1) {
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Flag is now 1");
			solo.sleep(2000);
			assertTrue((videosObj.getVideoView(solo)).isPlaying());
			//videoView.pause();
			solo.sleep(2000);
		} else {
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Video is talking more than expected time to load.");
		}
	}
	
	public void pauseVideo() {
		solo.waitForView(videosObj.getVideoView(solo));

		if ((videosObj.getVideoView(solo)).isPlaying()) {
			(videosObj.getVideoView(solo)).pause();
			assertFalse((videosObj.getVideoView(solo)).isPlaying());
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Video is paused sucessfully");
		} else {
			playVideo();
			(videosObj.getVideoView(solo)).pause();
			assertFalse((videosObj.getVideoView(solo)).isPlaying());
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Video is paused");
		}
	}

	
	
	public void checOrientationForVideoTab(){
		WeatherController wc = (WeatherController) solo.getCurrentActivity();
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		playVideo();
		assertTrue(wc.getWindow().getWindowManager().getDefaultDisplay()
				.getRotation() == Surface.ROTATION_0
				|| ((wc.getWindow().getWindowManager().getDefaultDisplay()
						.getRotation() == Surface.ROTATION_180)));
		solo.setActivityOrientation(Solo.LANDSCAPE);
		solo.sleep(2000);
		//playVideo();
		assertTrue(wc.getWindow().getWindowManager().getDefaultDisplay()
				.getRotation() == Surface.ROTATION_90
				|| ((wc.getWindow().getWindowManager().getDefaultDisplay()
						.getRotation() == Surface.ROTATION_270)));
		
	}
	
	public void searchAndAddLocation() throws InterruptedException{
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.clickOnActionBarItem(R.id.search);
		solo.sleep(5000);
		utilobj.enterCity(WeatherConstants.CITY_FOR_ADD_LOCATION_TEST,solo);
	}
	
	public void checkVideoTitle(){
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		playVideo();
		GridView videoGridView = (GridView) solo.getView(R.id.video_listView);
		System.out.println("Count: "+videoGridView.getCount());
		System.out.println("child Count: "+videoGridView.getChildCount());
		
		for(int i=0 ; i<videoGridView.getChildCount(); i++){
			ViewGroup gridChild = (ViewGroup) videoGridView.getChildAt(i);
			//gridChild.getContentDescription();
			//TextView videoTitle = (TextView) solo.getView(R.id.video_line_title);
			
			System.out.println("Video Title for " +i + "is " +gridChild.getContentDescription().toString());
			
		}
		
		//System.out.println(videosObj.getVideoView(solo).get);
		
		//assertEquals(videoMainTitle.getText(), videosObj.getVideoView(solo).getContentDescription());
		
	}


}
