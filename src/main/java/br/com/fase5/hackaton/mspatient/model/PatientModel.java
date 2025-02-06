package br.com.fase5.hackaton.mspatient.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "patient")
public class PatientModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;
    private String cpf;
    private String rne;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private List<AddressModel> addresses;

    public PatientModel(String name, String cpf, String rne, LocalDate birthDate, String email, String phone, List<AddressModel> addresses) {
        this.name = name;
        this.cpf = cpf;
        this.rne = rne;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.addresses = addresses;
    }

    public PatientModel() {}

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRne() {
        return rne;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<AddressModel> getAddresses() {
        return addresses;
    }

    public void setId(String id) {
        this.id = id;
    }
}
