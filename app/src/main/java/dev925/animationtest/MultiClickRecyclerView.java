package dev925.animationtest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * http://stackoverflow.com/questions/36290055/preventing-recyclerview-from-consuming-touch-events
 * Created by vguzzi on Mar 29, 2016.
 */

public class MultiClickRecyclerView extends RecyclerView {


    public MultiClickRecyclerView(Context context) {
        super(context);
    }

    public MultiClickRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiClickRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && this.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) {
            this.stopScroll();
        }
        return super.onInterceptTouchEvent(event);
    }
}
