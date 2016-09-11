package com.arover.rxdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxConcatActivity extends AppCompatActivity {

    private static final String TAG = "Concat";
    private List<Integer> mResult = new ArrayList<>(2);
    private TextView mResulsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_concat);
        mResulsText= (TextView) findViewById(R.id.results);

        if(getActionBar()!=null){
            getActionBar().setTitle("Concat");
        }
        Observable<Integer> getA = Observable.create(new Observable.OnSubscribe<Integer>(){

            @Override public void call(Subscriber<? super Integer> subscriber) {
                int a = new Random().nextInt(100);
                Log.d(TAG,"A return "+a);
                subscriber.onNext(a);
                subscriber.onCompleted();
            }
        });

        Observable<Integer> getB = Observable.create(new Observable.OnSubscribe<Integer>(){

            @Override public void call(Subscriber<? super Integer> subscriber) {
                int b = new Random().nextInt(100);
                Log.d(TAG,"B return "+b);
                subscriber.onNext(b);
                subscriber.onCompleted();
            }
        });
        
        List<Observable<Integer>>  observables = new ArrayList<>();
        observables.add(getA);
        observables.add(getB);
        mResult.clear();
        mResulsText.setText("");

        Observable.concat(observables)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override public void onCompleted() {
                        Log.d(TAG,"concat completed");
                        int total = 0;
                        for(Integer i : mResult){
                            total +=i;
                        }
                        mResulsText.append("completed total="+total);
                    }

                    @Override public void onError(Throwable e) {
                        Log.d(TAG,"concat onError",e);
                    }

                    @Override public void onNext(Integer integer) {
                        Log.d(TAG,"concat onNext var="+integer);
                        mResulsText.append("onNext var="+integer+"\n");
                        mResult.add(integer);
                    }
                });
    }
}
