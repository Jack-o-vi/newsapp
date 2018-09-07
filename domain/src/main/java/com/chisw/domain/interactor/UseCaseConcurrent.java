package com.chisw.domain.interactor;

import com.chisw.domain.executor.PostExecutionThread;
import com.chisw.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCaseConcurrent<T, P> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    public UseCaseConcurrent(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        disposables = new CompositeDisposable();
    }

    public abstract Observable<T> buildUseCaseObservable(P params);

    public void execute(DisposableObserver<T> observer, P params) {
        if (observer != null) {
            final Observable<T> observable = this.buildUseCaseObservable(params)
                    .subscribeOn(Schedulers.from(threadExecutor))
                    .observeOn(postExecutionThread.getScheduler());
            addDisposable(observable.subscribeWith(observer));
        }
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(DisposableObserver<T> disposableObserver) {
        if (disposableObserver != null && disposables != null) {
            disposables.add(disposableObserver);
        }
    }

}
