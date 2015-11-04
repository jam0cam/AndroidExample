package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.TextView;

import com.jitse.example.ExampleApplication;
import com.jitse.example.R;
import com.jitse.example.retrofit.PhetchService;

import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PhetchActivity extends ActionBarActivity {

    @InjectView(R.id.button)
    Button mButton;

    @InjectView(R.id.text_view)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phetch);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    public void phetch() {
        PhetchService service = ((ExampleApplication) getApplication()).getPhetchService();
        service.getAsinsByStockId(Collections.singletonList("32735136"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(asinStocks -> {
                    mTextView.setText(asinStocks.get(0).asin + "::" + asinStocks.get(0).stockId);
                });

    }

}
