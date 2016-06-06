package org.indoor_gauge.gauge.controller;

import org.json.JSONException;
import org.apache.http.client.ClientProtocolException;
import java.io.IOException;

/**
 * Created by Luis Gumucio on 4/23/2016.
 */
public interface Crud {


    public Boolean reade() throws ClientProtocolException, IOException, JSONException;
    public Boolean create() throws ClientProtocolException, IOException, JSONException;
}
