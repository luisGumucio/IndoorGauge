package org.indoor_gauge.gauge.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * @author Ortiz Pulido Andrea Patricia
 * @author Corredor Merchán Jonnathan Silvestre
 */
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idarea;
    private String nombrearea;
    private List<Area> areaList;
    private List<Area> areaList1;
    private List<Coordinates> coordenadaList;
    private Floor piso;
    private int idPiso;

    public Area() {
        coordenadaList = new ArrayList<>();
    }

    public Area(int idarea) {
        this.idarea = idarea;
    }

    public Area(int idarea, String nombrearea, int idPiso) {
        this.idarea = idarea;
        this.nombrearea = nombrearea;
        this.idPiso = idPiso;
        coordenadaList = new ArrayList<>();
    }

    public int getIdarea() {
        return this.idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public String getNombrearea() {
        return nombrearea;
    }

    public void setNombrearea(String nombrearea) {
        this.nombrearea = nombrearea;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public List<Area> getAreaList1() {
        return areaList1;
    }

    public void setAreaList1(List<Area> areaList1) {
        this.areaList1 = areaList1;
    }

    public List<Coordinates> getCoordenadaList() {
        return coordenadaList;
    }

    public void setCoordenadaList(List<Coordinates> coordenadaList) {
        this.coordenadaList = coordenadaList;
    }
    public void addCordenadas(Coordinates coordenada){
        coordenadaList.add(coordenada);
    }

    public Floor getPiso() {
        return piso;
    }

    public void setPiso(Floor piso) {
        this.piso = piso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        if (idarea != area.idarea) return false;
        if (idPiso != area.idPiso) return false;
        if (nombrearea != null ? !nombrearea.equals(area.nombrearea) : area.nombrearea != null)
            return false;
        if (areaList != null ? !areaList.equals(area.areaList) : area.areaList != null)
            return false;
        if (areaList1 != null ? !areaList1.equals(area.areaList1) : area.areaList1 != null)
            return false;
        if (coordenadaList != null ? !coordenadaList.equals(area.coordenadaList) : area.coordenadaList != null)
            return false;
        return !(piso != null ? !piso.equals(area.piso) : area.piso != null);

    }

    @Override
    public int hashCode() {
        int result = idarea;
        result = 31 * result + (nombrearea != null ? nombrearea.hashCode() : 0);
        result = 31 * result + (areaList != null ? areaList.hashCode() : 0);
        result = 31 * result + (areaList1 != null ? areaList1.hashCode() : 0);
        result = 31 * result + (coordenadaList != null ? coordenadaList.hashCode() : 0);
        result = 31 * result + (piso != null ? piso.hashCode() : 0);
        result = 31 * result + idPiso;
        return result;
    }

    @Override
    public String toString() {
        return "Entities.Area[ idarea=" + idarea + " ]";
    }

    public int getIdPiso() {
        return idPiso;
    }

    public void setIdPiso(int idPiso) {
        this.idPiso = idPiso;
    }
}

