package org.indoor_gauge.gauge.model;

import java.io.Serializable;

/**
 * @author Ortiz Pulido Andrea Patricia <andreaortiz@javeriana.eud.co>
 * @author Corredor Merchán Jonnathan Silvestre
 *         <jonnathan.corredor@javeriana.edu.co>
 */
public class SenialPK implements Serializable {
    private int idsenial;
    private int idcoordenada;
    private int idaccesspoint;

    public SenialPK() {
    }

    public SenialPK(int idsenial, int idcoordenada,
                    int idaccesspoint) {
        this.idsenial = idsenial;
        this.idcoordenada = idcoordenada;
        this.idaccesspoint = idaccesspoint;
    }

    public int getIdsenial() {
        return idsenial;
    }

    public void setIdsenial(int idsenial) {
        this.idsenial = idsenial;
    }

    public int getIdcoordenada() {
        return idcoordenada;
    }

    public void setIdcoordenada(int idcoordenada) {
        this.idcoordenada = idcoordenada;
    }

    public int getIdaccesspoint() {
        return idaccesspoint;
    }

    public void setIdaccesspoint(int idaccesspoint) {
        this.idaccesspoint = idaccesspoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SenialPK senialPK = (SenialPK) o;

        if (idsenial != senialPK.idsenial) return false;
        if (idcoordenada != senialPK.idcoordenada) return false;
        return idaccesspoint == senialPK.idaccesspoint;

    }

    @Override
    public int hashCode() {
        int result = idsenial;
        result = 31 * result + idcoordenada;
        result = 31 * result + idaccesspoint;
        return result;
    }

    @Override
    public String toString() {
        return "Entities.SenialPK[ idsenial=" + idsenial + ", idcoordenada="
                + idcoordenada + ", idaccesspoint=" + idaccesspoint + " ]";
    }

}

