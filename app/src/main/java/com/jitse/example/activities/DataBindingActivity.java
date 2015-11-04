package com.jitse.example.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jitse.example.ExampleApplication;
import com.jitse.example.R;
import com.jitse.example.databinding.ActivityDataBindingBinding;
import com.jitse.example.retrofit.PatronProductResponse;
import com.jitse.example.retrofit.Product;
import com.jitse.example.retrofit.ProductService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataBindingActivity extends ActionBarActivity {

    private static final String TAG = DataBindingActivity.class.getSimpleName();
    @InjectView(R.id.brand)
    EditText mBrand;

    @InjectView(R.id.name)
    EditText mName;

    @InjectView(R.id.imageUrl)
    EditText mDescription;

    @InjectView(R.id.button_save)
    Button mButtonSave;

    @InjectView(R.id.button_change_data)
    Button mButtonChangeData;

    ProductService mProductService;

    Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

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
                        mProduct = patronProductResponse.product.get(0);
                        binding.setProduct(mProduct);
                    }
                });

    }

    @OnClick(R.id.button_save)
    public void save() {
        Log.d(TAG, "productName:" + mProduct.getProductName());
        Log.d(TAG, "productName:" + mProduct.getBrandName());
    }

    /**
     * Changing the object should automatically change the text fields that are bounded to this object
     */
    @OnClick(R.id.button_change_data)
    public void changeData() {
        mProduct.setBrandName("Nike");
        mProduct.setProductName("Gel Nimbus");
    }
}
