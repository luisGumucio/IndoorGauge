package org.indoor_gauge.service_client;

/**
 * Created by RobertoCarlos on 4/23/2016.
 */
public class Connection {

    private final String Url = "http://recordatorio.esy.es/index.php/";
    private static Connection ourInstance = new Connection();

    public static Connection getInstance() {
        return ourInstance;
    }

    private Connection() {
    }


    public String getUrl(){
        return Url;
    }
}
