package br.com.fase5.hackaton.mspatient.service.impl;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.exception.ControllerNotFoundException;
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

        if (validateCpfOrRne(patientDTO.getCpf(), patientDTO.getRne())) {
            throw new ControllerNotFoundException("O CPF ou RNE deve ser informado.");
        }

        if (patientExisting(patientDTO.getCpf(), patientDTO.getRne())) {
            throw new ControllerNotFoundException("O paciente já existe com este CPF ou RNE.");
        }

        return PatientMapper.toPatientDTO(patientRepository.save(patientModel));

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
                .orElseThrow(() -> new ControllerNotFoundException("O paciente não foi localizado com o ID informado.")));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findByCpf(String cpf) {
        return patientRepository.findByCpf(cpf)
                .orElseThrow(() -> new ControllerNotFoundException("O paciente não foi localizado com o CPF informado."));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findByRne(String rne) {
        return patientRepository.findByRne(rne)
                .orElseThrow(() -> new ControllerNotFoundException("O paciente não foi localizado com o RNE informado."));
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
                .orElseThrow(() -> new ControllerNotFoundException("O paciênte não foi localizado com o ID informado.")));

        patientDTOExisting.setName(patientDTO.getName());
        patientDTOExisting.setBirthDate(patientDTO.getBirthDate());
        patientDTOExisting.setEmail(patientDTO.getEmail());
        patientDTOExisting.setPhone(patientDTO.getPhone());
        patientDTOExisting.setAddresses(patientDTO.getAddresses());

        PatientModel patientModel = PatientMapper.toPatientModel(patientDTOExisting);
        patientModel.setId(id);
        return PatientMapper.toPatientDTO(patientRepository.save(patientModel));
    }

    public boolean patientExisting(String cpf, String rne) {
        boolean existingCpf = false;
        boolean existingRne = false;

        if (cpf != null){
            existingCpf = patientRepository.existsByCpf(cpf);
        }

        if (rne != null){
            existingRne = patientRepository.existsByRne(rne);
        }

        return  existingCpf || existingRne;
    }

    public boolean validateCpfOrRne(String cpf, String rne) {
        return cpf == null && rne == null;
    }

}
