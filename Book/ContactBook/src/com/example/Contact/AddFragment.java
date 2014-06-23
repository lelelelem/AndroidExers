package com.example.Contact;

import com.example.diary.R;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class AddFragment extends Fragment {

	private ModelClass mClass;
	
	private Intent intent;
	private int columnIndex;
	
	private String mPicturePath;
	private Button mAddDetailsButton;
	private Button mTakePictureButton;
	
	private ImageView mImageView;
	
	private static final int PHOTO_ACT = 0;
	protected static final String Tag = "ADDFRAGMENT";

	private EditText mTitleEditText;

	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.add_detail_button:
				mClass.setPicPath(mPicturePath);
				Log.i(Tag, "Title "+ mTitleEditText.getText().toString()+" path"+mPicturePath);
				try {
					ModelSingleton.get(getActivity()).addDetails(mClass);
					ModelSingleton.get(getActivity()).saveDetails();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				intent = new Intent(getActivity(), MainActivity.class);
				startActivity(intent);
				break;
			case R.id.take_picture_buton:
				
				intent= new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, PHOTO_ACT);
				break;
			}

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mClass=new ModelClass();
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(Tag, "SAW YOU");
		View view = inflater.inflate(R.layout.addfragment, container, false);

		mAddDetailsButton = (Button) view.findViewById(R.id.add_detail_button);
		

		mTakePictureButton = (Button) view
				.findViewById(R.id.take_picture_buton);
		mTitleEditText = (EditText) view.findViewById(R.id.detail_edittext);
		
		mTitleEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mClass.setName(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		mAddDetailsButton.setOnClickListener(mClickListener);
		mTakePictureButton.setOnClickListener(mClickListener);

		mImageView = (ImageView)view.findViewById(R.id.image_view);
		
		return view;
	}

	
	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		

		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == PHOTO_ACT && data != null) {
				Log.i(Tag, "GOT IT");
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getActivity().getContentResolver().query(
						selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();

				columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				mPicturePath = cursor.getString(columnIndex);
				
				

				BitmapDrawable bitmap_drawable = null;
				bitmap_drawable = PictureHandler.getScaledDrawable(
						getActivity(), mPicturePath);

				Log.i(Tag, mPicturePath);
				mImageView.setImageDrawable(bitmap_drawable);
				
				cursor.close();
			}

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
