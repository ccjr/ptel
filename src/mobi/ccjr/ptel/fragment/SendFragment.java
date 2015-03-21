package mobi.ccjr.ptel.fragment;

import mobi.ccjr.ptel.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SendFragment extends Fragment implements OnClickListener {

	private Button sendButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_send, container, false);
		sendButton = (Button) v.findViewById(R.id.send_sms_button);
		sendButton.setOnClickListener(this);
		return v;
	}

	@SuppressLint("UnlocalizedSms")
	@Override
	public void onClick(View v) {
		// send SMS with the word BAL to 7801
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage("7801", null, "BAL", null, null);
	}

}
