package com.example.ljh.liangminghui.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by ljh on 2017/12/6.
 */

public class DialogManager {
    static ProgressDialog progressDialog;
    static AlertDialog alertDialog;
    static AlertDialog.Builder builder;

    public static void showProgressDialog(Context context, String message){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dismissProgressDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
