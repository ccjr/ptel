package mobi.ccjr.ptel.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mobi.ccjr.ptel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstRunFragment
        extends Fragment {

    private Button checkBalanceButton;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first_run, container, false);
        checkBalanceButton = (Button) v.findViewById(R.id.check_balance);

        return v;
    }
}
