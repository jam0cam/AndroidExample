package com.jitse.example.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

import com.jitse.example.ExampleApplication;
import com.jitse.example.R;
import com.jitse.example.databinding.ActivityDataBindingBinding;
import com.jitse.example.retrofit.PatronProductResponse;
import com.jitse.example.retrofit.ProductService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataBindingActivity extends ActionBarActivity {

    @InjectView(R.id.brand)
    EditText mBrand;

    @InjectView(R.id.name)
    EditText mName;

    @InjectView(R.id.description)
    EditText mDescription;

    ProductService mProductService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        ButterKnife.inject(this);

        mProductService = ((ExampleApplication)getApplication()).getProductService();

        mProductService.getProduct(false, "108000")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PatronProductResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PatronProductResponse patronProductResponse) {
                        binding.setProduct(patronProductResponse.product.get(0));
                    }
                });

    }

}
