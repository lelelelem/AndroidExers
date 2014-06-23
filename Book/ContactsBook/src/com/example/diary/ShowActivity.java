package com.example.diary;

import android.support.v4.app.Fragment;

public class ShowActivity extends SingleFragmentActivity {

	@Override
	protected Fragment setupFragment() {
		
		ModelClass modelClass=(ModelClass)getIntent().getSerializableExtra(ShowFragment.MODEL_CLASS);
		
		return ShowFragment.newInstance(modelClass);
	}

}
