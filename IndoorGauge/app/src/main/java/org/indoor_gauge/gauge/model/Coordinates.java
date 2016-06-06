package org.indoor_gauge.gauge.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Ortiz Pulido Andrea Patricia <andreaortiz@javeriana.eud.co>
 * @author Corredor Merchán Jonnathan Silvestre
 *         <jonnathan.corredor@javeriana.edu.co>
 */
public class Coordinates implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idcoordenada;
    private BigInteger x;
    private BigInteger y;
    private Area idarea;
    private int idArea;
    private List<Senial> senialList;


    public Coordinates() {
    }

    public Coordinates(int idcoordenada) {
        this.idcoordenada = idcoordenada;
    }

    public Coordinates(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(int idcoordenada, BigInteger x, BigInteger y, int idArea) {
        this.idcoordenada = idcoordenada;
        this.x = x;
        this.y = y;
        this.idArea = idArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdcoordenada() {
        return idcoordenada;
    }

    public void setIdcoordenada(int idcoordenada) {
        this.idcoordenada = idcoordenada;
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public Area getIdarea() {
        return idarea;
    }

    public void setIdarea(Area idarea) {
        this.idarea = idarea;
    }

    public List<Senial> getSenialList() {
        return senialList;
    }

    public void setSenialList(List<Senial> senialList) {
        this.senialList = senialList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (idcoordenada != that.idcoordenada) return false;
        if (idArea != that.idArea) return false;
        if (x != null ? !x.equals(that.x) : that.x != null) return false;
        if (y != null ? !y.equals(that.y) : that.y != null) return false;
        if (idarea != null ? !idarea.equals(that.idarea) : that.idarea != null) return false;
        return !(senialList != null ? !senialList.equals(that.senialList) : that.senialList != null);

    }

    @Override
    public int hashCode() {
        int result = idcoordenada;
        result = 31 * result + (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (idarea != null ? idarea.hashCode() : 0);
        result = 31 * result + idArea;
        result = 31 * result + (senialList != null ? senialList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entities.Coordinates[ idcoordenada=" + idcoordenada + " ]";
    }

}
