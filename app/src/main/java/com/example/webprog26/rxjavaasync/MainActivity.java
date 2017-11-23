package com.example.webprog26.rxjavaasync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "debug_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSimpleObservableAndObserverFromStringsArray();
//        createObservaleWithCreateMethod();
    }

    private void createSimpleObservableAndObserverFromStringsArray(){
        Observable<String> myObservable = Observable.fromArray("Hello from RxJava",
                "Welcome...",
                "Goodbye");

        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(DEBUG_TAG, "onSubscribe" + d.toString());
            }

            @Override
            public void onNext(String s) {
                Log.i(DEBUG_TAG, "onNext() " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(DEBUG_TAG, "onError() " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(DEBUG_TAG, " onComplete() Rx Java events completed");
            }
        };

        myObservable.subscribe(myObserver);
    }

    private void createObservaleWithCreateMethod(){
        Observable<Integer> myObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(10);
                e.onNext(3);
                e.onNext(9);
                e.onComplete();
            }
        });

        myObservable.subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.i(DEBUG_TAG, "onSubscribe" + d.toString());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(DEBUG_TAG, "onNext() " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(DEBUG_TAG, "onError() " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(DEBUG_TAG, " onComplete() Rx Java events completed");
            }
        });
    }
}
