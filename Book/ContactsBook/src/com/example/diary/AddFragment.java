package com.example.diary;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

@SuppressLint("NewApi")
public class AddFragment extends Fragment {

	private LinkedList mLinkedList;
	private ModelClass mClass;
	private LinearLayout mMain;
	private CharSequence mDetailSeq, mNameSeq;

	private Button mAddDetailsButton, mTakePictureButton, mSaveButton;

	private static final int PHOTO_ACT = 0;
	protected static final String Tag = "ADDFRAGMENT";

	private String ImgPath;
	private ImageView mImageView;
	private EditText mTitleEditText, mNameEditText;

	private static final String IMAGE = "image";

	private View.OnClickListener imageClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			android.support.v4.app.FragmentManager fm = getActivity()
					.getSupportFragmentManager();
			ImageDialog.newInstance(ImgPath).show(fm, IMAGE);
		}
	};

	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.add_detail_button:
				try {
					if(mDetailSeq.equals(null))
						return;
					//updates layout to show added details
					TextView nTextView = new TextView(getActivity());
					nTextView.setTextSize(30.0f);
					nTextView.setGravity(Gravity.CENTER_HORIZONTAL);
					nTextView.setText(mDetailSeq);
					nTextView.setTypeface(Typeface.DEFAULT_BOLD);
					nTextView.setBackgroundColor(getResources().getColor(R.color.BurlyWood));
					nTextView.setTextColor(Color.WHITE);
					mMain.addView(nTextView);
					mLinkedList.add(mDetailSeq.toString(), 0);
					mTitleEditText.setText("");	
				} catch (Exception e) {
					//To Avoid Exit when no Detail is typed
				}
				break;
			case R.id.take_picture_buton:
				Log.i(Tag, "dam");
				//Starts intent to choose image from gallery
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, PHOTO_ACT);
				break;
			case R.id.save_contact_entry:
				Log.i(Tag, "PATH IS " + ImgPath);

				try {
					//adds flag
					if (ImgPath==null)
						ImgPath= "homer";
					//adds default value for name
					if (mNameSeq==null)
						mNameSeq = "???";
					
					//passes linked list with detail values and UUI
					mClass = new ModelClass(mLinkedList);
					mClass.setImgPath(ImgPath);
					mClass.setName(mNameSeq.toString());
					ModelSingleton.get(getActivity()).addDetails(mClass);
					ModelSingleton.get(getActivity()).saveDetails();
					Toast.makeText(getActivity(), R.string.toast_success, Toast.LENGTH_SHORT).show();
					
					intent = new Intent(getActivity(), MainActivity.class);

					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intent);
					Log.i(Tag, "Done");
				} catch (Exception e) {
					//Used to not exit prematurely
				}

				
				break;
			}

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mLinkedList = new LinkedList();
		Log.i(Tag, Integer.toString(mLinkedList.size));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.addfragment, container, false);

		mAddDetailsButton = (Button) view.findViewById(R.id.add_detail_button);

		mTakePictureButton = (Button) view
				.findViewById(R.id.take_picture_buton);
		mTitleEditText = (EditText) view.findViewById(R.id.detail_edittext);
		mNameEditText = (EditText) view.findViewById(R.id.name_edittext);

		mImageView = (ImageView) view.findViewById(R.id.image_viewer);

		mSaveButton = (Button) view.findViewById(R.id.save_contact_entry);
		mSaveButton.setOnClickListener(mClickListener);

		//add default image to imageview
		try {
			Drawable  bitmap_drawable = Drawable.createFromStream(getActivity()
					.getAssets().open("homer.png"), null);
			mImageView.setImageDrawable(bitmap_drawable);

		} catch (IOException e) {
			e.printStackTrace();
		}

		
		mAddDetailsButton.setOnClickListener(mClickListener);
		mTakePictureButton.setOnClickListener(mClickListener);

		mTitleEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mDetailSeq = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				//not used
			}

			@Override
			public void afterTextChanged(Editable s) {
				//not used
			}
		});

		mNameEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mNameSeq = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				//not used
			}

			@Override
			public void afterTextChanged(Editable s) {
				//not used
			}
		});

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		mMain = (LinearLayout) getActivity().findViewById(R.id.details_here);
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == PHOTO_ACT && data != null) {
				Log.i(Tag, "wow");
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getActivity().getContentResolver().query(
						selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);

				Log.i(Tag, picturePath);

				ImgPath = picturePath;

				BitmapDrawable bitmap_drawable = PictureHandler
						.getScaledDrawable(getActivity(), picturePath);

				mImageView.setImageDrawable(bitmap_drawable);
				mImageView.setOnClickListener(imageClickListener);
				cursor.close();
			}

		}
		super.onActivityResult(requestCode, resultCode, data);

	}

}
