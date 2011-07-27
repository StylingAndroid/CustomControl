package com.stylingandroid.customcontrol;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.main );
		
//		HighlightedTextView htv = (HighlightedTextView)findViewById( R.id.HighlightedText );
//		htv.setPattern( "([Hh]ighlight)" );
	}
}