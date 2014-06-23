package com.example.diary;


import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

	
	@Override
	protected Fragment setupFragment() {
		return new MainFragment();
	}

	
}
