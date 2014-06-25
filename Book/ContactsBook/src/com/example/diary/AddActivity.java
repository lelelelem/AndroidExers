package com.example.diary;

import android.support.v4.app.Fragment;

public class AddActivity extends SingleFragmentActivity {

	@Override
	protected Fragment setupFragment() {
		return new AddFragment();
	}

}
