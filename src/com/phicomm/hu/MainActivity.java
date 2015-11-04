package com.phicomm.hu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button start = (Button)findViewById(R.id.start_id);
        Button remove = (Button)findViewById(R.id.remove_id);
        
        start.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(MainActivity.this, FxService.class);
				startService(intent);
				finish();
			}
		});
        
        remove.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(MainActivity.this, FxService.class);
				stopService(intent);
			}
		});
    }
}
