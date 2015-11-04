package com.phicomm.hu;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FxService extends Service 
{
    LinearLayout mFloatLayout;
    WindowManager.LayoutParams wmParams;
	WindowManager mWindowManager;
	
	Button mFloatView;
	
	private static final String TAG = "FxService";
	
	@Override
	public void onCreate() 
	{
		super.onCreate();
		createFloatView();
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@SuppressLint({ "InflateParams", "ClickableViewAccessibility" })
	private void createFloatView()
	{
		wmParams = new WindowManager.LayoutParams();
		getApplication();
		mWindowManager = (WindowManager)getApplication().getSystemService(Context.WINDOW_SERVICE);
		wmParams.type = LayoutParams.TYPE_PHONE; 
        wmParams.format = PixelFormat.RGBA_8888; 
        wmParams.flags = 
//          LayoutParams.FLAG_NOT_TOUCH_MODAL |
          LayoutParams.FLAG_NOT_FOCUSABLE
//          LayoutParams.FLAG_NOT_TOUCHABLE
          ;

        //wmParams.gravity = Gravity.LEFT | Gravity.TOP; 
        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.gravity = Gravity.CENTER | Gravity.TOP;
        wmParams.x = 0;
        wmParams.y = 940;
        
        //wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        //wmParams.width = WindowManager.LayoutParams.FILL_PARENT;
        //wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //wmParams.width = 1080;
        //wmParams.height = 100;
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout, null);

        mWindowManager.addView(mFloatLayout, wmParams);

        mFloatView = (Button)mFloatLayout.findViewById(R.id.float_id);
        
        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
				.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        Log.i(TAG, "Width/2--->" + mFloatView.getMeasuredWidth()/2);
        Log.i(TAG, "Height/2--->" + mFloatView.getMeasuredHeight()/2);

        mFloatView.setOnTouchListener(new OnTouchListener() 
        {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) 
			{
				wmParams.x = (int) event.getRawX() - mFloatView.getMeasuredWidth()/2;
	            wmParams.y = (int) event.getRawY() - mFloatView.getMeasuredHeight()/2 - 25;
	            mWindowManager.updateViewLayout(mFloatLayout, wmParams);
				return false;
			}
		});	
        
        mFloatView.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				Toast.makeText(FxService.this, "onClick", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	public void onDestroy() 
	{
		super.onDestroy();
		if(mFloatLayout != null)
		{
			mWindowManager.removeView(mFloatLayout);
		}
	}
	
}
