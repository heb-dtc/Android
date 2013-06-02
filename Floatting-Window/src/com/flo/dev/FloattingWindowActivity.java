package com.flo.dev;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class FloattingWindowActivity extends Activity 
{
	private Button likemenuButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        likemenuButton = (Button)(findViewById(R.id.likemenu));
        
        likemenuButton.setOnClickListener(new View.OnClickListener() 
        {
                public void onClick(View v) 
                {
                	DemoMyWindow popup = new DemoMyWindow(v);
                	popup.showLikeQuickAction();
                }
        });

        
        //DemoMyWindow popup = new DemoMyWindow(this.findViewById(android.R.id.content).getRootView());
        //popup.showLikePopDownMenu();
    }
    
    private static class DemoMyWindow extends MyPopup implements OnClickListener 
    {
    	private VideoView mVideoView = null;
    	
        public DemoMyWindow(View anchor) 
        {
                super(anchor);
                onCreate();
        }

        protected void onCreate() 
        {
                // inflate layout
                LayoutInflater inflater =
                                (LayoutInflater) this.mAnchorView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                ViewGroup root = (ViewGroup) inflater.inflate(R.layout.my_popup, null);

                mVideoView = (VideoView)root.getChildAt(0);
                
                mVideoView.setVideoURI(Uri.parse("http://daily3gp.com/vids/747.3gp"));
                //mVideoView.setMediaController(new MediaController());
                //mVideoView.requestFocus();
                mVideoView.start();
                
                // setup button events
                /*for(int i = 0, icount = root.getChildCount() ; i < icount ; i++) 
                {
                        View v = root.getChildAt(i);

                        if(v instanceof TableRow) {
                                TableRow row = (TableRow) v;

                                for(int j = 0, jcount = row.getChildCount() ; j < jcount ; j++) {
                                        View item = row.getChildAt(j);
                                        if(item instanceof Button) {
                                                Button b = (Button) item;
                                                b.setOnClickListener(this);
                                        }
                                }
                        }
                }*/

                // set the inflated view as what we want to display
                this.setContentView(root);
        }

        public void onClick(View v) 
        {
                // we'll just display a simple toast on a button click
                Button b = (Button) v;
                Toast.makeText(this.mAnchorView.getContext(), b.getText(), Toast.LENGTH_SHORT).show();
                this.dismiss();
        }
}

}