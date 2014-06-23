package com.example.diary;

import java.io.IOException;
import java.io.Serializable;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShowFragment extends Fragment {

	public static final String MODEL_CLASS = "model_class";
	public static final String Tag = "showfrag";

	public static final String IMAGE = "showfrag";

	private LinearLayout mLinearLayout;
	private String mdataNodes[][];
	
	private TextView mTextView,mNameTextView;

	private ModelClass modelClass;
	private ImageView mImageView;

	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (modelClass.getImgPath().equals("homer")){
				Toast.makeText(getActivity(), R.string.toast_some_error, Toast.LENGTH_SHORT);
				return;
			}
			
			android.support.v4.app.FragmentManager fm = getActivity()
					.getSupportFragmentManager();
			ImageDialog.newInstance(modelClass.getImgPath()).show(fm, IMAGE);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modelClass = (ModelClass) getArguments().getSerializable(MODEL_CLASS);
		
		mdataNodes = modelClass.mLinkedList2().getAllInfo();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.show_fragment, container, false);

		mLinearLayout = (LinearLayout) view.findViewById(R.id.show_holder);

		mNameTextView = (TextView)view.findViewById(R.id.name_textview);
		
		mNameTextView.setText("NAME: "+modelClass.getName());
		
		mImageView = (ImageView)view.findViewById(R.id.image_viewer);
		
		Drawable  bitmap_drawable =null;
		if (modelClass.getImgPath().equals("homer")){
			try {
				Log.i(Tag, "Homie");
				bitmap_drawable = Drawable.createFromStream(getActivity().getAssets().open("homer.png"), null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			bitmap_drawable=PictureHandler.getScaledDrawable(getActivity(), modelClass.getImgPath());
		}
		mImageView.setImageDrawable(bitmap_drawable);
		mImageView.setOnClickListener(mClickListener);
		
		//shows all other details added by the user
		for (int i = 0; i < mdataNodes.length; i++) {
			//to hide UUID which has data type of 1
			//mDataNode[i][1] denotes to Type
			//mDataNode[i][0] denotes is the detail
			if (mdataNodes[i][1].equals("1"))
				continue;

			if (mdataNodes[i][1].equals("0")) {
				Log.i(Tag, "Text");
				mTextView = new TextView(getActivity());
				mTextView.setTextSize(30.0f);
				mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
				mTextView.setText(mdataNodes[i][0]);
				mLinearLayout.addView(mTextView);
			}
		}

		return view;
	}

	public static ShowFragment newInstance(ModelClass modelClass) {
		Bundle argsBundle = new Bundle();
		argsBundle.putSerializable(MODEL_CLASS, (Serializable) modelClass);

		ShowFragment fragment = new ShowFragment();
		fragment.setArguments(argsBundle);

		return fragment;
	}

}
