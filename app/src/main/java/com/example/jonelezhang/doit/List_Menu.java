package com.example.jonelezhang.doit;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class List_Menu extends AppCompatActivity {
    List_Recycler_View_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //get instance of ListData ad RecyclerView
        List<ListData> data = fill_with_data();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);
        //get instance of list adapter
        adapter = new List_Recycler_View_Adapter(data,getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set animation for add recycler view item
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        //set recyclerView listener for recyclerView item
        recyclerView.setOnTouchListener(new OnSwipeTouchListener(this, recyclerView));
    }

    //implement  OnSwipeTouchListener
    public class OnSwipeTouchListener implements OnTouchListener {
        RecyclerView mrecyclerView;
        private GestureDetector mgestureDetector;
        private Context mcontext;

        public OnSwipeTouchListener(Context context, RecyclerView recyclerView) {
            mgestureDetector = new GestureDetector(context, new GestureListener());
            mcontext = context;
            this.mrecyclerView = recyclerView;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return mgestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
            private static final int SWIPE_THRESHOLD = 2;
            private static final int SWIPE_Vertical_THRESHOLD = 1;
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
            private int getPosition(MotionEvent e1) {
                return mrecyclerView.indexOfChild( mrecyclerView.findChildViewUnder( e1.getX(),  e1.getY()));
            }
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2,
                                   float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
//                if (Math.abs(distanceX) > Math.abs(distanceY)
//                        && Math.abs(distanceX) > SWIPE_THRESHOLD) {
//                    if (distanceX > 0)
//                        onSwipeRight(getPosition(e1));
//                    else
//                        onSwipeLeft(getPosition(e1));
//                    return true;
//                }
                if(Math.abs(distanceY) > Math.abs(distanceX)
                        && distanceY > SWIPE_Vertical_THRESHOLD){
                        onSwipeItemDown();
                }
                return false;
            }

            public void onSwipeRight(int pos) {
                //Do what you want after swiping left to right
                Toast.makeText(getApplicationContext(), "left to right ", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft(int pos) {
                //Do what you want after swiping right to left
                Toast.makeText(getApplicationContext(), "right to left ", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeItemDown(){
                adapter.insert(0, new ListData("", ""));
            }
        }

    }
    //set data for listData
    public List<ListData> fill_with_data() {
        List<ListData> data = new ArrayList<>();
        data.add(new ListData("food","0"));
        data.add(new ListData("fruit","1"));
        data.add(new ListData("homework", "2"));
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
