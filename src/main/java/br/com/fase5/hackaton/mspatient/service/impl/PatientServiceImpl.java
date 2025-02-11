package br.com.fase5.hackaton.mspatient.service.impl;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.exception.NotFoundException;
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
import java.util.Collections;

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

        validateInput(patientDTO, "save");

        if (patientExisting(patientDTO.getCpf(), patientDTO.getRne())) {
            throw new NotFoundException("O paciente já existe com este CPF ou RNE.");
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
                .orElseThrow(() -> new NotFoundException("O paciente não foi localizado com o ID informado.")));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findByCpf(String cpf) {
        return patientRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("O paciente não foi localizado com o CPF informado."));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findByRne(String rne) {
        return patientRepository.findByRne(rne)
                .orElseThrow(() -> new NotFoundException("O paciente não foi localizado com o RNE informado."));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        patientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PatientDTO update(String id, PatientDTO patientDTO) {
        validateInput(patientDTO, "update");

        PatientModel patientModel = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O paciente não foi localizado com o ID informado."));

        patientModel.setName(patientDTO.getName());
        patientModel.setBirthDate(patientDTO.getBirthDate());
        patientModel.setEmail(patientDTO.getEmail());
        patientModel.setPhone(patientDTO.getPhone());
        patientModel.setAddresses(patientDTO.getAddresses()!= null
                ? patientDTO.getAddresses().stream().map(PatientMapper::toAddressModel).toList()
                : Collections.emptyList());

        return PatientMapper.toPatientDTO(patientRepository.save(patientModel));
    }

    public boolean patientExisting(String cpf, String rne) {
        boolean existingCpf = false;
        boolean existingRne = false;

        if (cpf != null && !cpf.isEmpty()){
            existingCpf = patientRepository.existsByCpf(cpf);
        }

        if (rne != null && !rne.isEmpty()){
            existingRne = patientRepository.existsByRne(rne);
        }

        return  existingCpf || existingRne;
    }

    public void validateInput(PatientDTO patientDTO, String method) {
            if (patientDTO.getName() == null || patientDTO.getName().isEmpty()) {
                throw new NotFoundException("O Nome deve ser informado.");
            } else if ((patientDTO.getCpf() == null || patientDTO.getCpf().isEmpty()) &&
                    (patientDTO.getRne() == null || patientDTO.getRne().isEmpty()) && method.equals("save")) {
                throw new NotFoundException("O CPF ou RNE deve ser informado.");
            } else if (patientDTO.getAddresses().isEmpty()) {
                throw new NotFoundException("Ao menos um endereço deve ser informado.");
            }
    }

}
