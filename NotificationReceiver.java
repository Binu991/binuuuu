package com.example.meal.helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {

    // Define your notification channel ID (must match channel ID used when scheduling notifications)
    private static final String CHANNEL_ID = "channel_id";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Retrieve the title and message from the Intent extras
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");

        // Create a notification channel if running on Android 8.0 (API 26) or higher.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Meal Notifications";
            String description = "Notifications for scheduled meals";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Build the notification using NotificationCompat.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info) // Use your own icon if desired
                .setContentTitle(title != null ? title : "Default Title")
                .setContentText(message != null ? message : "Default Message")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        // Display the notification.
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, builder.build());
    }
}
