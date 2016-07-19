package com.example.jonelezhang.doit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class Theme extends AppCompatActivity {
    private String[] colorName;
    private String[] colorValue;
    private RecyclerView  theme_recyclerView;
    private Theme_Recycler_View_Adapter theme_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        //get color name array and use adapter
        colorName = getResources().getStringArray(R.array.Theme_Color_Name);
        colorValue = getResources().getStringArray(R.array.Theme_Color_Value);
        theme_recyclerView = (RecyclerView) findViewById(R.id.theme_recyclerView);
        theme_adapter = new Theme_Recycler_View_Adapter(this,colorName,colorValue);
        theme_recyclerView.setAdapter(theme_adapter);
        theme_recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_theme, menu);
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
