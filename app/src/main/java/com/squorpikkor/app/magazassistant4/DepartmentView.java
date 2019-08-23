package com.squorpikkor.app.magazassistant4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class DepartmentView extends View {

    //circle and text colors
    private int circleCol, labelCol;
    //label text
    private String circleText;
    //paint for drawing custom view
    private Paint circlePaint;

    public DepartmentView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //paint object for drawing in onDraw
        circlePaint = new Paint();

        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.DepartmentView, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            circleText = a.getString(R.styleable.DepartmentView_circleLabel);
            circleCol = a.getInteger(R.styleable.DepartmentView_circleColor, 0);//0 is default
            labelCol = a.getInteger(R.styleable.DepartmentView_labelColor, 0);
        } finally {
            a.recycle();
        }
    }






}
