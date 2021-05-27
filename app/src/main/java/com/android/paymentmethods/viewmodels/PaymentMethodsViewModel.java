package com.android.paymentmethods.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.paymentmethods.model.Networks;
import com.android.paymentmethods.networking.PaymentRepository;

public class PaymentMethodsViewModel extends ViewModel {

    private MutableLiveData<Networks> mutableLiveData;
    private MutableLiveData<Boolean> loading;
    private PaymentRepository paymentRepository;


    public void init(){
        if (mutableLiveData != null){
            return;
        }
        paymentRepository = PaymentRepository.getInstance();
        mutableLiveData = paymentRepository.getPaymentMethods();
        loading = paymentRepository.getLoading();

    }

    public LiveData<Networks> getPaymentRepository() {
        return mutableLiveData;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

}
