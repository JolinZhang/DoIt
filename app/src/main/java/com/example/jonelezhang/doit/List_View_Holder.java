package com.example.jonelezhang.doit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jonelezhang on 7/11/16.
 */
public class List_View_Holder extends RecyclerView.ViewHolder {
    EditText title;
    TextView count;
    Button done;
    Button clear;
    LinearLayout listItem;
    RelativeLayout list;
    HorizontalScrollView swipe;
    List_View_Holder(View itemView){
        super(itemView);
        swipe = (HorizontalScrollView) itemView.findViewById(R.id.list_swipe);
        list = (RelativeLayout) itemView.findViewById(R.id.list_relative);
        listItem = (LinearLayout) itemView.findViewById(R.id.list_item_list);
        title = (EditText) itemView.findViewById(R.id.list_item_title);
        count = (TextView) itemView.findViewById(R.id.list_item_count);
        done = (Button) itemView.findViewById(R.id.swipe_button_done);
        clear = (Button) itemView.findViewById(R.id.swipe_button_clear);
    }
}
