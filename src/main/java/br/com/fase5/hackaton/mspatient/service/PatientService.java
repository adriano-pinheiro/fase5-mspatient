package br.com.fase5.hackaton.mspatient.service;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    PatientDTO save(PatientDTO patientDTO);
    Page<PatientDTO> findAll(Pageable pageable);
    PatientDTO findById(String id);
    PatientDTO findByCpf(String cpf);
    PatientDTO findByRne(String rne);
    void deleteById(String id);
    PatientDTO update(String id, PatientDTO patientDTO);
}
