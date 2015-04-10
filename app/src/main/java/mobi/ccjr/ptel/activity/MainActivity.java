package mobi.ccjr.ptel.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.data.BalanceDAO;
import mobi.ccjr.ptel.fragment.CurrentBalanceFragment;
import mobi.ccjr.ptel.model.Balance;
import mobi.ccjr.ptel.receiver.BalanceMessageReceiver;
import mobi.ccjr.ptel.receiver.BootCompletedReceiver;
import mobi.ccjr.ptel.ui.FloatingActionButton;
import mobi.ccjr.ptel.utils.Caller;

public class MainActivity
        extends Activity {

    private BalanceMessageReceiver balanceMessageReceiver;
    private CurrentBalanceFragment currentBalanceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        currentBalanceFragment = (CurrentBalanceFragment) fm.findFragmentById(R.id.current_balance_fragment);

        setMostRecentBalanceInfo();
        addPurchaseAirtimeButton();
        //enableBootReceiver();
        registerBalanceMessageReceiver();
    }

    private void registerBalanceMessageReceiver() {
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        balanceMessageReceiver = new BalanceMessageReceiver();
        balanceMessageReceiver.setActivity(this);
        registerReceiver(balanceMessageReceiver, filter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_settings:
            Intent intent = new Intent(this, PrefsActivity.class);
            startActivity(intent);
            break;
        case R.id.call_customer_care:
            Caller.callCustomerService(this);
            break;
        case R.id.check_balance_menu_item:
            Caller.checkBalance(this);
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void enableBootReceiver() {
        ComponentName receiver = new ComponentName(this, BootCompletedReceiver.class);
        PackageManager pm = getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                                      PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                      PackageManager.DONT_KILL_APP);
    }

    private void setMostRecentBalanceInfo() {
        BalanceDAO dao = new BalanceDAO(this);
        Balance balance = dao.findMostRecent();

        if (balance != null) {
            currentBalanceFragment.setBalance(balance);
        }
    }

    private void addPurchaseAirtimeButton() {
        FloatingActionButton fabButton = new FloatingActionButton.Builder(this).withDrawable(
                getResources().getDrawable(R.drawable.ic_money))
                                                                               .withButtonColor(
                                                                                       Color.RED)
                                                                               .withGravity(
                                                                                       Gravity.BOTTOM |
                                                                                       Gravity.END)
                                                                               .withMargins(0,
                                                                                            0,
                                                                                            16,
                                                                                            16)
                                                                               .create();

        fabButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Caller.callAddCredit(getApplicationContext());
            }
        });
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(balanceMessageReceiver);
        super.onDestroy();
    }

    public void onBalanceUpdate() {
        Toast toast = Toast.makeText(this, R.string.balance_update, Toast.LENGTH_SHORT);
        toast.show();

        setMostRecentBalanceInfo();
    }
}
