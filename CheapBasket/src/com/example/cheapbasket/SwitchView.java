package com.example.cheapbasket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class SwitchView extends Fragment {

	public int layout;
	View view;

	public SwitchView(int layout) {
		this.layout = layout;
	}

	public SwitchView() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(layout, container, false);
		switch (layout) {
		case R.layout.scan:
			cameraShoot();
			break;

		default:
			break;
		}
		return view;
	}
	
	Camera camera = null;
	CameraPreview cameraPreview = null;

	private void cameraShoot() {
		camera = Camera.open();
		cameraPreview = new CameraPreview(getActivity(), camera);
		FrameLayout shootPreview = (FrameLayout) view.findViewById(R.id.flPreview);
		shootPreview.addView(cameraPreview);
		Button takePicture = (Button) view.findViewById(R.id.bTakePicture);
		takePicture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PictureCallback pic = new PictureCallback() {
					
					@Override
					public void onPictureTaken(byte[] data, Camera arg1) {
						File pictureFile = getOutputMediaFile(1);
				        if (pictureFile == null){
				            return;
				        }

				        try {
				            FileOutputStream fos = new FileOutputStream(pictureFile);
				            fos.write(data);
				            fos.close();
				        } catch (FileNotFoundException e) {
				            Log.d("FileNotFound", "File not found: " + e.getMessage());
				        } catch (IOException e) {
				            Log.d("IONExcepiton", "Error accessing file: " + e.getMessage());
				        }
				    }
				};
				camera.takePicture(null, null, pic);
			}
		});
	}
	
	private static File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MyCameraApp");
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("MyCameraApp", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == 1){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == 2) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}

}
