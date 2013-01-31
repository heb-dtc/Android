package com.flo.simplelocalservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SimpleLocalService extends Service{
	
	private static String TAG = SimpleLocalService.class.getSimpleName();
	
	private SimpleLocalServiceBinder mBinder = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.e(TAG, "Starting Simple Local Service");
		mBinder = new SimpleLocalServiceBinder();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}
	
	@Override
	public void onDestroy() {
		if(mBinder != null){
			mBinder.shutDown();
			mBinder = null;
		}
		
		super.onDestroy();
	}
}
