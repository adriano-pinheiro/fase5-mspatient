package br.com.fase5.hackaton.mspatient.service;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.model.PatientModel;

public interface PatientService {

    PatientDTO save(PatientDTO patientDTO);
    PatientModel toPatientModel(PatientDTO patientDTO);
    PatientDTO toPatientDTO(PatientModel patientModel);

}
