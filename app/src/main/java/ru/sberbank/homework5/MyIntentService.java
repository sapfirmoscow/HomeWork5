package ru.sberbank.homework5;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
