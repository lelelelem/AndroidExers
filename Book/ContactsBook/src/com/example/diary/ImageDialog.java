package com.example.diary;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageDialog extends DialogFragment{

	public static final String IMAGE_PATH="ImageDialog";
	private ImageView mImageView;
	
	
	public static ImageDialog newInstance(String path){
		ImageDialog imageDialog = new ImageDialog();
		Bundle bundle = new Bundle();
		
		bundle.putSerializable(IMAGE_PATH, path);
		
		imageDialog.setArguments(bundle);
		imageDialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		
		return imageDialog;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mImageView = new ImageView(getActivity());
		
		BitmapDrawable drawable = PictureHandler.getScaledDrawable(getActivity(), (String)getArguments().getSerializable(IMAGE_PATH));
		mImageView.setImageDrawable(drawable);
		
		return mImageView;
	}
	
	@Override
	public void onDestroyView() {
	
		super.onDestroyView();
		PictureHandler.cleanImageView(mImageView);
	}
	
	
}
