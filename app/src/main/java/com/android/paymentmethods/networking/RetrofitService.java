package com.android.paymentmethods.networking;

import android.util.Log;

import com.android.paymentmethods.BuildConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                try {
                    Response response = chain.proceed(chain.request());
                    if (!response.isSuccessful()) {
                        Log.e("tag", "Failure central - response code: " + response.code());
                        Log.e("tag", "central server error handling");

                        // Central error handling for error responses here:
                        // e.g. 4XX and 5XX errors
                        switch (response.code()) {
                            case 401:
                                Log.e("tag", "Unauthorized happened:" + response.code());
                                break;
                            case 403:
                                Log.e("tag", "Forbidden happened:" + response.code());
                                break;
                            case 404:
                                Log.e("tag", "Not Found happened:" + response.code());
                                break;
                            case 500:
                                Log.e("tag", "Service Unavailable happened:" + response.code());
                                break;
                            default:
                                Log.e("tag", "Log error or do something else with error code:" + response.code());
                                break;
                        }
                    }

                    return response;
                } catch (IOException e) {
                    // Central error handling for network errors here:
                    // e.g. no connection to internet / to server
                    Log.e("tag", e.getMessage(), e);
                    Log.e("tag", "central network error handling");

                    throw e;
                }
            })
            .build();

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
