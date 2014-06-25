package com.example.diary;

import android.support.v4.app.Fragment;

public class CameraActivity extends SingleFragmentActivity{

	@Override
	protected Fragment setupFragment() {
		return new CameraFragment();
	}

}
