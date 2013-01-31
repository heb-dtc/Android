package com.flo.simplelocalservice;

import java.util.ArrayList;
import android.os.Binder;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SimpleLocalServiceBinder extends Binder{
	private static String TAG = SimpleLocalServiceBinder.class.getSimpleName();
	
	private boolean mIsProcedureAvailable = false;
	private int mState = STATE_ERROR;
	
	private final static int STATE_READY = 100;
	private final static int STATE_STAND_BY = 101;
	private final static int STATE_ERROR = 101;
			
	private final static int NOTIFY_PROCEDURE_AVAILABLE = 0;
	private final static int NOTIFY_PROCEDURE_NO_MORE_AVAILABLE = 1;
	
	private ArrayList<ISimpleLocalServiceCallbacks> mCallbackList = null;
	
	private Handler mCallbackHandler = new Handler() {
		
		@Override
		public void handleMessage(Message msg) {
			final int N = mCallbackList.size();
			
			switch (msg.what) {
			case NOTIFY_PROCEDURE_AVAILABLE:
				for (int i = 0; i < N; i++) {
					mCallbackList.get(i).procedureAvailable();
				}
				break;
			case NOTIFY_PROCEDURE_NO_MORE_AVAILABLE:
				for (int i = 0; i < N; i++) {
					mCallbackList.get(i).procedureNoMoreAvailable();
				}
				break;
			}
		}
	};
	
	SimpleLocalServiceBinder(){
		mCallbackList = new ArrayList<ISimpleLocalServiceCallbacks>();
		mState = STATE_STAND_BY;
	}
	
	public void shutDown(){
		deinitProcedure();
	}
	
	public void registerSimpleLocalServiceCallbacks(ISimpleLocalServiceCallbacks callbacks){
		Log.i(TAG, "registerSimpleLocalServiceCallbacks");
		mCallbackList.add(callbacks);
	}
	
	public void unregisterSimpleLocalServiceCallbacks(ISimpleLocalServiceCallbacks callbacks){
		Log.i(TAG, "registerSimpleLocalServiceCallbacks");
		mCallbackList.remove(callbacks);
	}
	
	public void useProcedure(){
		if(mState == STATE_READY){
			Log.v(TAG, "use procedure");
		}
	}
	
	public void initProcedure(){
		mIsProcedureAvailable = true;
		mState = STATE_READY;
		mCallbackHandler.sendEmptyMessage(NOTIFY_PROCEDURE_AVAILABLE);
	}
	
	public void deinitProcedure(){
		mIsProcedureAvailable = true;
		mState = STATE_STAND_BY;
		mCallbackHandler.sendEmptyMessage(NOTIFY_PROCEDURE_NO_MORE_AVAILABLE);
	}
	
	public int getState(){
		return mState;
	}
}
