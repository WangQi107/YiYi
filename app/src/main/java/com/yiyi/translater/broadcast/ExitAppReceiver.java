package com.yiyi.translater.broadcast;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * Created by wq107 on 2018/1/19.
 */

public class ExitAppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (context != null) {

            if (context instanceof Activity) {

                ((Activity) context).finish();
            } else if (context instanceof FragmentActivity) {

                ((FragmentActivity) context).finish();
            } else if (context instanceof Service) {

                ((Service) context).stopSelf();
            }
        }
    }
}
