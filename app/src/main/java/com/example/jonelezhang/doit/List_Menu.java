package com.example.jonelezhang.doit;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class List_Menu extends AppCompatActivity{
    List_Recycler_View_Adapter adapter;
    int height;
    int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //get device width and height
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        List<ListData> data = fill_with_data();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);
        //get instance of list adapter
        adapter = new List_Recycler_View_Adapter(data,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set recycler view touch listener swipe down to add item
        recyclerView.setOnTouchListener(new OnTouchListener() {
            float point_x1;
            float point_y1;
            float point_x2;
            float point_y2;
            float distancesX;
            float distancesY;
            final int SWIPE_THRESHOLD = 1;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int  action =  event.getAction();
                switch(action){
                    case MotionEvent.ACTION_DOWN:
                        point_x1 = event.getX();
                        point_y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        point_x2 = event.getX();
                        point_y2 = event.getY();
                        distancesX = point_x2 - point_x1;
                        distancesY = point_y2 - point_y1;
                        if(distancesY> SWIPE_THRESHOLD && distancesY >distancesX){
                             adapter.insert(0, new ListData("", ""));
                        }
                }
                return false;
            }
        });
    }

    //animation for reset recyclerView item
    public void resetItem(int position, int resetItemPosition) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);
        ((List_View_Holder) recyclerView.findViewHolderForAdapterPosition(position)).swipe.smoothScrollTo(resetItemPosition, 0);
    }

    //initial listData
    public List<ListData> fill_with_data() {

        List<ListData> data = new ArrayList<>();
        data.add(new ListData("food","0"));
        data.add(new ListData("fruit","1"));
        data.add(new ListData("homework","2"));
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
