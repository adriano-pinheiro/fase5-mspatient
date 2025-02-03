package br.com.fase5.hackaton.mspatient.service.impl;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.model.PatientModel;
import br.com.fase5.hackaton.mspatient.repository.PatientRepository;
import br.com.fase5.hackaton.mspatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientDTO save(PatientDTO patientDTO) {
        PatientModel patientModel = toPatientModel(patientDTO);
        patientRepository.save(patientModel);
        return patientDTO;

    }

    @Override
    public PatientModel toPatientModel(PatientDTO patientDTO) {
        return new PatientModel(
                patientDTO.getName(),
                patientDTO.getCpf(),
                patientDTO.getBirthDate(),
                patientDTO.getEmail(),
                patientDTO.getPhone(),
                patientDTO.getAddresses());
    }

    @Override
    public PatientDTO toPatientDTO(PatientModel patientModel) {
        return new PatientDTO(
                patientModel.getName(),
                patientModel.getCpf(),
                patientModel.getBirthDate(),
                patientModel.getEmail(),
                patientModel.getPhone(),
                patientModel.getAddresses());
    }
}
