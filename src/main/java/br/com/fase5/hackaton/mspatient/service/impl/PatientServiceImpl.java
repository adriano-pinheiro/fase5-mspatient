package br.com.fase5.hackaton.mspatient.service.impl;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.mapper.PatientMapper;
import br.com.fase5.hackaton.mspatient.model.PatientModel;
import br.com.fase5.hackaton.mspatient.repository.PatientRepository;
import br.com.fase5.hackaton.mspatient.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public PatientDTO save(PatientDTO patientDTO) {
        PatientModel patientModel = PatientMapper.toPatientModel(patientDTO);
        patientRepository.save(patientModel);
        return patientDTO;

    }

}
