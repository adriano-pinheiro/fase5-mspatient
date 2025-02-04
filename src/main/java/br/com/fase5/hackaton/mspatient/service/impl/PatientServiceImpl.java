package br.com.fase5.hackaton.mspatient.service.impl;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.mapper.PatientMapper;
import br.com.fase5.hackaton.mspatient.model.PatientModel;
import br.com.fase5.hackaton.mspatient.repository.PatientRepository;
import br.com.fase5.hackaton.mspatient.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    @Transactional(readOnly = true)
    public Page<PatientDTO> findAll(Pageable pageable) {
        Sort sort = Sort.by("name").ascending();
        Pageable pagination =
                PageRequest.of(pageable.getPageNumber(),
                        pageable.getPageSize(), sort);

        Page<PatientModel> patientModels = patientRepository.findAll(pagination);
        return patientModels.map(PatientMapper::toPatientDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findById(String id) {
        return  PatientMapper.toPatientDTO( patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found by ID")));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findByCpfOrRne(String cpf, String rne) {
        return patientRepository.findByCpfOrRne(cpf, rne)
                .orElseThrow(() -> new RuntimeException("Patient not found by CPF or RNE"));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        patientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PatientDTO update(String id, PatientDTO patientDTO) {
        PatientDTO patientDTOExisting = PatientMapper.toPatientDTO(patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found")));

        patientDTOExisting.setName(patientDTO.getName());
        patientDTOExisting.setBirthDate(patientDTO.getBirthDate());
        patientDTOExisting.setEmail(patientDTO.getEmail());
        patientDTOExisting.setPhone(patientDTO.getPhone());
        patientDTOExisting.setAddresses(patientDTO.getAddresses());

        PatientModel patientModel = PatientMapper.toPatientModel(patientDTOExisting);
        patientModel.setId(id);
        patientRepository.save(patientModel);
        return patientDTO;
    }

}
