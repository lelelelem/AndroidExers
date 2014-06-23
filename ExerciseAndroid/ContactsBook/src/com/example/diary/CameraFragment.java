/*This Module was not used but was placed for future usage
 * 
 * 
 * */


package com.example.diary;

import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("NewApi")
public class CameraFragment extends Fragment {

	public static final String IMAGE_FILENAME = "Extra_filename";

	private Button mTakeButton;
	private SurfaceView mSurfaceView;
	private Camera mCamera;
	private final String TAG = "tag";

	

	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			// mCamera.takePicture(mShutterCallback, null, mPictureCallback);

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.camerafragment, container, false);

		mTakeButton = (Button) view.findViewById(R.id.capture_button);
		mTakeButton.setOnClickListener(mClickListener);

		mSurfaceView = (SurfaceView) view.findViewById(R.id.main_camera);
		SurfaceHolder holder = mSurfaceView.getHolder();
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		holder.addCallback(new SurfaceHolder.Callback() {
			public void surfaceCreated(SurfaceHolder holder) {
				// Tell the camera to use this surface as its preview area
				try {
					if (mCamera != null) {
						mCamera.setPreviewDisplay(holder);
					}
				} catch (IOException exception) {
					Log.e(TAG, "Error setting up preview display", exception);
				}
			}

			public void surfaceDestroyed(SurfaceHolder holder) {
				// We can no longer display on this surface, so stop the
				// preview.
				if (mCamera != null) {
					mCamera.stopPreview();
				}
			}

			public void surfaceChanged(SurfaceHolder holder, int format, int w,
					int h) {
				if (mCamera == null)
					return;

				// The surface has changed size; update the camera preview size
				Camera.Parameters parameters = mCamera.getParameters();
				Size s = getSupportedSized(parameters.getSupportedPreviewSizes(), w, h);
				parameters.setPreviewSize(s.width, s.height);
				mCamera.setParameters(parameters);
				try {
					mCamera.startPreview();
				} catch (Exception e) {
					Log.e(TAG, "Could not start preview", e);
					mCamera.release();
					mCamera = null;
				}
			}

		});

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			mCamera = Camera.open(0);
		} else {
			mCamera = Camera.open();
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
	}

	private Size getSupportedSized(List<Size> sizes, int width, int height) {
		Size best_size = sizes.get(0);
		int largest_possible_area = best_size.width * best_size.height;

		for (Size s : sizes) {
			int area = s.height * s.width;
			if (area > largest_possible_area) {
				best_size = s;
				largest_possible_area = area;
			}

		}

		return best_size;
	}

}
