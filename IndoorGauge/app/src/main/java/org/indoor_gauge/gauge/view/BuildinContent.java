package org.indoor_gauge.gauge.view;

import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import org.indoor_gauge.R;
import org.indoor_gauge.service_client.TaskBuildingGet;
import org.indoor_gauge.util.ActivityCaller;

public class BuildinContent extends AppCompatActivity {

    private ListView listBuilding;
    private ImageView imageBuilding;
    private TaskBuildingGet taskBuildingGet;
    private String idBuilding;
    private AlertDialog.Builder builder;
    private static String[] options = {"Detalles", "Editar", "Eliminar"};
    private Context context = this;
    private WifiManager wifiMananger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildin_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        wifiMananger = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        initComponent();
//        if (wifiMananger.isWifiEnabled()) {
//            initComponent();
//        } else {
//            Toast.makeText(getApplicationContext(), "EL WIFI ESTA DESHABILITADO!!", Toast.LENGTH_LONG).show();
//        }
    }
    private void initComponent() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar una opcion");
        builder.setItems(options, actionListener);
        builder.setNegativeButton("Cancelar", null);

        listBuilding = (ListView) findViewById(R.id.listEdifice);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ActivityCaller.CallActivity(BuildinContent.this,
//                        BuildingActivity.class, null);
            }
        });
        taskBuildingGet = new TaskBuildingGet(BuildinContent.this, listBuilding);
        taskBuildingGet.execute();

        listBuilding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                // idBuilding = ((TextView) v.findViewById(R.id.item_idBuilding)).getText().toString();
                builder.show();
            }
        });
    }

    private DialogInterface.OnClickListener actionListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case 0: // Take Photo
                    listBuilding.getAdapter().getItem(0);
                    break;
                case 1: // Choose Gallery
                    break;
                default:
                    break;
            }
        }
    };

}

