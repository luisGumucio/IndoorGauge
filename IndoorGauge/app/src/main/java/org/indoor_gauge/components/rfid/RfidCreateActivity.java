package org.indoor_gauge.components.rfid;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.indoor_gauge.R;
import org.indoor_gauge.gauge.controller.BuildingController;
import org.indoor_gauge.gauge.model.Building;
import org.indoor_gauge.service_client.ClientService;
import org.indoor_gauge.service_client.Connection;
import org.indoor_gauge.service_client.TaskBuildingGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RfidCreateActivity extends AppCompatActivity {

    boolean mWriteMode = false;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mNfcPendingIntent;
    private Spinner buildingList;
    private Spinner nroFloors;
    TextView choco_display, ice_display;
    Context context= this;
    List<Building> list = new ArrayList<Building>();
    private String grabador;
    String idBuilding;
    private String NumberFloor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfid_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        addIceCream();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btsaveRfid);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNfcAdapter = NfcAdapter.getDefaultAdapter(RfidCreateActivity.this);
                mNfcPendingIntent = PendingIntent.getActivity(RfidCreateActivity.this, 0,
                        new Intent(RfidCreateActivity.this, RfidCreateActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

                enableTagWriteMode();

                new AlertDialog.Builder(RfidCreateActivity.this).setTitle("Touch tag to write")
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                disableTagWriteMode();
                            }

                        }).create().show();
            }
        });
    }
    public void addIceCream()
    {
        List<Integer>list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);
        list1.add(7);
        list1.add(8);
        list1.add(9);
        buildingList = (Spinner) findViewById(R.id.ice_cream_spinner);
        nroFloors = (Spinner)findViewById(R.id.ice_cream_spinner1);
        nroFloors.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int pos, long id) {

                        NumberFloor = parent.getItemAtPosition(pos).toString();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        buildingList.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int pos, long id) {
                        for (Building current: list) {
                            if (current.getNameEdifice().equals(parent.getItemAtPosition(pos).toString())){
                                idBuilding =  String.valueOf(current.getIdEdifice());
                            }
                        }

                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
      TaskRfid taskRfid = new TaskRfid();
        taskRfid.execute();

        ArrayAdapter<Integer> dataAdapter1 = new ArrayAdapter<Integer>(context,android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nroFloors.setAdapter(dataAdapter1);


    }
    private void enableTagWriteMode() {
        mWriteMode = true;
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter[] mWriteTagFilters = new IntentFilter[] { tagDetected };
        mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mWriteTagFilters, null);
    }

    private void disableTagWriteMode() {
        mWriteMode = false;
        mNfcAdapter.disableForegroundDispatch(this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        // Tag writing mode
        if (mWriteMode && NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            grabador = idBuilding +"-"+NumberFloor;
           NdefRecord record = NdefRecord.createMime(grabador, grabador.getBytes());
            //NdefRecord record = null;
            NdefMessage message = new NdefMessage(new NdefRecord[]{record});
            if (writeTag(message, detectedTag)) {
                Toast.makeText(this, "Correcto al grabar en el tag", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /*
* Writes an NdefMessage to a NFC tag
*/
    public boolean writeTag(NdefMessage message, Tag tag) {
        int size = message.toByteArray().length;
        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();
                if (!ndef.isWritable()) {
                    Toast.makeText(getApplicationContext(),
                            "Error: tag not writable",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (ndef.getMaxSize() < size) {
                    Toast.makeText(getApplicationContext(),
                            "Error: tag too small",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
                ndef.writeNdefMessage(message);
                return true;
            } else {
                NdefFormatable format = NdefFormatable.get(tag);
                if (format != null) {
                    try {
                        format.connect();
                        format.format(message);
                        return true;
                    } catch (IOException e) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    private class TaskRfid extends AsyncTask<String, Void, Boolean>{
        ProgressDialog progressDialog;
        private final Connection connection = Connection.getInstance();

        List<String>stringList = new ArrayList<>();
        private BuildingController buildingController = new BuildingController();
        @Override
        protected Boolean doInBackground(String... params) {
            String url = connection.getUrl() + "BuildingController/getBuilding";
            HttpGet httpGet = ClientService.getHttpReade(url);
            Boolean result = true;
            try {
                //exectute
                HttpResponse response = ClientService.getClient().execute(httpGet);
                HttpEntity entity = response.getEntity();
                BufferedHttpEntity buffer = new BufferedHttpEntity(entity);
                InputStream iStream = buffer.getContent();
                String aux = "";
                BufferedReader r = new BufferedReader(new InputStreamReader(iStream));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    aux += line;
                }
                JSONObject jsonObject = new JSONObject(aux);
                JSONArray array = jsonObject.getJSONArray("edifice");
                Building[] buildings = new Building[array.length()];
                // Recorremos el array con los elementos cities
                for (int i = 0; i < array.length(); i++) {
                    JSONObject row = array.getJSONObject(i);
                    int idEdifice = row.getInt("idEdifice");
                    String NameEdifice;
                    NameEdifice = row.getString("NameEdifice");
                    buildings[i] = new Building(idEdifice, NameEdifice);
                    list.add(new Building(idEdifice, NameEdifice));
                    stringList.add(NameEdifice);
                }
                return result;

            } catch (ClientProtocolException e) {
                result = false;
                e.printStackTrace();
            } catch (IOException e) {
                result = false;
                e.printStackTrace();
            } catch (JSONException e) {
                result = false;
                e.printStackTrace();
            }
            return result;
        }
        /**
         * Antes de comenzar la tarea muestra el progressDialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(
                    context, "Por favor espere", "Procesando...");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, stringList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                buildingList.setAdapter(dataAdapter);
                progressDialog.dismiss();
            }
        }
    }

}
