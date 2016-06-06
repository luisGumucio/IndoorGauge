package org.indoor_gauge.service_client;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;
import org.indoor_gauge.R;
import org.json.JSONException;

import java.io.IOException;

import org.indoor_gauge.gauge.model.Building;
import org.indoor_gauge.gauge.controller.BuildingController;

/**
 * Created by RobertoCarlos on 4/23/2016.
 */
public class TaskBuildingGet extends AsyncTask<String, Void, Boolean> {

    private ProgressDialog progressDialog;
    private Activity context;
    private BuildingController manageBuilding;
    private ImageView imageMap;
    private ListView listBuilding;



    public TaskBuildingGet(Activity context, ListView listBuilding) {
        this.context = context;
        this.listBuilding = listBuilding;
        manageBuilding = new BuildingController();

    }

    public TaskBuildingGet(Activity context){
        this.context = context;
        manageBuilding = new BuildingController();
    }
    public BuildingController getManageBuilding() {
        return manageBuilding;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        Boolean result = false;
        try {
            result = manageBuilding.reade();
            progressDialog.dismiss();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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
            AdapterEdifice adapter = new AdapterEdifice();
            listBuilding.setAdapter(adapter);
        }
    }
    class AdapterEdifice extends ArrayAdapter<Building> {

        AdapterEdifice() {
            super(context, R.layout.listitem_building, manageBuilding.getBuildings());
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;
            ViewHolder holder;

            if (item == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.listitem_building, null);

                holder = new ViewHolder();
                holder.titulo = (TextView) item.findViewById(R.id.LblTitulo);
                holder.idBuilding = (TextView) item.findViewById(R.id.item_idBuilding);
                imageMap = (ImageView) item.findViewById(R.id.menuOptions);
                item.setTag(holder);

            } else {
                holder = (ViewHolder) item.getTag();
            }

            holder.titulo.setText(manageBuilding.getBuildings()[position].getNameEdifice());
//            holder.subtitulo.setText(manageBuilding.getBuildings()[position].getIdEdifice());

            return (item);
        }
    }

    static class ViewHolder {

        TextView titulo;
        TextView idBuilding;
    }

}
