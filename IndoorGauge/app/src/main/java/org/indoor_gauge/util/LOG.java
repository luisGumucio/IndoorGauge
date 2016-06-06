package org.indoor_gauge.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import android.os.Environment;
import android.util.Log;

import org.indoor_gauge.gauge.model.Coordinates;


/**
 * @author Ortiz Pulido Andrea Patricia <andreaortiz@javeriana.eud.co>
 * @author Corredor Merchán Jonnathan Silvestre <jonnathan.corredor@javeriana.edu.co>
 */
public class LOG {

    public static void printLOG(Map<Coordinates, Float> m,String begin, String filename)
    {

        for(Map.Entry<Coordinates, Float> entry: m.entrySet())
        {
            String linea=begin;
            linea += entry.getKey().getIdarea().getIdarea()+"\t"+entry.getKey().getX()+"\t"+entry.getKey().getY()+"\t"+entry.getValue();
            addStringToFile(linea, filename);
        }

    }

    public static void printLOG(List<Coordinates> m,String begin, String filename)
    {
        for(Coordinates c: m)
        {
            String linea=begin;
            linea += "\t"+c.getIdarea().getIdarea()+"\t"+c.getX()+"\t"+c.getY();
            addStringToFile(linea, filename);
        }
    }



    /***
     * Escribe una linea en el archivo
     * @param datos a insertar
     * @param nameFile nombre del archivo
     */
    public static boolean addStringToFile(String datos, String nameFile)
    {
        File path = Environment.getExternalStoragePublicDirectory("");
        File file = new File(path, nameFile+".txt");

        try {
            path.mkdirs();
            BufferedWriter buff = new BufferedWriter(new FileWriter(file,true));
            buff.append(datos);
            buff.newLine();
            buff.flush();
            buff.close();
        }
        catch (IOException e)
        {
            Log.i("ERROR i/o", e.getMessage());
            return false;
        }
        return true;
    }

}

