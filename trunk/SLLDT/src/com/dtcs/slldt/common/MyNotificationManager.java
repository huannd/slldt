package com.dtcs.slldt.common;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.dtcs.slldt.activity.MainActivity;
import com.dtcs.slldt.gcmservice.GCMManagerMessage;
import com.edu.ebookcontact.R;

/**
 * @author TND
 * 
 */
public class MyNotificationManager {

    private static final String LOG_TAG = MyNotificationManager.class.getSimpleName();

    private static final int ICON = R.drawable.ic_launcher;
    private static final String TEXT_TITLE = "Imadoko";


    private static int requestId = 1101;

    private static final long[] VIBRATE = new long[] {1000, 2000, 3000};


    private String mMessage;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    private MyNotificationManager() {}

    public static MyNotificationManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void showNotify(Context pContext) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(pContext);
        builder.setSmallIcon(ICON);
        builder.setContentTitle(pContext.getResources().getString(R.string.app_name));
        String content = getMessage();
        builder.setContentText(content);
        builder.setAutoCancel(true);
        
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        
        builder.setSound(uri);
        
        builder.setVibrate(VIBRATE);

        final Intent intent = new Intent(pContext, MainActivity.class);
        intent.putExtra(GCMManagerMessage.GCM_MSG_KEY, content);
        intent.putExtra(ICommonDefine.SCREEN_KEY, ICommonDefine.SCREEN_MAIN);
        // intent.setAction(action)

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(pContext, requestId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = this.getNotificationService(pContext);
        notificationManager.notify(requestId, builder.build());

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private NotificationManager getNotificationService(Context pContext) {
        return (NotificationManager) pContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    private static class SingletonHolder {
        public static final MyNotificationManager INSTANCE = new MyNotificationManager();
    }

}
