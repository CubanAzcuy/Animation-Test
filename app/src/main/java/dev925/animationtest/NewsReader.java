package dev925.animationtest;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsReader extends AppCompatActivity {

    @BindView(R.id.list_view)
    RecyclerView mListView;

    GestureListener mGestureListener;
    GestureDetector mGestureDetector;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_reader);
        ButterKnife.bind(this);

        mGestureListener = new GestureListener();
        mGestureDetector = new GestureDetector(this, mGestureListener, null, true);

        ArrayList<Object> list = new ArrayList<Object>() {{
            add("A");
            add("B");
            add("C");
        }};

        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        adapter.setCollection(list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mListView.addItemDecoration(new SimpleDividerItemDecoration(this));

        mListView.setLayoutManager(layoutManager);

        mListView.setHasFixedSize(true);
        mListView.setAdapter(adapter);
        mListView.setOnTouchListener(new View.OnTouchListener() {
            Float mHistoricX = null;
            Float mHistoricY = null;

            Float mHistoricX2 = null;
            Float mHistoricY2 = null;

            int mScrollDirection = 0;
            //1 = Left Right
            //2 = Up Down

            @Override
            public boolean onTouch(View v, MotionEvent e) {
                Log.d("TAG", "eX: " + e.getX() + " eY: " + e.getY());
                switch (e.getAction()) {

                        case MotionEvent.ACTION_UP:
                            Log.d("TAG", "ACTION_UP");

                            mHistoricX = null;
                            mHistoricY = null;
                            mScrollDirection = 0;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.d("TAG", "ACTION_MOVE");
                            if(mHistoricX == null || mHistoricY == null) {
                                mHistoricX = e.getX();
                                mHistoricY = e.getY();
                            } else {
                                if(mScrollDirection == 0) {
                                    float tempX = Math.abs(mHistoricX - e.getX());
                                    float tempy = Math.abs(mHistoricY - e.getY());

                                    if(tempX >= tempy) {
                                        mScrollDirection = 1;
                                    } else {
                                        mScrollDirection = 2;
                                    }

                                    mHistoricX2 =  mHistoricX - e.getX();
                                    mHistoricY2 = mHistoricY - e.getY();

                                } else {
                                    mHistoricX2 =  mHistoricX - e.getX();
                                    mHistoricY2 = mHistoricY - e.getY();
                                    Log.d("TAG", "X: " + mHistoricX2 + " Y: " + mHistoricY2);

                                    mHistoricX = e.getX();
                                    mHistoricY = e.getY();
                                }

                            }
                            break;
                        default:
                            break;

                    }
                if(mScrollDirection == 2){
                    mListView.animate().setDuration(0).xBy(-mHistoricX2).yBy(-mHistoricY2);
                    return true;
                }

                return false;
            }
        });



        mListView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d("TAG", "X: " + scrollX + " Y: " + scrollY);
            }
        });
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            mListView.setX(distanceX);
            mListView.setY(distanceY);
            return true;
        }
    }
}

