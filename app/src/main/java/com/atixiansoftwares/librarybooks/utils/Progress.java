package com.atixiansoftwares.librarybooks.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class Progress {
    static ProgressDialog progressDoalog;

   // public static void showProgress(Context context, String msg, String title) {
    public static void showProgress(Context context) {

        progressDoalog = new ProgressDialog(context);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setMessage("Please Wait");
        progressDoalog.setTitle("Loading");
        progressDoalog.setIndeterminate(true);
        progressDoalog.setCanceledOnTouchOutside(false);
        progressDoalog.show();
    }

    public static void dismissProgress() {

        if (progressDoalog != null) {
            progressDoalog.dismiss();
        }
    }
}
