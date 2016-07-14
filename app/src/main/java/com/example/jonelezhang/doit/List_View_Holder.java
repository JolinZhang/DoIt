package com.example.jonelezhang.doit;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jonelezhang on 7/11/16.
 */
public class List_View_Holder extends RecyclerView.ViewHolder {
//    TextView title;
//    TextView count;
    ViewPager viewPager;
    List_View_Holder(View itemView){
        super(itemView);
//        title = (TextView) itemView.findViewById(R.id.list_item_title);
//        count = (TextView) itemView.findViewById(R.id.list_item_count);
        viewPager = (ViewPager) itemView.findViewById(R.id.list_view_pager);
    }



}
