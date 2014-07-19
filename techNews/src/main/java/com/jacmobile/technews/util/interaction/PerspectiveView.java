package com.jacmobile.technews.util.interaction;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class PerspectiveView extends LinearLayout
{
    private static final float FACTOR = 0.7f;
    private static final float X_ANCHOR = 0.5f;
    private static final float Y_ANCHOR = 1.0f;

    public PerspectiveView(Context context)
    {
        super(context);
    }

    public PerspectiveView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public PerspectiveView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    //    Constructors omitted for brevity. Include init() in each.
    private void init()
    {
        setStaticTransformationsEnabled(true);
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t)
    {
        HorizontalScrollView scrollView = null;
        if (getParent() instanceof HorizontalScrollView) {
            scrollView = (HorizontalScrollView) getParent();
        }
        if (scrollView == null) {
            return false;
        }
        int childCenter = getXCenter(child);
        int xCenter = getXCenter(scrollView);
        float delta = Math.min(1.0f, Math.abs(childCenter - xCenter) / (float) xCenter);
        float scale = FACTOR * delta;
        float xTrans = child.getWidth() * X_ANCHOR;
        float yTrans = child.getHeight() * Y_ANCHOR;
        t.clear();
        t.getMatrix().setScale(scale, scale, xTrans, yTrans);
        child.invalidate();
        return true;
    }

    private int getXCenter(View view)
    {
        int[] childCoords = new int[2];
        view.getLocationOnScreen(childCoords);
        int childCenter = childCoords[0] + (view.getWidth() / 2);
        return childCenter;
    }
}