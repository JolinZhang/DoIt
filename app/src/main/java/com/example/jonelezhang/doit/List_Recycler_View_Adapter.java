package com.example.jonelezhang.doit;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
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
    //tag for if allow to move scroll view
    public boolean MODE_ALLOWED = true;
    public int LAST_POSITION;

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
        // get touch event on scroll view
        holder.swipe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                final int action = event.getAction();
                //close the open item, when click on other item not itself
                if (MODE_ALLOWED == false && LAST_POSITION != holder.getAdapterPosition()) {
                    ((List_Menu) context).resetItem(LAST_POSITION, (int) holder.listItem.getX());
                    MODE_ALLOWED = true;
                }
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        // done
                        if(holder.swipe.getScrollX()< (holder.done.getX() -50)){
                            remove(holder.getAdapterPosition());
                            MODE_ALLOWED = true;
                        }
                        //show left done button
                        else if (holder.swipe.getScrollX() <= (holder.done.getX() +(holder.listItem.getX()- holder.done.getX()) /2) &&  holder.swipe.getScrollX() >= (holder.done.getX() -50)) {
                            holder.swipe.post(new Runnable() {
                                @Override
                                public void run() {
                                    holder.swipe.smoothScrollTo((int)holder.done.getX(), 0);
                                }
                            });
                            MODE_ALLOWED = false;
                            LAST_POSITION = holder.getLayoutPosition();
                        }
                        //call back into middle
                        else if (holder.swipe.getScrollX() > (holder.done.getX() + (holder.listItem.getX()- holder.done.getX())/ 2) && holder.swipe.getScrollX() < (holder.done.getX()+(holder.listItem.getX()- holder.done.getX()) * 3/2)) {
                            holder.swipe.post(new Runnable() {
                                @Override
                                public void run() {
                                    holder.swipe.smoothScrollTo((int) holder.listItem.getX(), 0);
                                }
                            });
                            MODE_ALLOWED = true;
                        }
                        //show right clear button
                        else if (holder.swipe.getScrollX() > (holder.done.getX() + (holder.listItem.getX()- holder.done.getX()) * 3/2) && holder.swipe.getScrollX() <= (holder.done.getX() +2* (holder.listItem.getX()- holder.done.getX())+50)){
                            holder.swipe.post(new Runnable() {
                                @Override
                                public void run() {
                                    holder.swipe.smoothScrollTo((int)(holder.listItem.getX()+(holder.listItem.getX()- holder.done.getX())), 0);
                                }
                            });
                            MODE_ALLOWED = false;
                            LAST_POSITION = holder.getAdapterPosition();
                        }
                        //delete
                        else if(holder.swipe.getScrollX() > (holder.done.getX() +2* (holder.listItem.getX()- holder.done.getX())+50)){
                            remove(holder.getAdapterPosition());
                            MODE_ALLOWED = true;
                        }
                        break;
                }
                return false;
            }
        });

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

    // insert item in recycler view
    public void insert(int position, ListData listData){
        list.add(position, listData);
        notifyItemInserted(position);
    }
    // remove item in recycler view
    public void remove(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }


}
