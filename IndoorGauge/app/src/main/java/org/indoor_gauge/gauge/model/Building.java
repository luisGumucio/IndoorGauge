package org.indoor_gauge.gauge.model;

/**
 * Created by Luis Gumucio on 4/23/2016.
 */
public class Building {

    private int idEdifice;
    private String nameEdifice;

    public Building(int idEdifice, String nameEdifice) {
        this.idEdifice = idEdifice;
        this.nameEdifice = nameEdifice;
    }

    public int getIdEdifice() {
        return idEdifice;
    }

    public String getNameEdifice() {
        return nameEdifice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Building building = (Building) o;

        if (idEdifice != building.idEdifice) return false;
        return !(nameEdifice != null ? !nameEdifice.equals(building.nameEdifice) : building.nameEdifice != null);

    }

    @Override
    public int hashCode() {
        int result = idEdifice;
        result = 31 * result + (nameEdifice != null ? nameEdifice.hashCode() : 0);
        return result;
    }
}
