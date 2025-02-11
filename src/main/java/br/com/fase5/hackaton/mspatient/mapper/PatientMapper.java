package br.com.fase5.hackaton.mspatient.mapper;

import br.com.fase5.hackaton.mspatient.dto.AddressDTO;
import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.model.AddressModel;
import br.com.fase5.hackaton.mspatient.model.PatientModel;
import java.util.Collections;

public class PatientMapper {

    private PatientMapper(){}

    public static PatientModel toPatientModel(PatientDTO patientDTO) {
        return new PatientModel(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getCpf(),
                patientDTO.getRne(),
                patientDTO.getBirthDate(),
                patientDTO.getEmail(),
                patientDTO.getPhone(),
                patientDTO.getAddresses() != null
                        ? patientDTO.getAddresses().stream().map(PatientMapper::toAddressModel).toList()
                        : Collections.emptyList());
    }

    public static AddressModel toAddressModel(AddressDTO addressDTO) {
        return new AddressModel(
                addressDTO.getStreet(),
                addressDTO.getNumber(),
                addressDTO.getComplement(),
                addressDTO.getNeighborhood(),
                addressDTO.getCity(),
                addressDTO.getState(),
                addressDTO.getZipCode());
    }

    public static PatientDTO toPatientDTO(PatientModel patientModel) {
        return new PatientDTO(
                patientModel.getId(),
                patientModel.getName(),
                patientModel.getCpf(),
                patientModel.getRne(),
                patientModel.getBirthDate(),
                patientModel.getEmail(),
                patientModel.getPhone(),
                patientModel.getAddresses() != null
                        ? patientModel.getAddresses().stream().map(PatientMapper::toAddressDTO).toList()
                        : Collections.emptyList());
    }

    public static AddressDTO toAddressDTO(AddressModel addressModel) {
        return new AddressDTO(
                addressModel.getStreet(),
                addressModel.getNumber(),
                addressModel.getComplement(),
                addressModel.getNeighborhood(),
                addressModel.getCity(),
                addressModel.getState(),
                addressModel.getZipCode());
    }

}
