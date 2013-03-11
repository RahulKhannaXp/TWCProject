package com.weather.Weather.Objects;

import android.widget.LinearLayout;

import com.weather.Weather.R;
import com.weather.Weather.test.NowModule;

public class NowModuleObjects extends NowModule {
	
	public LinearLayout linearLayoutCircle() {
		LinearLayout circleRect = (LinearLayout)solo.getView(R.id.circle_rect);
		return circleRect;
	}
}
