package com.weather.Weather.Objects;


import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.R;
import com.weather.Weather.view.VideoViewWithMidpoint;
public class VideoModuleObjets {



	public Button getMustSeeButton(Solo solo){
		Button mustSeeBtn = (Button)solo.getView(R.id.btn_must_see);
		return mustSeeBtn;

	}

	public Button getLocalUsButton(Solo solo){
		Button localUsBtn = (Button)solo.getView(R.id.btn_local_us);
		return localUsBtn;
	}

	public Button getOnTvButton(Solo solo){
		Button onTvBtn = (Button)solo.getView(R.id.btn_on_tv);
		return onTvBtn;
	}

	public Button getWorldButton(Solo solo){
		Button worldBtn = (Button)solo.getView(R.id.btn_world);
		return worldBtn;
	}



	public ImageView getPlayButtonImage(Solo solo){
		ImageView playButton = (ImageView) solo.getView(R.id.play_button);
		return playButton;
	}

	
	
	public TextView getLoadingVideoText(Solo solo){
		TextView videoLoading = (TextView) solo.getView(R.id.video_loading);
		return videoLoading;
	}
	
	public VideoViewWithMidpoint getVideoView(Solo solo) {
		VideoViewWithMidpoint videoView = (VideoViewWithMidpoint) solo
				.getView(R.id.video_view);
		return videoView;
	}
	
	public ImageView getImageViewPlayButton(Solo solo) {
		ImageView playButton = (ImageView) solo.getView(R.id.play_button);
		return playButton;
	}
	
	public TextView getTextViewVideoLoading(Solo solo) {
		TextView videoLoading = (TextView) solo.getView(R.id.video_loading);
		return videoLoading;
	}

}
