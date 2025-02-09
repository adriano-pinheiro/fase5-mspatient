package br.com.fase5.hackaton.mspatient.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;
import java.util.List;

public class PatientDTO {

    private String id;

    @NotBlank(message = "O Nome deve ser informado.")
    private String name;

    @CPF(message = "O CPF informado é inválido.")
    private String cpf;

    private String rne;
    private LocalDate birthDate;

    private String email;
    private String phone;
    private List<AddressDTO> addresses;

    public PatientDTO(){}

    public PatientDTO(String id, String name, String cpf, String rne, LocalDate birthDate, String email, String phone, List<AddressDTO> addresses) {
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

    public List<AddressDTO> getAddresses() {
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

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRne(String rne) {
        this.rne = rne;
    }
}
