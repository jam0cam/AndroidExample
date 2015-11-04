package com.jitse.example.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.jitse.example.R;

import java.util.List;

/**
 * Created by jitse on 12/25/14.
 */
public class MyListAdapter extends ArrayAdapter<String> {

    private static final String TAG = ListAdapter.class.getName();
    private List<String> mCoupons;
    private Context mContext;
    private View.OnClickListener mClickListener;
    private View.OnLongClickListener mLongClickListener;

    public MyListAdapter(Context context, List<String> objects) {
        super(context, R.layout.list_item, objects);

        mContext = context;
        mCoupons = objects;
    }

    //TODO: JIA: learn to use the view holder pattern
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView mTv = (TextView) rowView.findViewById(R.id.tv);
        mTv.setText(mCoupons.get(position));

        return rowView;
    }
}
