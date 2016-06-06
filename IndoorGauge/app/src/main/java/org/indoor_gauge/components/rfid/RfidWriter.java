package org.indoor_gauge.components.rfid;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.AlertDialog;

import org.indoor_gauge.gauge.view.MainActivity;

/**
 * Created by Unkon on 04/06/16.
 */
public class RfidWriter {
    boolean mWriteMode = false;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mNfcPendingIntent;
    private Context context;

    public RfidWriter(Context context) {
        this.context = context;
    }

    public void writerRfid() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(context);
        mNfcPendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        enableTagWriteMode();

        new AlertDialog.Builder(context).setTitle("Touch tag to write")
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        disableTagWriteMode();
                    }

                }).create().show();
    }
    private void enableTagWriteMode() {
        mWriteMode = true;
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter[] mWriteTagFilters = new IntentFilter[] { tagDetected };
        mNfcAdapter.enableForegroundDispatch((Activity)context, mNfcPendingIntent, mWriteTagFilters, null);
    }

    private void disableTagWriteMode() {
        mWriteMode = false;
        mNfcAdapter.disableForegroundDispatch((Activity) context);
    }
}
