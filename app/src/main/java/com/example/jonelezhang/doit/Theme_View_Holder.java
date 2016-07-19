package com.example.jonelezhang.doit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jonelezhang on 7/18/16.
 */
public class Theme_View_Holder extends RecyclerView.ViewHolder {
    LinearLayout theme_color;
    TextView theme_color_name;
    public Theme_View_Holder(View itemView) {
        super(itemView);
        theme_color = (LinearLayout) itemView.findViewById(R.id.theme_color);
        theme_color_name = (TextView) itemView.findViewById(R.id.theme_color_name);
    }
}
