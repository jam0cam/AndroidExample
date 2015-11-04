package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HtmlActivity extends ActionBarActivity {

    @InjectView(R.id.text_view)
    TextView mTextView;

    String html = "<span class=\"street-address\">3385 South Durango Drive</span>, <span class=\"locality\">Las Vegas</span>, <span class=\"region\">NV</span> <span class=\"postal-code\">89117</span>, <span class=\"country-name\">United States</span>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        ButterKnife.inject(this);


        mTextView.setText(Html.fromHtml(html));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_html, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
