package org.indoor_gauge.service_client;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by RobertoCarlos on 4/23/2016.
 */
public class ClientService {

    private static HttpClient httpclient;
    private static ClientService ourInstance = new ClientService();

    public static ClientService getInstance() {
        return ourInstance;
    }

    private ClientService() {
        httpclient = new DefaultHttpClient();
    }

    public static HttpClient getClient() {
        return httpclient;
    }

    public static HttpGet getHttpReade(String url) {
        HttpGet httpGet;
        return httpGet = new HttpGet(url);
    }

    public static HttpPost getHttpPost(String url) {
        return new HttpPost(url);
    }
}
