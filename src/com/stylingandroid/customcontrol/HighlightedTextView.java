package com.stylingandroid.customcontrol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.widget.TextView;

public class HighlightedTextView extends TextView
{
	private Pattern pattern;
	private int highlightStyle = Typeface.BOLD;
	
	public HighlightedTextView( Context context )
	{
		this( context, null );
	}

	public HighlightedTextView( Context context, AttributeSet attrs )
	{
		this( context, attrs, R.attr.highlightedTextViewStyle );
	}

	public HighlightedTextView( Context context, AttributeSet attrs, int defStyle )
	{
		super( context, attrs, defStyle );
		TypedArray ta = context.obtainStyledAttributes( attrs, R.styleable.HighlightedTextView, defStyle, 0 );
		CharSequence text = ta.getString( R.styleable.HighlightedTextView_android_text );
		if( text != null )
		{
			updateText( text );
		}
		String pattern = ta.getString( R.styleable.HighlightedTextView_pattern );
		if( pattern != null )
		{
			setPattern( pattern );
		}
		int style = ta.getInteger( R.styleable.HighlightedTextView_highlightStyle, -1 );
		if( style >= 0 )
		{
			setHighlightStyle( style );
		}
		ta.recycle();
	}
	
	@Override
	public void setText( CharSequence text, BufferType type )
	{
		updateText( text );
	}
	
	public String getPattern()
	{
		return pattern.pattern();
	}

	public void setPattern( String pattern )
	{
		this.pattern = Pattern.compile( pattern );
		updateText( getText() );
	}

	public int getHighlightStyle()
	{
		return highlightStyle;
	}

	public void setHighlightStyle( int highlightStyle )
	{
		this.highlightStyle = highlightStyle;
		updateText( getText() );
	}
	
	public void updateText( CharSequence text )
	{
		Spannable spannable = new SpannableString( text );
		if( pattern != null )
		{
			Matcher matcher = pattern.matcher( text );
			while( matcher.find() )
			{
				int start = matcher.start();
				int end = matcher.end();
				StyleSpan span = new StyleSpan( highlightStyle );
				spannable.setSpan( span, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE );
			}
		}
		super.setText( spannable, BufferType.SPANNABLE );
	}
}
