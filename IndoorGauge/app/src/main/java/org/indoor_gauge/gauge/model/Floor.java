package org.indoor_gauge.gauge.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;

/**
 * Created by Luis Gumucio on 24/04/16.
 */
public class Floor {

    private int idFloor;
    private String nameFloor;
    private int numberFloor;
    private int idImg;
    private Drawable drawable;
    private Bitmap photobmp;
    private String data;
    public Floor(){}

    public Floor(int idFloor, String nameFloor, int numberFloor, int idImg, Drawable drawable, Bitmap photobmp) {
        this.idFloor = idFloor;
        this.nameFloor = nameFloor;
        this.numberFloor = numberFloor;
        this.idImg = idImg;
        this.drawable = drawable;
        this.photobmp = photobmp;
    }


    public Floor(int idFloor, String nameFloor, int numberFloor) {
        this.idFloor = idFloor;
        this.nameFloor = nameFloor;
        this.numberFloor = numberFloor;
    }

    public String getData() {
        return data;
    }
    public Bitmap getPhotobmp() {
        return photobmp;
    }

    public void setPhotobmp(String data) {
        this.data = data;
        try {
            byte[] byteData = Base64.decode(data, Base64.DEFAULT);
            this.photobmp = BitmapFactory.decodeByteArray(byteData, 0,
                    byteData.length);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public int getIdFloor() {
        return idFloor;
    }

    public void setIdFloor(int idFloor) {
        this.idFloor = idFloor;
    }

    public String getNameFloor() {
        return nameFloor;
    }

    public void setNameFloor(String nameFloor) {
        this.nameFloor = nameFloor;
    }

    public int getNumberFloor() {
        return numberFloor;
    }

    public void setNumberFloor(int numberFloor) {
        this.numberFloor = numberFloor;
    }

    public int getIdImg() {
        return idImg;
    }

    public void setIdImg(int idImg) {
        this.idImg = idImg;
    }
}
