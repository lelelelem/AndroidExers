package com.lemuel.exercise.simplemenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private CheckBox mShowMoreBox;
	//will be used to be able to keep track of menu in
	//onCreateOptionsMenu
	private Menu mMenu;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mShowMoreBox = (CheckBox)findViewById(R.id.show_checkbox);
		
		//cant seem to find a way to place this in a
		//private variable 
		mShowMoreBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				onCreateOptionsMenu(mMenu);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mMenu = menu;
		//used in order not to crash the first time
		if(menu!=null)
			menu.clear(); //clears the menu to avoid recreating menu
		
		getMenuInflater().inflate(R.menu.mymain, menu);
		menu.setGroupVisible(R.id.group1, mShowMoreBox.isChecked());
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(getApplication(), String.valueOf(item.getTitle())+" Was Clicked", Toast.LENGTH_SHORT).show();		
		return super.onOptionsItemSelected(item);
	}
}
