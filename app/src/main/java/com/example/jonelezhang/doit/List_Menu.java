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

        switch(action) {
//            case (MotionEvent.ACTION_DOWN) :
//                Toast.makeText(getApplicationContext(), "Action was down ", Toast.LENGTH_SHORT).show();
//                return true;
//            case (MotionEvent.ACTION_MOVE) :
//                Toast.makeText(getApplicationContext(), "Action was MOVE ", Toast.LENGTH_SHORT).show();
//                return true;
            case (MotionEvent.ACTION_UP) :
                adapter.insert(0,new ListData("",""));
//                Toast.makeText(getApplicationContext(), "Action was UP ", Toast.LENGTH_SHORT).show();
                return true;
//            case (MotionEvent.ACTION_CANCEL) :
//                Toast.makeText(getApplicationContext(), "Action was CANCEL ", Toast.LENGTH_SHORT).show();
//                return true;
//            case (MotionEvent.ACTION_OUTSIDE) :
//                Log.d("DEBUG_TAG","Movement occurred outside bounds " +
//                        "of current screen element");
//                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}
