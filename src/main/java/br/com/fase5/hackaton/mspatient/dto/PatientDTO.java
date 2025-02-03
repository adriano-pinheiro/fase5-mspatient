package br.com.fase5.hackaton.mspatient.dto;

import br.com.fase5.hackaton.mspatient.model.AddressModel;
import java.time.LocalDate;
import java.util.List;

public class PatientDTO {

    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private List<AddressModel> addresses;

    public PatientDTO(){

    }

    public PatientDTO(String name, String cpf, LocalDate birthDate, String email, String phone, List<AddressModel> addresses) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
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

}
