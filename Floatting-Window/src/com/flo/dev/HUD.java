package com.flo.dev;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class HUD extends Service implements OnTouchListener 
{
    Button mButton;
    Button mButtonBas;
    
    VideoView mVideoView;
    
    @Override
    public IBinder onBind(Intent intent) 
    {
        return null;
    }

    @Override
    public void onCreate() 
    {
        super.onCreate();
        
        //mView = new HUDView(this);
        
        mButton = new Button(this);
        mButton.setText("Overlay button");
        mButton.setOnTouchListener(this);
        
        //mButtonBas = new Button(this);
        //mButtonBas.setText("Overlay button bas");
        //mButtonBas.setOnTouchListener(this);
        /*
        mVideoView = new VideoView(this);
        mVideoView.setVideoURI(Uri.parse("http://daily3gp.com/vids/747.3gp"));
        mVideoView.setMediaController(new MediaController());
        mVideoView.requestFocus();  
        */
        
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        params.setTitle("Load Average");
        
        //View v = new View(this);
        
        
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        wm.addView(mButton, params);
        //wm.addView(mButtonBas, params);
        //wm.addView(mVideoView, params);
        
        //mVideoView.start(); 
    }

    @Override
    public void onDestroy() 
    {
        super.onDestroy();
       
        if(mButton != null)
        {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(mButton);
            mButton = null;
        }
        
        /*if(mButtonBas != null)
        {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(mButtonBas);
            mButtonBas = null;
        }*/
        
        /*if(mVideoView != null)
        {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(mVideoView);
            mVideoView = null;
        }*/
    }

	public boolean onTouch(View v, MotionEvent event) 
	{
        Toast.makeText(this,"Overlay button event", Toast.LENGTH_SHORT).show();
		return false;
	}
    
    
}