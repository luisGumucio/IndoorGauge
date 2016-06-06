package org.indoor_gauge.gauge.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author Ortiz Pulido Andrea Patricia <andreaortiz@javeriana.eud.co>
 * @author Corredor Merchán Jonnathan Silvestre
 *         <jonnathan.corredor@javeriana.edu.co>
 */
public class Senial implements Serializable {

    private static final long serialVersionUID = 1L;
    protected SenialPK senialPK;
    private BigInteger intensidad;
    private Coordinates coordenada;
    private Accesspoint accesspoint;

    public Senial() {
    }

    public Senial(SenialPK senialPK) {
        this.senialPK = senialPK;
    }

    public Senial(SenialPK senialPK, BigInteger intensidad) {
        this.senialPK = senialPK;
        this.intensidad = intensidad;
    }

    public Senial(int idsenial, int idcoordenada,
                  int idaccesspoint, BigInteger intensidad) {
        this.senialPK = new SenialPK(idsenial, idcoordenada, idaccesspoint);
        this.intensidad = intensidad;
    }

    public SenialPK getSenialPK() {
        return senialPK;
    }

    public void setSenialPK(SenialPK senialPK) {
        this.senialPK = senialPK;
    }

    public BigInteger getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(BigInteger intensidad) {
        this.intensidad = intensidad;
    }

    public Coordinates getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordinates coordenada) {
        this.coordenada = coordenada;
    }

    public Accesspoint getAccesspoint() {
        return accesspoint;
    }

    public void setAccesspoint(Accesspoint accesspoint) {
        this.accesspoint = accesspoint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (senialPK != null ? senialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Senial)) {
            return false;
        }
        Senial other = (Senial) object;
        if ((this.senialPK == null && other.senialPK != null)
                || (this.senialPK != null && !this.senialPK
                .equals(other.senialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Senial[ senialPK=" + senialPK + " ]";
    }

}

