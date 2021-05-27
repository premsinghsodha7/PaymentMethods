package com.android.paymentmethods.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.paymentmethods.R;
import com.android.paymentmethods.model.Applicable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.NewsViewHolder> {

    final Context context;
    final ArrayList<Applicable> applicables;

    public PaymentMethodsAdapter(Context context, ArrayList<Applicable> applicables) {
        this.context = context;
        this.applicables = applicables;
    }

    @NonNull
    @Override
    public PaymentMethodsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gateways_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodsAdapter.NewsViewHolder holder, int position) {
        holder.tvLabel.setText(applicables.get(position).getLabel());

        if(!applicables.get(position).getLinks().getLogo().isEmpty())
            Picasso.get()
                    .load(applicables.get(position).getLinks().getLogo())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.ic_picture)
                    .into(holder.ivLogo);
    }

    @Override
    public int getItemCount() {
        return applicables.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        final ImageView ivLogo;
        final TextView tvLabel;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvLabel = itemView.findViewById(R.id.tvLabel);

        }
    }
}
