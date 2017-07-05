package com.example.dns.blackcub;

/**
 * Created by dns on 16.07.2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dns on 19.06.2016.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final Integer[] imgid;
    public ArrayList<String> titleList = new ArrayList<String>();


    public CustomListAdapter(Activity context, ArrayList<String> titleList, Integer[] imgid) {
        super(context, R.layout.mylist, titleList);

        this.context = context;
        this.titleList = titleList;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.Itemname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.Icon2);

        txtTitle.setText(titleList.get(position));
        imageView.setImageResource(imgid[position % imgid.length]);
        return rowView;
    };
}
