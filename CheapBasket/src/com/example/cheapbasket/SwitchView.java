package com.example.cheapbasket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class SwitchView extends Fragment{

	final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    
    Uri imageUri= null;
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
		//clickedbut=false;
		view = inflater.inflate(layout, container, false);
		if (layout==R.layout.scan) {

			cameraShoot();
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
				capture();
				Intent intent=new Intent(getActivity(),Product.class);
				startActivity(intent);
				getActivity().finish();
				layout=R.layout.navigation;
				
			
				/*PictureCallback pic = new PictureCallback() {
					@Override
					public void onPictureTaken(byte[] data, Camera arg1) {
						File pictureFile = null;
							//pictureFile = getOutputMediaFile(1);
					
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
			}*/
			}
			});
	}
		
	private void capture() {
	    camera.takePicture(null, null, null, new Camera.PictureCallback() {

	        @Override
	        public void onPictureTaken(byte[] data, Camera camera1) {
	           //Toast.makeText(MainActivity.class, "Picture Taken",Toast.LENGTH_SHORT).show();
	           System.out.print("picture");
	        	Intent intent = new Intent();
	            intent.putExtra("image_arr", data);
	            camera1.stopPreview();
	            if (camera1 != null) {
	                camera1.release();
	                camera = null;
	            }
	        }
	    });
	}

	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;

	public static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + "/map");
		boolean success = true;
		if (!mediaStorageDir.exists()) {
		    success = mediaStorageDir.mkdir();
		}
		if (!success || isExternalStorageWritable()) {
			 Log.d("MyCameraApp", "failed to create directory");
	            return null;
		}
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}

}
