package com.android.paymentmethods.networking;

import com.android.paymentmethods.model.ListResultPayment;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiEndpoints {
    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Single<ListResultPayment> getPaymentList();
}
