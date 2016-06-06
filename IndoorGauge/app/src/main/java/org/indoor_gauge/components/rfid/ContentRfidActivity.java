package org.indoor_gauge.components.rfid;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.indoor_gauge.R;
import org.indoor_gauge.util.ActivityCaller;

public class ContentRfidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rfid);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab12);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCaller.CallActivity(ContentRfidActivity.this,
                        RfidCreateActivity.class, null);
            }
        });
    }
}
