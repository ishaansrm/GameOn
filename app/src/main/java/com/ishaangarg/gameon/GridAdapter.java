package com.ishaangarg.gameon;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ishaan on 8/22/2015.
 */
public class GridAdapter extends ArrayAdapter<String> {
    private final Context mContext;
    ArrayList<String> mApps, mPackages;

    public GridAdapter(Context c, ArrayList<String> apps, ArrayList<String> packages) {
        super(c, R.layout.grid_item, apps);
        this.mContext = c;
        this.mApps = apps;
        this.mPackages = packages;
    }

    public int getCount() {
        return mApps.size();
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.grid_item, parent, false);
        Drawable icon = ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher);
        try {
            icon = getContext().getPackageManager().getApplicationIcon(mPackages.get(position));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        TextView textView = (TextView) rowView.findViewById(R.id.appName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.appIcon);

        textView.setText(mApps.get(position));
        imageView.setImageDrawable(icon);

        return rowView;
    }
}
