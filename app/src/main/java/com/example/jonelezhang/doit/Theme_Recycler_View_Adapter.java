package com.example.jonelezhang.doit;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jonelezhang on 7/18/16.
 */
public class Theme_Recycler_View_Adapter extends RecyclerView.Adapter<Theme_View_Holder> {
    private Context context;
    private String[] color_name;
    private String[] color_value;

    public Theme_Recycler_View_Adapter(Context Context, String[] Color_Name, String[] Color_Value){
        this.context = Context;
        this.color_name = Color_Name;
        this.color_value = Color_Value;
    }


    @Override
    public Theme_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_recyclerview_item,parent,false);
        Theme_View_Holder holder = new Theme_View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Theme_View_Holder holder, int position) {
        holder.theme_color_name.setText(color_name[position]);
        int color = Color.parseColor(color_value[position]);
        holder.theme_color.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return color_name.length;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
