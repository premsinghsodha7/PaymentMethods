package com.android.paymentmethods.networking;


import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.android.paymentmethods.model.ListResultPayment;
import com.android.paymentmethods.model.Networks;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PaymentRepository {

    private static PaymentRepository paymentRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public static PaymentRepository getInstance(){
        if (paymentRepository == null){
            paymentRepository = new PaymentRepository();
        }
        return paymentRepository;
    }

    private final ApiEndpoints apiEndpoints;

    public PaymentRepository(){
        apiEndpoints = RetrofitService.createService(ApiEndpoints.class);
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Networks> getPaymentMethods(){
        loading.setValue(true);
        MutableLiveData<Networks> paymentData = new MutableLiveData<>();

        disposable.add(
                apiEndpoints.getPaymentList().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ListResultPayment>() {
                            @Override
                            public void onSuccess(@NotNull ListResultPayment listResultPayment) {
                                loading.setValue(false);
                                paymentData.setValue(listResultPayment.getNetworks());
                            }

                            @Override
                            public void onError(@NotNull Throwable e) {
                                // Network error
                                loading.setValue(false);
                                paymentData.setValue(null);
                            }
                        })
        );
        return paymentData;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }
}
