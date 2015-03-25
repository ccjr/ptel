package mobi.ccjr.ptel.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import mobi.ccjr.ptel.R;

public class SendFragment extends Fragment implements OnClickListener {

	private Button sendButton, callCustomerCareButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_send, container, false);

		sendButton = (Button) v.findViewById(R.id.send_sms_button);
		sendButton.setOnClickListener(this);

        callCustomerCareButton = (Button) v.findViewById(R.id.call_customer_care);
        callCustomerCareButton.setOnClickListener(this);

		return v;
	}

	@SuppressLint("UnlocalizedSms")
	@Override
	public void onClick(View v) {
        switch (v.getId())
        {
        case R.id.send_sms_button:
            sendSMS7801();
            break;
        case R.id.call_customer_care:
            callCustomerCare();
            break;
        }
    }

    private void sendSMS7801()
    {
        // send SMS with the word BAL to 7801
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("7801", null, "BAL", null, null);
    }

    private void callCustomerCare()
    {
        // TODO Call Customer Care....611 [SEND]
    }



}
