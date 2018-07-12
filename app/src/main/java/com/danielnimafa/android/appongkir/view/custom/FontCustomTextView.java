package com.danielnimafa.android.appongkir.view.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class FontCustomTextView extends android.support.v7.widget.AppCompatTextView {

    public FontCustomTextView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init();
    }

    public FontCustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontCustomTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "trajanpro_bold.otf");
        setTypeface(tf);
    }

}
