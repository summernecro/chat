package com.summer.chat.view;

//by summer on 2018-07-11.

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class FitSysRelativeLayout extends RelativeLayout {


    private int[] mInsets = new int[4];

    public FitSysRelativeLayout(Context context) {
        super(context);
    }

    public FitSysRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FitSysRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FitSysRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public final int[] getInsets() {
        return mInsets;
    }


    @Override
    protected final boolean fitSystemWindows(Rect insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Intentionally do not modify the bottom inset. For some reason,
            // if the bottom inset is modified, window resizing stops working.
            // TODO: Figure out why.

            mInsets[0] = insets.left;
            mInsets[1] = insets.top;
            mInsets[2] = insets.right;

            insets.left = 0;
            insets.top = 0;
            insets.right = 0;
        }

        return super.fitSystemWindows(insets);
    }

}
