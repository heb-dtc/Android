package com.flo.simplelocalservice;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public class SimpleLocalServiceWrapper implements ServiceConnection{

	private SimpleLocalServiceBinder mBinder = null;
	private ISimpleLocalServiceCallbacks mListener = null;
	
	private boolean mIsSimpleLocalServiceBinded = false;
	
	public SimpleLocalServiceWrapper(ISimpleLocalServiceCallbacks listener) {
		mListener = listener;
	}

	@Override
	public void onServiceConnected(ComponentName arg0, IBinder binder) {
		mBinder = (SimpleLocalServiceBinder) binder;
		mIsSimpleLocalServiceBinded = true;
		
		mBinder.registerSimpleLocalServiceCallbacks(mListener);
	}

	@Override
	public void onServiceDisconnected(ComponentName arg0) {
		mIsSimpleLocalServiceBinded = false;
		mBinder.unregisterSimpleLocalServiceCallbacks(mListener);
	}
	
	public SimpleLocalServiceBinder getSimpleLocalService(){
		return mBinder;
	}
	
	public boolean isBinded(){
		return mIsSimpleLocalServiceBinded;
	}
}
