package com.yiyi.translater.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wq107 on 2018/1/19.
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * 关闭Activity的广播，放在自定义的基类中，让其他的Activity继承这个Activity就行
     */
    protected BroadcastReceiver finishAppReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("exitapp");
        this.registerReceiver(this.finishAppReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.finishAppReceiver);
    }
}
