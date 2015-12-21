package com.jitse.example.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jitse.example.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ListViewActivity extends AppCompatActivity {

    @InjectView(R.id.list_view)
    ListView mListView;

    ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ButterKnife.inject(this);

        List<Item> items = new ArrayList<>();

        items.add(new Item("http://a2.zassets.com/images/z/1/4/0/14078-p-MULTIVIEW.jpg", "One"));
        items.add(new Item("http://a1.zassets.com/images/z/3/4/1/2/9/6/3412963-p-MULTIVIEW.jpg", "Two"));
        items.add(new Item("http://a2.zassets.com/images/z/3/4/6/8/1/3/3468131-p-MULTIVIEW.jpg", "Three"));
        items.add(new Item("http://a2.zassets.com/images/z/1/4/0/14079-p-MULTIVIEW.jpg", "Four"));
        items.add(new Item("http://a1.zassets.com/images/z/2/8/7/3/3/6/287336-p-MULTIVIEW.jpg", "Five"));

        mAdapter = new ListAdapter(this, R.layout.list_item_2, items);
        mListView.setAdapter(mAdapter);
    }

    class Item  {
        String url;
        String name;

        Item(String s1, String s2) {
            url = s1;
            name = s2;
        }
    }

    static class ListAdapter extends ArrayAdapter<Item> {

        static class ViewHolder {
            TextView mTxtName;
            ImageView mImageView;
        }

        private int mResource;

        public ListAdapter(Context context, int resource, List<Item> objects) {
            super(context, resource, objects);
            mResource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi = convertView;             //trying to reuse a recycled view
            ViewHolder holder = null;
            if (vi == null) {
                //The view is not a recycled one: we have to inflate
                LayoutInflater inflater = LayoutInflater.from(getContext());
                vi = inflater.inflate(mResource, parent, false);
                holder = new ViewHolder();
                holder.mTxtName = (TextView) vi.findViewById(R.id.txt_name);
                holder.mImageView = (ImageView) vi.findViewById(R.id.image_view);
                vi.setTag(holder);
            } else {
                // View recycled !
                // no need to inflate
                // no need to findViews by id
                holder = (ViewHolder) vi.getTag();
            }

            Item item = getItem(position);
            holder.mTxtName.setText(item.name);

            Picasso.with(getContext())
                    .load(item.url)
                    .fit()
                    .centerInside()
                    .into(holder.mImageView);

            return vi;
        }
    }
}
