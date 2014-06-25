package com.example.diary;

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

	private LinkedList mLinkedList;
	private ModelClass mClass;
	private LinearLayout mMain;
	private CharSequence mCs;

	private Button mAddDetailsButton;
	private Button mTakePictureButton;
	private Button mSaveDetailButton;

	private static final int PHOTO_ACT = 0;
	protected static final String Tag = "asd";

	private EditText mTitleEditText;

	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.add_detail_button:
				TextView nTextView = new TextView(getActivity());
				nTextView.setTextSize(30.0f);
				nTextView.setGravity(Gravity.CENTER_HORIZONTAL);
				nTextView.setText(mCs);
				nTextView.setTypeface(Typeface.DEFAULT_BOLD);
				nTextView.setBackgroundColor(Color.BLACK);
				nTextView.setTextColor(Color.WHITE);
				mMain.addView(nTextView);
				mLinkedList.add(mCs.toString(), 0);
				mTitleEditText.setText("");
				break;
			case R.id.take_picture_buton:
				Log.i(Tag, "dam");
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, PHOTO_ACT);
				break;
			case R.id.save_details_button:
				try {
					mClass = new ModelClass(mLinkedList);
					ModelSingleton.get(getActivity()).addDetails(mClass);
					ModelSingleton.get(getActivity()).saveDetails();
					Log.i(Tag, "Done");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.i(Tag, "Error");
					e.printStackTrace();
				}
				break;
			}

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mLinkedList = new LinkedList();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.addfragment, container, false);

		mAddDetailsButton = (Button) view.findViewById(R.id.add_detail_button);
		mSaveDetailButton = (Button) view
				.findViewById(R.id.save_details_button);

		mTakePictureButton = (Button) view
				.findViewById(R.id.take_picture_buton);
		mTitleEditText = (EditText) view.findViewById(R.id.detail_edittext);

		mSaveDetailButton.setOnClickListener(mClickListener);
		mAddDetailsButton.setOnClickListener(mClickListener);
		mTakePictureButton.setOnClickListener(mClickListener);

		mTitleEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				mCs = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
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

		super.onActivityResult(requestCode, resultCode, data);

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
				mLinkedList.add(picturePath, 1);
				Log.i(Tag, picturePath);

				BitmapDrawable bitmap_drawable = null;
				bitmap_drawable = PictureHandler.getScaledDrawable(
						getActivity(), picturePath);

				ImageView image_view = new ImageView(getActivity());
				mMain.addView(image_view);
				image_view.setBackgroundColor(Color.BLACK);
				image_view.setImageDrawable(bitmap_drawable);

				cursor.close();
			}

		}
	}

}
