package br.com.fase5.hackaton.mspatient.mapper;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.model.PatientModel;

public class PatientMapper {

    private PatientMapper(){}

    public static PatientModel toPatientModel(PatientDTO patientDTO) {
        return new PatientModel(
                patientDTO.getName(),
                patientDTO.getCpf(),
                patientDTO.getRne(),
                patientDTO.getBirthDate(),
                patientDTO.getEmail(),
                patientDTO.getPhone(),
                patientDTO.getAddresses());
    }

    public static PatientDTO toPatientDTO(PatientModel patientModel) {
        return new PatientDTO(
                patientModel.getName(),
                patientModel.getCpf(),
                patientModel.getRne(),
                patientModel.getBirthDate(),
                patientModel.getEmail(),
                patientModel.getPhone(),
                patientModel.getAddresses());
    }

}
