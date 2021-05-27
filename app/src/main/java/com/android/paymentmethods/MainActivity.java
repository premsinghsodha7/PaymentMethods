package com.android.paymentmethods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.paymentmethods.adapters.PaymentMethodsAdapter;
import com.android.paymentmethods.customviews.CustomProgressBar;
import com.android.paymentmethods.model.Applicable;
import com.android.paymentmethods.viewmodels.PaymentMethodsViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final ArrayList<Applicable> applicableArrayList = new ArrayList<>();
    PaymentMethodsAdapter paymentMethodsAdapter;
    private RecyclerView rvPaymentMethods;
    PaymentMethodsViewModel paymentMethodsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomProgressBar customProgressBar = new CustomProgressBar(this);
        rvPaymentMethods = findViewById(R.id.rvPaymentMethods);

        paymentMethodsViewModel = ViewModelProviders.of(this).get(PaymentMethodsViewModel.class);
        paymentMethodsViewModel.init();

        paymentMethodsViewModel.getPaymentRepository().observe(this, newsResponse -> {
            applicableArrayList.addAll(newsResponse.getApplicable());
            paymentMethodsAdapter.notifyDataSetChanged();
        });

        paymentMethodsViewModel.getLoading().observe(this, customProgressBar::loading);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (paymentMethodsAdapter == null) {
            paymentMethodsAdapter = new PaymentMethodsAdapter(MainActivity.this, applicableArrayList);
            rvPaymentMethods.setAdapter(paymentMethodsAdapter);
            rvPaymentMethods.setItemAnimator(new DefaultItemAnimator());
            rvPaymentMethods.setNestedScrollingEnabled(true);
        } else {
            paymentMethodsAdapter.notifyDataSetChanged();
        }
    }
}
