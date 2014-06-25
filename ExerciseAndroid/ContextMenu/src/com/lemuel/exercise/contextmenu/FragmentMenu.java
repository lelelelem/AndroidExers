package com.lemuel.exercise.contextmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentMenu extends Fragment {

	private TextView mTextColor;
	private TextView mTextSize;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_main, container,false);
		
		mTextColor= (TextView)view.findViewById(R.id.text_color_text);
		mTextSize=	(TextView)view.findViewById(R.id.text_size_text);
		
		registerForContextMenu(mTextColor);
		registerForContextMenu(mTextSize);
		
		return view;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		switch(v.getId()){
			case R.id.text_color_text:
				getActivity().getMenuInflater().inflate(R.menu.color_menu, menu);
				break;
			case R.id.text_size_text:
				getActivity().getMenuInflater().inflate(R.menu.size_menu, menu);
				break;
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {		
			case R.id.red_context_menu:
				mTextColor.setTextColor(Color.RED);
				break;
			case R.id.blue_context_menu:
				mTextColor.setTextColor(Color.BLUE);
				break;
			case R.id.green_context_menu:
				mTextColor.setTextColor(Color.GREEN);
				break;
			case R.id.ttwo_context_menu:
				mTextSize.setTextSize(22.0f);
				break;
			case R.id.tsix_context_menu:
				mTextSize.setTextSize(26.0f);
				break;
			case R.id.thirty_context_menu:
				mTextSize	.setTextSize(30.0f);
				break;
		}
		
		return super.onContextItemSelected(item);
	}
}
