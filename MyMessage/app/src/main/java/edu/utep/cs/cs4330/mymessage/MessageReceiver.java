package edu.utep.cs.cs4330.mymessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {

    private String messageSender;
    private String messageBody;
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        this.context = context;
        if (bundle == null) {
            return;
        }
        parseMessage(bundle);
        Intent mIntent = new Intent(context, MainActivity.class);
        mIntent.putExtra("msgSender", messageSender);
        mIntent.putExtra("msgBody", messageBody);
        showToast("Received Message BR");
//        context.startActivity(mIntent);
    }

    /** Parse the message contained in the given bundle. */
    private void parseMessage(@NonNull Bundle bundle) {
        messageSender = messageBody = null;
        for (Object pdu: (Object[]) bundle.get("pdus")) {
            SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
            messageSender = msg.getOriginatingAddress();
            messageBody = msg.getMessageBody();
        }
    }

    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
