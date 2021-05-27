package com.android.paymentmethods.customviews;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.android.paymentmethods.R;


public class CustomProgressBar {

    private Dialog mDialog = null;

    public CustomProgressBar(Context context) {
        if (context == null)
            return;

        mDialog = new Dialog(context, R.style.NewDialog);
        mDialog.setContentView(R.layout.loading_layout);
        ProgressBar mProgressBar = mDialog.findViewById(R.id.cp_pbar);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setBackgroundResource(R.drawable.circle_shape);

        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
    }

    public void loading(boolean isLoad) {
        if (mDialog != null){
            if (isLoad && !mDialog.isShowing()){
                mDialog.show();
            }else{
                mDialog.dismiss();
            }
        }
    }
}
