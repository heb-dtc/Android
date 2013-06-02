package com.flo.dev;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class VideoPopupActivty extends Activity
{
	private VideoView mVideoView = null;
	
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_popup);
        
        mVideoView = (VideoView)findViewById(R.id.video);
        
        mVideoView.setVideoURI(Uri.parse("http://daily3gp.com/vids/747.3gp"));
        //mVideoView.setMediaController(new MediaController());
        mVideoView.requestFocus();
        mVideoView.start();   
    }

}
