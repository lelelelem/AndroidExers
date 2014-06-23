package com.example.diary;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public abstract class SingleFragmentActivity extends FragmentActivity {

	protected abstract Fragment setupFragment();
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle arg0) {
	
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
			View decorView = getWindow().getDecorView();
			
			decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
			
		else {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		FragmentManager fm=getSupportFragmentManager();
		Fragment fragment=fm.findFragmentById(R.id.container);
		
		fragment = setupFragment();
		
		fm.beginTransaction().add(R.id.container,fragment).commit();
		
	}
	
	
}
