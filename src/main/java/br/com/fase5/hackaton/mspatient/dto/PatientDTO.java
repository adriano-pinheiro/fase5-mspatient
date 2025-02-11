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

    public PatientDTO(String id, String name, String cpf, String rne, List<AddressDTO> addresses) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rne = rne;
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

}
