package com.example.jonelezhang.doit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jonelezhang on 7/11/16.
 */
public class List_Recycler_View_Adapter extends RecyclerSwipeAdapter<List_View_Holder> {
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
        List_View_Holder holder = new List_View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(List_View_Holder holder, int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        holder.count.setText(list.get(position).count);

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        // Drag From Left
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.swipe_clear));
        // Drag From Right
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.swipe_done));

        // Handling different events when swiping
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
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

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, ListData listData){
        list.add(position, listData);
        notifyItemInserted(position);

    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(ListData listData){
        int position = list.indexOf(listData);
        list.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return 0;
    }

}
