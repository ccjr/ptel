package mobi.ccjr.ptel;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import mobi.ccjr.ptel.data.BalanceDAO;
import mobi.ccjr.ptel.model.Balance;

public class MainActivity
        extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMostRecentBalanceInfo();
    }

    private void setMostRecentBalanceInfo() {
        BalanceDAO dao = new BalanceDAO(this);
        Balance balance = dao.findMostRecent();

        if (balance != null) {
            TextView balanceTextView = (TextView)findViewById(R.id.recent_balance);
            balanceTextView.setText(balance.getBalance());

            TextView expiryTextView = (TextView)findViewById(R.id.recent_expiry);
            expiryTextView.setText(balance.getExpiry());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
