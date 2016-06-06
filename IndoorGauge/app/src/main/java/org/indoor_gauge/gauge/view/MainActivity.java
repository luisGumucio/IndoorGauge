package org.indoor_gauge.gauge.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.indoor_gauge.R;
import org.indoor_gauge.components.rfid.ContentRfidActivity;
import org.indoor_gauge.util.ActivityCaller;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.home);
        Bitmap rfidIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.rfid);
        Bitmap localizadorIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.localizador);
        Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.beacon);

        gridArray.add(new Item(homeIcon,"Administrar Edificio"));
        gridArray.add(new Item(rfidIcon,"Administrar RFID"));
        gridArray.add(new Item(localizadorIcon,"Localizador"));
        gridArray.add(new Item(userIcon,"Administrar Beacon"));


        gridView = (GridView) findViewById(R.id.gridView1);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                String idBuilding = ((TextView) v.findViewById(R.id.item_text)).getText().toString();
                switch (idBuilding){
                    case "Administrar RFID":
                        ActivityCaller.CallActivity(MainActivity.this,
                                ContentRfidActivity.class, null);
                        break;
                    case "Administrar Edificio":
                        ActivityCaller.CallActivity(MainActivity.this,
                                BuildinContent.class, null);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
