package mobi.ccjr.ptel.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import mobi.ccjr.ptel.service.BalanceRequestService;

public class BootCompletedReceiver
        extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                     SystemClock.elapsedRealtime(),
                                     AlarmManager.INTERVAL_DAY,
                                     //1000 * 60,
                                     getServiceIntent(context));
    }

    private PendingIntent getServiceIntent(Context context) {
        Intent intent = new Intent(context, BalanceRequestService.class);
        return PendingIntent.getService(context, 0, intent, 0);
    }

}
