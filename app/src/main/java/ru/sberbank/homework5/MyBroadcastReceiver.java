package ru.sberbank.homework5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {

    Callback callback;

    public MyBroadcastReceiver(Callback callback) {
        this.callback = callback;
    }

    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String getData = intent.getStringExtra("Data");
        callback.setData(getData);


    }
}
