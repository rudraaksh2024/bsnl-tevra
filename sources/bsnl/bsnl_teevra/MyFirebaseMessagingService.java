package bsnl.bsnl_teevra;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public void onNewToken(String str) {
        super.onNewToken(str);
        Log.d("FCM TOKEN", str);
        getSharedPreferences("myloginpreference", 0).edit().putString("Pref_fcmtoken", str).commit();
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.v(FirebaseMessaging.INSTANCE_ID_SCOPE, remoteMessage.toString());
        if (remoteMessage.getData() != null) {
            Show_Notification1(remoteMessage.getData());
        }
    }

    private void Show_Notification1(Map<String, String> map) {
        Intent intent;
        Map<String, String> map2 = map;
        if (isAppOnForeground().booleanValue()) {
            intent = new Intent(this, DashBoard_New.class);
        } else {
            intent = new Intent(this, Login_Splash_Activity.class);
        }
        intent.addFlags(67108864);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 67108864);
        String str = map2.get("notify");
        if (str.equals("PMS")) {
            String str2 = map2.get("title");
            String str3 = map2.get(NotificationCompat.CATEGORY_ALARM);
            String str4 = map2.get("body");
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            PendingIntent pendingIntent = activity;
            NotificationChannel notificationChannel = new NotificationChannel("BSNl_TEEVRA-FCM", "PCM", 4);
            notificationChannel.enableLights(true);
            notificationChannel.setDescription("NETWORK");
            notificationChannel.setVibrationPattern(new long[]{300, 300, 300});
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(1);
            notificationManager.createNotificationChannel(notificationChannel);
            RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notify_pms_collapse);
            remoteViews.setTextViewText(R.id.title, str2);
            remoteViews.setTextViewText(R.id.message, str4);
            NotificationManager notificationManager2 = notificationManager;
            RemoteViews remoteViews2 = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notify_pms_expand);
            remoteViews2.setTextViewText(R.id.title, str2);
            remoteViews2.setTextViewText(R.id.message, str4);
            remoteViews2.setTextViewText(R.id.desc, map2.get("desc"));
            remoteViews2.setTextViewText(R.id.loc, map2.get("location"));
            remoteViews2.setTextViewText(R.id.interfac, map2.get("interface"));
            remoteViews2.setTextViewText(R.id.traffic, map2.get("traffic"));
            remoteViews2.setTextViewText(R.id.time, map2.get("time"));
            if (str3.contains("MAJOR")) {
                remoteViews.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_major);
                remoteViews2.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_major);
            } else if (str3.contains("MINOR")) {
                remoteViews.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_minor);
                remoteViews2.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_minor);
            } else if (str3.contains("NORMAL")) {
                remoteViews.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_normal);
                remoteViews2.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_normal);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder((Context) this, "BSNl_TEEVRA-FCM");
            builder.setAutoCancel(false).setSmallIcon((int) R.drawable.ic_action_icon).setDefaults(3).setPriority(2).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setCustomContentView(remoteViews).setCustomBigContentView(remoteViews2).setContentIntent(pendingIntent);
            notificationManager2.notify(new Random().nextInt(1000000), builder.build());
        } else if (str.equals("OUTAGE")) {
            String str5 = map2.get("title");
            String str6 = map2.get(NotificationCompat.CATEGORY_ALARM);
            String str7 = map2.get("body");
            NotificationManager notificationManager3 = (NotificationManager) getSystemService("notification");
            NotificationChannel notificationChannel2 = new NotificationChannel("BSNl_TEEVRA-FCM", "OUTAGE", 4);
            notificationChannel2.enableLights(true);
            notificationChannel2.setDescription("NETWORK");
            notificationChannel2.setVibrationPattern(new long[]{300, 300, 300});
            notificationChannel2.setShowBadge(true);
            notificationChannel2.setLockscreenVisibility(1);
            notificationManager3.createNotificationChannel(notificationChannel2);
            RemoteViews remoteViews3 = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notify_outage_collapse);
            remoteViews3.setTextViewText(R.id.title, str5);
            remoteViews3.setTextViewText(R.id.message, str7);
            NotificationManager notificationManager4 = notificationManager3;
            RemoteViews remoteViews4 = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notify_outage_expand);
            remoteViews4.setTextViewText(R.id.title, str5);
            remoteViews4.setTextViewText(R.id.message, str7);
            remoteViews4.setTextViewText(R.id.desc, map2.get("desc"));
            remoteViews4.setTextViewText(R.id.loc, map2.get("location"));
            remoteViews4.setTextViewText(R.id.outage, map2.get("outage_duration"));
            remoteViews4.setTextViewText(R.id.time, map2.get("time"));
            if (str6.contains("MAJOR")) {
                remoteViews3.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_major);
                remoteViews4.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_major);
            } else if (str6.contains("MINOR")) {
                remoteViews3.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_minor);
                remoteViews4.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_minor);
            } else if (str6.contains("NORMAL")) {
                remoteViews3.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_normal);
                remoteViews4.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_normal);
            }
            NotificationCompat.Builder builder2 = new NotificationCompat.Builder((Context) this, "BSNl_TEEVRA-FCM");
            builder2.setAutoCancel(false).setSmallIcon((int) R.drawable.ic_action_icon).setDefaults(3).setPriority(2).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setCustomContentView(remoteViews3).setCustomBigContentView(remoteViews4).setContentIntent(activity);
            notificationManager4.notify(new Random().nextInt(1000000), builder2.build());
        }
    }

    private void Show_Notification(String str, String str2, String str3, String str4, String str5, String str6) {
        Intent intent;
        String str7 = str;
        String str8 = str2;
        if (isAppOnForeground().booleanValue()) {
            intent = new Intent(this, DashBoard_New.class);
        } else {
            intent = new Intent(this, Login_Splash_Activity.class);
        }
        intent.addFlags(67108864);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, BasicMeasure.EXACTLY);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        NotificationChannel notificationChannel = new NotificationChannel("BSNl_TEEVRA-FCM", "mansoor", 4);
        notificationChannel.enableLights(true);
        notificationChannel.setDescription("hi");
        notificationChannel.setVibrationPattern(new long[]{300, 300, 300});
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(1);
        notificationManager.createNotificationChannel(notificationChannel);
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notify_outage_collapse);
        remoteViews.setTextViewText(R.id.title, str7);
        remoteViews.setTextViewText(R.id.message, str8);
        remoteViews.setImageViewResource(R.id.icon, R.drawable.bsnl_logo);
        RemoteViews remoteViews2 = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notify_outage_expand);
        remoteViews2.setTextViewText(R.id.title, str7);
        remoteViews2.setTextViewText(R.id.message, str8);
        remoteViews2.setTextViewText(R.id.desc, str3);
        if (str8.contains("Offline")) {
            remoteViews.setTextColor(R.id.message, getResources().getColor(R.color.colorRed));
            remoteViews2.setTextColor(R.id.message, getResources().getColor(R.color.colorRed));
            remoteViews.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_major);
            remoteViews2.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_major);
        } else if (str8.contains("Live")) {
            remoteViews.setTextColor(R.id.message, getResources().getColor(R.color.colorGreen));
            remoteViews2.setTextColor(R.id.message, getResources().getColor(R.color.colorGreen));
            remoteViews.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_normal);
            remoteViews2.setImageViewResource(R.id.img_alarm, R.drawable.ic_new_notification_normal);
        }
        remoteViews2.setTextViewText(R.id.loc, str4);
        remoteViews2.setTextViewText(R.id.outage, str5);
        remoteViews2.setTextViewText(R.id.time, str6);
        remoteViews2.setImageViewResource(R.id.icon, R.drawable.bsnl_logo);
        NotificationCompat.Builder builder = new NotificationCompat.Builder((Context) this, "BSNl_TEEVRA-FCM");
        builder.setAutoCancel(true).setSmallIcon((int) R.drawable.bsnl_logo).setDefaults(3).setPriority(2).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setCustomContentView(remoteViews).setCustomBigContentView(remoteViews2).setContentIntent(activity);
        notificationManager.notify(new Random().nextInt(1000000), builder.build());
    }

    private Boolean isAppOnForeground() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getApplicationContext().getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = getApplicationContext().getPackageName();
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.importance == 100 && next.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }
}
