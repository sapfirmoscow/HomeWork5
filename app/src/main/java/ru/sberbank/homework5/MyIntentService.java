package ru.sberbank.homework5;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.security.SecureRandom;


public class MyIntentService extends IntentService {
    private static SecureRandom rnd = new SecureRandom();
    private final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public MyIntentService() {
        super("MyService");
    }

    public static final Intent newInteent(Context context) {
        return new Intent(context, MyIntentService.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Intent broadcastIntent = new Intent("ru.sberbank.SEND_MESSAGES_FILTER");
        broadcastIntent.putExtra("Data", randomString(10));
        sendBroadcast(broadcastIntent, "ru.sberbank.SEND_MESSAGES_PERMISSION");

    }

    private String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
