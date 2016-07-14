package com.example.jonelezhang.doit;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jonelezhang on 7/14/16.
 */
public class List_Recycler_View_Pager_Adapter extends PagerAdapter {
    private Context context;
    private ListData list;
    private LayoutInflater inflater;

    public List_Recycler_View_Pager_Adapter(Context context, ListData listData){
        super();
        this.context = context;
        this.list = listData;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_recyclerview_viewpager, container, false);
        TextView title = (TextView) layout.findViewById(R.id.list_item_title);
        TextView count = (TextView) layout.findViewById(R.id.list_item_count);
        title.setText(list.title);
        count.setText(list.count);
        container.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
