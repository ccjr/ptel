package mobi.ccjr.ptel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.data.BalanceDAO;
import mobi.ccjr.ptel.model.Balance;
import mobi.ccjr.ptel.ui.BalanceTextView;
import mobi.ccjr.ptel.ui.ExpiryTextView;
import mobi.ccjr.ptel.ui.FloatingActionButton;

public class MainActivity
        extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMostRecentBalanceInfo();
        addPurchaseAirtimeButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        }

        return super.onOptionsItemSelected(item);
    }

    private void setMostRecentBalanceInfo() {
        BalanceDAO dao = new BalanceDAO(this);
        Balance balance = dao.findMostRecent();

        if (balance != null) {
            BalanceTextView balanceTextView = (BalanceTextView) findViewById(R.id.recent_balance);
            balanceTextView.setBalance(balance);

            ExpiryTextView expiryTextView = (ExpiryTextView) findViewById(R.id.recent_expiry);
            expiryTextView.setBalance(balance);
        }
    }

    private void addPurchaseAirtimeButton() {
        FloatingActionButton fabButton = new FloatingActionButton.Builder(this).withDrawable(
                getResources().getDrawable(R.drawable.ic_money))
                                                                               .withButtonColor(
                                                                                       Color.RED)
                                                                               .withGravity(
                                                                                       Gravity.BOTTOM |
                                                                                       Gravity.RIGHT)
                                                                               .withMargins(0,
                                                                                            0,
                                                                                            16,
                                                                                            16)
                                                                               .create();

        fabButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO refactor code like this into a class that handles call/sms to operator
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:233"));
                startActivity(callIntent);
            }
        });
    }
}
