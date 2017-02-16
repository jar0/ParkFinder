package com.example.jarek.parkingfinder;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jarek on 27.10.2016.
 */
public class LogoView extends RelativeLayout {

    private LayoutInflater inflater;

    public LogoView(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
        init();
    }

    public LogoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        init();
        obtainAttrs(context,attrs);

    }


    public LogoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
        init();
        obtainAttrs(context,attrs);
    }

    private void obtainAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.LogoView,0,0);

        TextView linkView = (TextView) findViewById(R.id.link);
        String link = a.getString(R.styleable.LogoView_link);
        linkView.setText(link);
        a.recycle();
    }
    private void init(){
        inflater.inflate(R.layout.layout_logo_view,this,true);

    }
}
