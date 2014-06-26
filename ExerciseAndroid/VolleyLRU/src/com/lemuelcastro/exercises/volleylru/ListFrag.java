package com.lemuelcastro.exercises.volleylru;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class ListFrag extends android.support.v4.app.ListFragment {

	private ArrayList<ModelClass> mModelClasses;
	public static final String TAG = "ListFrag";
	private ForListAdapter mAdapter;

	private ImageLoader imageLoader;
	private RequestQueue mRequestQueue;

	public static final String BAND_NAME = "ListFrag";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		

		getActivity().setTitle(getActivity().getIntent().getStringExtra(BAND_NAME));

		mRequestQueue = Volley.newRequestQueue(getActivity());
		imageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());

		mModelClasses = ModelSingleton.returnAll();
		mAdapter = new ForListAdapter(mModelClasses);

		setListAdapter(mAdapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ModelClass modelClass = ((ForListAdapter) getListAdapter())
				.getItem(position);
		Toast.makeText(getActivity(), modelClass.getTitle(), Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);

		ListView listView = (ListView) view.findViewById(android.R.id.list);

		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// might be useful someday
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// might be useful someday
			}

		});

		registerForContextMenu(listView);

		return view;

	}

	public class ForListAdapter extends ArrayAdapter<ModelClass> {

		public ForListAdapter(ArrayList<ModelClass> modelClasses) {
			super(getActivity(), 0, modelClasses);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null)
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_fragment, null);

			ModelClass c = getItem(position);

			TextView textView = (TextView) convertView
					.findViewById(R.id.title_textview);

			textView.setText(c.getTitle());

			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.images);

			// Gets image from cache, uses volley cache defined in
			// BitmapLruCache class
			// no need to cache on to disk since image loaded are small
			imageLoader.get(c.getImgUrl(), ImageLoader.getImageListener(
					imageView, R.drawable.ic_launcher, R.drawable.ic_launcher));

			return convertView;
		}

	}

}
