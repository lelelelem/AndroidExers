package com.example.dialog;

import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

	

	@Override
	public Fragment getFragment() {
		
		return new MainFragment();
	}

}
