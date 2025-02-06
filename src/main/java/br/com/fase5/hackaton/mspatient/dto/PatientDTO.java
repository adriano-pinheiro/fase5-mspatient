package br.com.fase5.hackaton.mspatient.dto;

import br.com.fase5.hackaton.mspatient.model.AddressModel;
import java.time.LocalDate;
import java.util.List;

public class PatientDTO {

    private String id;
    private String name;
    private String cpf;
    private String rne;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private List<AddressModel> addresses;

    public PatientDTO(){}

    public PatientDTO(String id, String name, String cpf, String rne, LocalDate birthDate, String email, String phone, List<AddressModel> addresses) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rne = rne;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.addresses = addresses;
    }

    public String getId() {
        return id;
    }
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddresses(List<AddressModel> addresses) {
        this.addresses = addresses;
    }
}
