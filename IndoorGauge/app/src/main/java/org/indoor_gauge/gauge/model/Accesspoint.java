package org.indoor_gauge.gauge.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ortiz Pulido Andrea Patricia <andreaortiz@javeriana.eud.co>
 * @author Corredor Merchán Jonnathan Silvestre
 *         <jonnathan.corredor@javeriana.edu.co>
 */
public class Accesspoint implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idaccesspoint;
    private String nombreaccesspoint;
    private String mac;
    private Senial senial;
    private List<Senial> senialList;

    public Accesspoint() {
        senialList = new ArrayList<>();
    }

    public Accesspoint(int idaccesspoint) {
        this.idaccesspoint = idaccesspoint;
    }

    public Accesspoint(int idaccesspoint, String nombreaccesspoint,
                       String mac, Senial senial) {
        this.idaccesspoint = idaccesspoint;
        this.nombreaccesspoint = nombreaccesspoint;
        this.mac = mac;
        this.senial = senial;
    }

    public Senial getSenial() {
        return senial;
    }

    public void setSenial(Senial senial) {
        this.senial = senial;
    }

    public int getIdaccesspoint() {
        return idaccesspoint;
    }

    public void setIdaccesspoint(int idaccesspoint) {
        this.idaccesspoint = idaccesspoint;
    }

    public String getNombreaccesspoint() {
        return nombreaccesspoint;
    }

    public void setNombreaccesspoint(String nombreaccesspoint) {
        this.nombreaccesspoint = nombreaccesspoint;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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

        Accesspoint that = (Accesspoint) o;

        if (idaccesspoint != that.idaccesspoint) return false;
        if (nombreaccesspoint != null ? !nombreaccesspoint.equals(that.nombreaccesspoint) : that.nombreaccesspoint != null)
            return false;
        if (mac != null ? !mac.equals(that.mac) : that.mac != null) return false;
        if (senial != null ? !senial.equals(that.senial) : that.senial != null) return false;
        return !(senialList != null ? !senialList.equals(that.senialList) : that.senialList != null);

    }

    @Override
    public int hashCode() {
        int result = idaccesspoint;
        result = 31 * result + (nombreaccesspoint != null ? nombreaccesspoint.hashCode() : 0);
        result = 31 * result + (mac != null ? mac.hashCode() : 0);
        result = 31 * result + (senial != null ? senial.hashCode() : 0);
        result = 31 * result + (senialList != null ? senialList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entities.Accesspoint[ idaccesspoint=" + idaccesspoint + " ]";
    }

}

