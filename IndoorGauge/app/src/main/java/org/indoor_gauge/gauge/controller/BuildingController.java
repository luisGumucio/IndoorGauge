package org.indoor_gauge.gauge.controller;

import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.indoor_gauge.gauge.model.Building;
import org.indoor_gauge.gauge.model.Floor;
import org.indoor_gauge.service_client.ClientService;
import org.indoor_gauge.service_client.Connection;

import android.app.Application;

/**
 * Created by Luis Gumucio on 4/23/2016.
 */
public class BuildingController extends Application implements Crud {

    private final UUID uuid = UUID.randomUUID();
    private int randomUUIDString;
    private Building building;
    private Building[] buildings;
    private final Connection connection = Connection.getInstance();
    private TextView nameBuilding;
    private List<Floor> listFloor;

    public BuildingController() {

    }

    public BuildingController(TextView nameBuilding, List<Floor> listFloor) {
        this.nameBuilding = nameBuilding;
        this.listFloor = listFloor;
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public int getRandomUUIDString() {
        return randomUUIDString;
    }

    @Override
    public Boolean reade() throws ClientProtocolException, IOException, JSONException {
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
            buildings = new Building[array.length()];
            // Recorremos el array con los elementos cities
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                int idEdifice = row.getInt("idEdifice");
                String NameEdifice;
                NameEdifice = row.getString("NameEdifice");
                buildings[i] = new Building(idEdifice, NameEdifice);
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

    @Override
    public Boolean create() throws ClientProtocolException, IOException, JSONException {

        String url = connection.getUrl() + "BuildingController/createBuilding";
        //url y tipo de contenido
        HttpPost httppost = ClientService.getHttpPost(url);
        httppost.addHeader("Content-Type", "application/json");
        //forma el JSON y tipo de contenido
        JSONObject jsonObject = new JSONObject();
        Random rn = new Random();
        int answer = rn.nextInt(100) + 1;
        randomUUIDString = answer;
        jsonObject.put("idBuilding", answer);
        jsonObject.put("NameBuilding", nameBuilding.getText().toString());

        answer = rn.nextInt(100) + 1;
        jsonObject.put("idFloor", answer);
        for (Floor item : listFloor) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            item.getPhotobmp().compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            jsonObject.put("NameFloor", item.getNameFloor());
            jsonObject.put("NumberFloor", item.getNumberFloor());
            jsonObject.put("photo", encodedImage);
        }

        //
        StringEntity stringEntity = new StringEntity(jsonObject.toString());
        // stringEntity.setContentType((Header) new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httppost.setEntity(stringEntity);
        //ejecuta
        HttpResponse response = ClientService.getClient().execute(httppost);
        //obtiene la respuesta y transorma a objeto JSON
        String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
        JSONObject object = new JSONObject(jsonResult);
        if (object.getString("status").equals("200")) {
            return true;
        }
        return false;
    }

    /**
     * Transforma el InputStream en un String
     *
     * @return StringBuilder
     */
    private Object inputStreamToString(InputStream is) {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder;
    }
}
