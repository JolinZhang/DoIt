package com.example.jonelezhang.doit;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class List_Menu extends AppCompatActivity implements OnTouchListener {
    List_Recycler_View_Adapter adapter;
    //variables in on touch event
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private float dy;
    private float dx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        List<ListData> data = fill_with_data();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);
        //get instance of list adapter
        adapter = new List_Recycler_View_Adapter(data,getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //add new item  touch listener
        recyclerView.setOnTouchListener(this);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        final int SWIPE_THRESHOLD = 2;

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                x1 = event.getX();
                y1 = event.getY();
                break;
            case (MotionEvent.ACTION_UP) :
                x2 = event.getX();
                y2 = event.getY();
                dy = y2-y1;
                dx = x2-x1;
                if (Math.abs(dy) > Math.abs(dx)
                        && Math.abs(dy) > SWIPE_THRESHOLD) {
                    if(dy>0) {adapter.insert(0, new ListData("", ""));}}
                break;
            default :
                return super.onTouchEvent(event);
        }
        return true;
    }
}
