package mobi.ccjr.ptel.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.model.Balance;
import mobi.ccjr.ptel.ui.BalanceTextView;
import mobi.ccjr.ptel.ui.ExpiryTextView;

public class CurrentBalanceFragment
        extends Fragment {

    private BalanceTextView balanceTextView;
    private ExpiryTextView expiryTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.current_balance, container, false);

        balanceTextView = (BalanceTextView) v.findViewById(R.id.balance_text_view);
        expiryTextView = (ExpiryTextView) v.findViewById(R.id.expiry_text_view);

        return v;
    }

    public void setBalance(Balance balance) {
        balanceTextView.setBalance(balance);
        expiryTextView.setBalance(balance);
    }

}
