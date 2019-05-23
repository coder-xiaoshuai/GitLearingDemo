package com.example.common;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtils {

    public static <T> void doInBackground(ObservableSource<T> observableSource) {
        Observable.unsafeCreate(observableSource)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<T>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(T t) {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
    }

    public static <T> ObservableTransformer<T, T> toMain() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> void asynDo(final Callable<T> callable, Consumer<T> successCall) {
        Observable.unsafeCreate(new ObservableSource<T>() {
            @Override
            public void subscribe(Observer<? super T> observer) {
                try {
                    observer.onNext(callable.call());
                    observer.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    observer.onError(e);
                }
            }
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(successCall, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    //抛出异常
                }
            });
    }

    public static void asynDo(final Runnable runnable) {
        Observable.unsafeCreate(new ObservableSource<Object>() {
            @Override
            public void subscribe(Observer<? super Object> observer) {

                try {
                    runnable.run();
                    observer.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }
}
