package com.lou.cordova.plugin;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;

public class AppNotificationService extends NotificationListenerService {
    public static AppNotificationService instance;

    private static final List<String> IGNORE_PKG = new ArrayList<String>() {{
         add("com.android.systemui");
         add("com.google.android.gms");
         add("com.android.vending");
         add("android");
    }};

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (IGNORE_PKG.contains(sbn.getPackageName()))
            return;
        Intent intent = new Intent(NotificationListener.NOTIFY_CHANNEL);
        intent.putExtra("sbn", sbn);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }

    @Override
    public void onListenerConnected ()
    {
        instance = AppNotificationService.this;
    }
}

