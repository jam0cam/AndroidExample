package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jitse.example.R;
import com.jitse.example.model.IntegerList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxActivity extends ActionBarActivity {

    private static final String TAG = RxActivity.class.getName();

    @InjectView(R.id.parent)
    LinearLayout mParentContainer;

    @InjectView(R.id.btn_red)
    Button mBtnRed;

    @InjectView(R.id.btn_blue)
    Button mBtnBlue;

    List<Integer> red;
    List<Integer> blue;
    List<Integer> colors;
    int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.inject(this);

        red = new ArrayList<>();
        red.add(getResources().getColor(R.color.red));
        red.add(getResources().getColor(R.color.red_light));
        red.add(getResources().getColor(R.color.orange));


        blue = new ArrayList<>();
        blue.add(getResources().getColor(R.color.blue_light));
        blue.add(getResources().getColor(R.color.blue_dark));
        blue.add(getResources().getColor(R.color.purple));

        mHeight = getResources().getDimensionPixelOffset(R.dimen.custom_view_height);

        automaticObservable();
    }

    private void manualObservable() {
        Observable<IntegerList> redObservable = Observable.create(new Observable.OnSubscribe<IntegerList>() {
            @Override
            public void call(final Subscriber<? super IntegerList> subscriber) {
                mBtnRed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (subscriber.isUnsubscribed()) return;
                        subscriber.onNext(getRedList());
                    }
                });
            }
        });

        Observable<IntegerList> blueObservable = Observable.create(new Observable.OnSubscribe<IntegerList>() {
            @Override
            public void call(final Subscriber<? super IntegerList> subscriber) {
                mBtnBlue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (subscriber.isUnsubscribed()) return;
                        subscriber.onNext(getBlueList());
                    }
                });
            }
        });

        blueObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        redObservable.concatWith(blueObservable)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<IntegerList>() {
                    @Override
                    public void call(IntegerList integerList) {
                        for (int i : integerList.integers) {
                            addView(i);
                        }
                    }
                });
    }

    /**
     * This method automatically creates the 2 observables, and merge them together, and
     * create the views.
     */
    private void automaticObservable() {
        Observable<IntegerList> redObservable = Observable.just(getRedList());
        Observable<IntegerList> blueObservable = Observable.timer(10000, TimeUnit.MILLISECONDS).just(getBlueList());

        redObservable.concatWith(blueObservable)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<IntegerList>() {
                    @Override
                    public void call(IntegerList integerList) {
                        for (int i : integerList.integers) {
                            addView(i);
                        }
                    }
                });
    }

    private IntegerList getRedList() {
        return new IntegerList(red);
    }

    private IntegerList getBlueList() {
        return new IntegerList(blue);
    }

    private void addView(int color) {
        View v = new View(this);
        v.setBackgroundColor(color);
        v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight));
        mParentContainer.addView(v);
    }

}
