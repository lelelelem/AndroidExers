package com.example.Contact;


import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

	
	@Override
	protected Fragment setupFragment() {
		return new MainFragment();
	}

	
}
