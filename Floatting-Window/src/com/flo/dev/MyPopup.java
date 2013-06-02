package com.flo.dev;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class MyPopup 
{
	protected final View mAnchorView;
    private final PopupWindow mWindow;
    private View mRootView;
    private Drawable mBackground = null;
    private final WindowManager mWindowManager;
    

    public MyPopup(View anchor) 
    {
            mAnchorView = anchor;
            mWindow = new PopupWindow(anchor.getContext());

            // when a touch even happens outside of the window
            // make the window go away
            mWindow.setTouchInterceptor(new OnTouchListener() 
            {
                    public boolean onTouch(View v, MotionEvent event) 
                    {
                            if(event.getAction() == MotionEvent.ACTION_OUTSIDE) 
                            {
                                    mWindow.dismiss();
                                    return true;
                            }
                            return false;
                    }
            });

            mWindowManager = (WindowManager) mAnchorView.getContext().getSystemService(Context.WINDOW_SERVICE);
            onCreate();
    }

	private void onCreate() 
	{
		// TODO Auto-generated method stub
		
	}

	protected void onShow() 
	{
		
	}

    private void preShow() 
    {
            if(mRootView == null) 
            {
                    throw new IllegalStateException("setContentView was not called with a view to display.");
            }
            
            onShow();

            if(mBackground == null) 
            {	
                    mWindow.setBackgroundDrawable(new BitmapDrawable());
            } else 
            {
                   	mWindow.setBackgroundDrawable(mBackground);
            }

            // if using PopupWindow#setBackgroundDrawable this is the only values of the width and hight that make it work
            // otherwise you need to set the background of the root viewgroup
            // and set the popupwindow background to an empty BitmapDrawable
            mWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
            mWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            mWindow.setTouchable(true);
            mWindow.setFocusable(true);
            mWindow.setOutsideTouchable(true);

            mWindow.setContentView(mRootView);
    }

    public void setBackgroundDrawable(Drawable background) 
    {
            mBackground = background;
    }
    
    public void setContentView(View root) 
    {
        mRootView = root;
        mWindow.setContentView(root);
	}
	
	/**
	 * Will inflate and set the view from a resource id
	 * 
	 * @param layoutResID
	 */
	public void setContentView(int layoutResID) 
	{
	        LayoutInflater inflator =
	                        (LayoutInflater) mAnchorView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        this.setContentView(inflator.inflate(layoutResID, null));
	}
	
	/**
	 * If you want to do anything when {@link dismiss} is called
	 * 
	 * @param listener
	 */
	public void setOnDismissListener(PopupWindow.OnDismissListener listener) 
	{
	        mWindow.setOnDismissListener(listener);
	}
	
	/**
	 * Displays like a popdown menu from the anchor view
	 */
	public void showLikePopDownMenu() 
	{
	        showLikePopDownMenu(0, 0);
	}
	
	public void showLikePopDownMenu(int xOffset, int yOffset) 
	{
        this.preShow();

        //mWindow.setAnimationStyle(R.style.Animations_PopDownMenu);

        mWindow.showAsDropDown(mAnchorView, xOffset, yOffset);
	}
	
	/**
	 * Displays like a QuickAction from the anchor view.
	 */
	public void showLikeQuickAction() 
	{
	        showLikeQuickAction(0, 0);
	}

	public void showLikeQuickAction(int xOffset, int yOffset) 
	{
        this.preShow();

        //mWindow.setAnimationStyle(R.style.Animations_GrowFromBottom);

        int[] location = new int[2];
        mAnchorView.getLocationOnScreen(location);

        Rect anchorRect =
                        new Rect(location[0], location[1], location[0] + mAnchorView.getWidth(), location[1]
                                + mAnchorView.getHeight());

        mRootView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        int rootWidth = mRootView.getMeasuredWidth();
        int rootHeight = mRootView.getMeasuredHeight();

        int screenWidth = mWindowManager.getDefaultDisplay().getWidth();
        int screenHeight = mWindowManager.getDefaultDisplay().getHeight();

        int xPos = ((screenWidth - rootWidth) / 2) + xOffset;
        int yPos = anchorRect.top - rootHeight + yOffset;

        // display on bottom
        /*if(rootHeight > anchorRect.top) {
                yPos = anchorRect.bottom + yOffset;
                mWindow.setAnimationStyle(R.style.Animations_GrowFromTop);
        }*/

        mWindow.showAtLocation(mAnchorView, Gravity.NO_GRAVITY, xPos, -yPos); 
	}

	public void dismiss() 
	{
        mWindow.dismiss();
	}



}
