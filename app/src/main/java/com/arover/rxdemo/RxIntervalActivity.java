package com.arover.rxdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.arover.rxdemo.model.MessageService;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxIntervalActivity extends AppCompatActivity {

    private static final String TAG = "Interval";
    private TextView mResulsText;
    private MessageService mMessageService;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_interval);
        mResulsText = (TextView) findViewById(R.id.results);

        if (getActionBar() != null) {
            getActionBar().setTitle("Interval");
        }
        mMessageService = new MessageService();
    }

    @Override protected void onStart() {
        super.onStart();
        subscription = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> mMessageService.getMessage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override public void onCompleted() {

                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "onError", e);
                    }

                    @Override public void onNext(String message) {
                        Log.d(TAG, "onNext " + message);
                        mResulsText.append(message + "\n");
                    }
                });
    }

    @Override protected void onStop() {
        super.onStop();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
