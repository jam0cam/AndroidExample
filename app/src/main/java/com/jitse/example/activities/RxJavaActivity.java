package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jitse.example.ExampleApplication;
import com.jitse.example.R;
import com.jitse.example.retrofit.PatronProductResponse;
import com.jitse.example.retrofit.Product;
import com.jitse.example.retrofit.ProductService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends ActionBarActivity {
    private String TAG = RxJavaActivity.class.getName();

    @InjectView(R.id.parent_container)
    LinearLayout mParentContainer;

    @InjectView(R.id.button)
    Button mButton;

    ProductService mProductService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.inject(this);

        mProductService = ((ExampleApplication)getApplication()).getProductService();

    }

    @OnClick(R.id.button)
    public void clicked() {
        getDependentProduct();
    }

    /**
     * Make request A, A' and B. A' depends on A. Return when after all 3 calls have been made.
     *
     * It's a little complicated, but this is what the code does.
     *
     * It fetches product 108000. When the response is ready, it will get product 8265153. It will
     * return both the 8265153 response and the 108000 response. The brand name for those 2 products
     * are merged together. Then it maps that merged response back to an Observable.
     *
     * The (108000 + 8265153) observable is then "zipped" together with the "8463342" observable. When
     * both responses are ready, it displays it on the UI
     */
    private void getDependentProduct() {
        Observable.zip(
                mProductService.getProduct(false, "8463342"),
                mProductService.getProduct(false, "108000")
                        .flatMap(new Func1<PatronProductResponse, Observable<PatronProductResponse>>() {
                            @Override
                            public Observable<PatronProductResponse> call(PatronProductResponse o) {
                                return mProductService.getProduct(false, "8265153");
                            }
                        }, new Func2<PatronProductResponse, PatronProductResponse, PatronProductResponse>() {
                            @Override
                            public PatronProductResponse call(PatronProductResponse firstResponse, PatronProductResponse secondResponse) {
                                firstResponse.product.get(0).brandName += " " + secondResponse.product.get(0).brandName;
                                return firstResponse;
                            }
                        })
                        .flatMap(new Func1<PatronProductResponse, Observable<PatronProductResponse>>() {
                            @Override
                            public Observable<PatronProductResponse> call(PatronProductResponse patronProductResponse) {
                                return Observable.just(patronProductResponse);
                            }
                        }),
                new Func2<PatronProductResponse, PatronProductResponse, List<PatronProductResponse>>() {
                    @Override
                    public List<PatronProductResponse> call(PatronProductResponse patronProductResponse, PatronProductResponse patronProductResponse2) {
                        ArrayList<PatronProductResponse> list = new ArrayList<>();
                        list.add(patronProductResponse);
                        list.add(patronProductResponse2);

                        return  list;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<List<PatronProductResponse>>() {
                            @Override
                            public void call(List<PatronProductResponse> list) {
                                for (PatronProductResponse patronProductResponse : list) {
                                    Product p = patronProductResponse.product.get(0);

                                    TextView tv = new TextView(RxJavaActivity.this);
                                    tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                    tv.setText(p.getBrandName() + " " + p.getProductName());
                                    mParentContainer.addView(tv);
                                }
                            }
                        });
    }

    /**
     * Make request A and B. Wait for both to return and merge both requests.
     */
    private void get2SimultaneousProduct() {
        Observable.zip(
                mProductService.getProduct(false, "8265153"),
                mProductService.getProduct(false, "108000"),
                new Func2<PatronProductResponse, PatronProductResponse, List<PatronProductResponse>>() {
                    @Override
                    public List<PatronProductResponse> call(PatronProductResponse patronProductResponse, PatronProductResponse patronProductResponse2) {
                        ArrayList<PatronProductResponse> list = new ArrayList<>();
                        list.add(patronProductResponse);
                        list.add(patronProductResponse2);

                        return  list;
                    }
                }
        )
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<PatronProductResponse>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "ERROR: " + e.getMessage());
            }

            @Override
            public void onNext(List<PatronProductResponse> list) {
                for (PatronProductResponse patronProductResponse : list) {
                    Product p = patronProductResponse.product.get(0);

                    TextView tv = new TextView(RxJavaActivity.this);
                    tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    tv.setText(p.getBrandName() + " " + p.getProductName());
                    mParentContainer.addView(tv);
                }
            }
        });
    }

    private void getOneProduct() {
        mProductService.getProduct(false, "8265153")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PatronProductResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "ERROR: " + e.getMessage());
                    }

                    @Override
                    public void onNext(PatronProductResponse patronProductResponse) {
                        Product p = patronProductResponse.product.get(0);

                        TextView tv = new TextView(RxJavaActivity.this);
                        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        tv.setText(p.getBrandName() + " " + p.getProductName());
                        mParentContainer.addView(tv);
                    }
                });
    }
}
