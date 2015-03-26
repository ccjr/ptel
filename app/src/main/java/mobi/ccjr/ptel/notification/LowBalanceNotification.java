package mobi.ccjr.ptel.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import mobi.ccjr.ptel.MainActivity;
import mobi.ccjr.ptel.R;

public class LowBalanceNotification {

    public void notify(Context context) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_action_warning)
                        .setContentTitle("PTEL Low Balance")
                        .setContentText("Your balance is getting low - $2.50.")
                        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
        mBuilder.setContentIntent(contentIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }

}
