package com.example.diary;

import java.io.Serializable;

import android.R.integer;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowFragment extends Fragment {

	public static final String MODEL_CLASS = "model_class";
	public static final String Tag="showfrag";
	
	private LinearLayout mLinearLayout;
	private String mdataNodes[][];
	private ImageView mImageView;
	private TextView mTextView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ModelClass modelClass = (ModelClass)getArguments().getSerializable(MODEL_CLASS);
		mdataNodes= modelClass.mLinkedList2().getAllInfo();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.show_fragment, container,false);
		
		mLinearLayout = (LinearLayout)view.findViewById(R.id.show_holder);
		
		
		for(int i=0;i<mdataNodes.length;i++){
			if(mdataNodes[i][1].equals("0")){
				
				Log.i(Tag, "Text");
				mTextView = new TextView(getActivity());
				mTextView.setTextSize(30.0f);
				mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
				mTextView.setText(mdataNodes[i][0]);
				mTextView.setTypeface(Typeface.DEFAULT_BOLD);
				mTextView.setBackgroundColor(Color.BLACK);
				mTextView.setTextColor(Color.WHITE);
			mLinearLayout.addView(mTextView);
			}
			else {
				BitmapDrawable bitmap_drawable = null;
				bitmap_drawable = PictureHandler.getScaledDrawable(
						getActivity(), mdataNodes[i][0]);

				ImageView image_view = new ImageView(getActivity());
				mLinearLayout.addView(image_view);
				image_view.setBackgroundColor(Color.BLACK);
				image_view.setImageDrawable(bitmap_drawable);
			}			
		}
		
		return view;
	}
	
	public static ShowFragment newInstance(ModelClass modelClass){
		Bundle argsBundle = new Bundle();
		argsBundle.putSerializable(MODEL_CLASS, (Serializable) modelClass);
		
		ShowFragment fragment = new ShowFragment();
		fragment.setArguments(argsBundle);
		
		return fragment;
	}
	
	
}
