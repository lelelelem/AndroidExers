package com.example.Contact;

import java.util.UUID;

import com.example.diary.R;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowFragment extends Fragment {

	public static final String MODEL_CLASS = "model_class";
	public static final String Tag="showfrag";
	
	private ModelClass mClass;
	
	private TextView mDetailView;
	
	private ImageView mImageView;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		UUID extra_Uuid=(UUID)getActivity().getIntent().getSerializableExtra(MODEL_CLASS);
			Log.i(Tag, "Extra UUID is "+extra_Uuid);
		
		try {
			mClass = ModelSingleton.get(getActivity()).getDetail(extra_Uuid);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.show_fragment, container, false);

		mDetailView = (TextView)view.findViewById(R.id.detail_textview);
		
		mDetailView.setText(mClass.getName());
		mImageView = (ImageView)view.findViewById(R.id.image_view_show);
		mImageView.setImageDrawable(img(mClass.getPicPath()));
		
		return view;
	}

	
	

	
	public  BitmapDrawable img(String imagePath) {
		
				BitmapDrawable bitmap_drawable = PictureHandler.getScaledDrawable(
							getActivity(), imagePath);
				
				mImageView.setImageDrawable(bitmap_drawable);
				
				return bitmap_drawable;
	}

	
	
}
