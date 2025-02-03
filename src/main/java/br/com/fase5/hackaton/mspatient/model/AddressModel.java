package br.com.fase5.hackaton.mspatient.model;

import java.io.Serial;
import java.io.Serializable;

public class AddressModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

}
