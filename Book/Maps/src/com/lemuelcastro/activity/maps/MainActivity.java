package com.lemuelcastro.activity.maps;

import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragment {


	@Override
	public Fragment SetUpFragment() {
		return new MapFragment();
	}
	

}
