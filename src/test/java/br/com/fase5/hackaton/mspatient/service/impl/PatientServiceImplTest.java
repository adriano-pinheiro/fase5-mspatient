package br.com.fase5.hackaton.mspatient.service.impl;

import br.com.fase5.hackaton.mspatient.dto.AddressDTO;
import br.com.fase5.hackaton.mspatient.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.model.PatientModel;
import br.com.fase5.hackaton.mspatient.repository.PatientRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Profile("test")
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    private PatientDTO patient;
    private PatientModel patientModel;

    @BeforeEach
    void setUp() {
        patient = new PatientDTO("1", "Adrian Test", "12345678900", "12345XYZ", new ArrayList<>(List.of(new AddressDTO())));

        patientModel = new PatientModel();
        patientModel.setId("1");
        patientModel.setCpf("12345678900");
        patientModel.setRne("12345XYZ");
        patientModel.setName("Adrian Test");
    }

    @Test
    void save_WhenPatientDoesNotExist_ShouldSaveSuccessfully() {
        when(patientRepository.existsByCpf(patient.getCpf())).thenReturn(false);
        when(patientRepository.existsByRne(patient.getRne())).thenReturn(false);
        when(patientRepository.save(any(PatientModel.class))).thenReturn(patientModel);

        PatientDTO savedPatient = patientService.save(patient);

        assertNotNull(savedPatient);
        assertEquals(patient.getCpf(), savedPatient.getCpf());
        verify(patientRepository, times(1)).save(any(PatientModel.class));
    }

    @Test
    void save_WhenPatientExists_ShouldThrowException() {
        when(patientRepository.existsByCpf(patient.getCpf())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.save(patient));
        assertEquals("O paciente já existe com este CPF ou RNE.", exception.getMessage());
    }

    @Test
    void findById_WhenPatientExists_ShouldReturnPatient() {
        when(patientRepository.findById("1")).thenReturn(Optional.of(patientModel));

        PatientDTO foundPatient = patientService.findById("1");

        assertNotNull(foundPatient);
        assertEquals("1", foundPatient.getId());
    }

    @Test
    void findById_WhenPatientDoesNotExist_ShouldThrowException() {
        when(patientRepository.findById("1")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.findById("1"));
        assertEquals("O paciente não foi localizado com o ID informado.", exception.getMessage());
    }

    @Test
    void findAll_ShouldReturnPagedPatients() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PatientModel> patientPage = new PageImpl<>(Collections.singletonList(patientModel));
        when(patientRepository.findAll(any(Pageable.class))).thenReturn(patientPage);

        Page<PatientDTO> result = patientService.findAll(pageable);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(patientRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void deleteById_ShouldDeleteSuccessfully() {
        doNothing().when(patientRepository).deleteById("1");

        assertDoesNotThrow(() -> patientService.deleteById("1"));
        verify(patientRepository, times(1)).deleteById("1");
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        PatientDTO patientDTO = new PatientDTO( null, null, null, null, new ArrayList<>(List.of(new AddressDTO())));

        assertThatThrownBy(() -> patientService.validateInput(patientDTO,"save"))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("O Nome deve ser informado.");
    }

    @Test
    void shouldThrowExceptionWhenCpfAndRneAreNullOrEmpty() {
        PatientDTO patientDTO = new PatientDTO( null, "Tonho da Lua", null, "", new ArrayList<>(List.of(new AddressDTO())));

        assertThatThrownBy(() -> patientService.validateInput(patientDTO,"save"))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("O CPF ou RNE deve ser informado.");
    }

    @Test
    void shouldThrowExceptionWhenAddressListIsEmpty() {
        PatientDTO patientDTO = new PatientDTO(null, "Van Basten", "12345678900", "", new ArrayList<>());

        assertThatThrownBy(() -> patientService.validateInput(patientDTO,"save"))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Ao menos um endereço deve ser informado.");
    }

    @Test
    void shouldNotThrowExceptionForValidInput() {
        PatientDTO patientDTO = new PatientDTO( null, "Fernando Alonso", "12345678900", "", new ArrayList<>(List.of(new AddressDTO())));

        patientService.validateInput(patientDTO,"save");
    }
}