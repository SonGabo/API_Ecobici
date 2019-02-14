package xyz.gabrielrohez.apiecobici.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;

import xyz.gabrielrohez.apiecobici.R;

public class Loader extends Dialog {

    private boolean cancelable = false;

    public Loader(@NonNull Context context){
        super(context);
    }

    public Loader(@NonNull Context context, boolean cancelable){
        super(context);
        this.cancelable = cancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        super.setCancelable(false);
        if (savedInstanceState == null){
            this.setContentView(R.layout.loader_layout);
        }
    }

    public void show() {
        if (!this.isShowing()) {
            super.show();
        }
    }

    public void dismiss() {
        if (this.isShowing()) {
            super.dismiss();
        }
    }
}
