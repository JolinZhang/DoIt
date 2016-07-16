package com.example.jonelezhang.doit;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jonelezhang on 7/11/16.
 */
public class List_Recycler_View_Adapter extends RecyclerView.Adapter<List_View_Holder> {
    List<ListData> list = Collections.emptyList();
    Context context;

    public List_Recycler_View_Adapter(List<ListData> list, Context context){
        this.list = list;
        this.context = context;
    }


    @Override
    public List_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recyclerview_item,parent,false);
        final List_View_Holder holder = new List_View_Holder(v);
        //scroll view set position
        holder.swipe.post(new Runnable() {
            @Override
            public void run() {
                //let ic_clear_red show
                ViewGroup.MarginLayoutParams marginLayoutParams =
                        (ViewGroup.MarginLayoutParams) holder.swipe.getLayoutParams();
                marginLayoutParams.setMargins(0,
                        marginLayoutParams.topMargin,
                        marginLayoutParams.rightMargin,
                        marginLayoutParams.bottomMargin);
                        holder.swipe.requestLayout();
                //let stop at position at middle list item
                holder.swipe.scrollTo((int) holder.listItem.getX(), 0);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final List_View_Holder holder, int position) {
        //get device's width and height, set recyclerview list item's height
        holder.listItem.getLayoutParams().width = ((List_Menu) context).width;
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        holder.count.setText(list.get(position).count);
    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
