package mobi.ccjr.ptel.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.service.BalanceRequestService;

public class SendFragment
        extends Fragment
        implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_send, container, false);

        Button sendButton = (Button) v.findViewById(R.id.send_sms_button);
        sendButton.setOnClickListener(this);

        Button callCustomerCareButton = (Button) v.findViewById(R.id.call_customer_care);
        callCustomerCareButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.send_sms_button:
            sendSMS7801();
            break;
        case R.id.call_customer_care:
            callCustomerCare();
            break;
        }
    }

    private void sendSMS7801() {
        Intent intent = new Intent(Intent.ACTION_SYNC,
                                   null,
                                   this.getActivity(),
                                   BalanceRequestService.class);
        this.getActivity().startService(intent);
    }

    private void callCustomerCare() {
        // call customer service at 611
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:611"));
        startActivity(callIntent);
    }
}
