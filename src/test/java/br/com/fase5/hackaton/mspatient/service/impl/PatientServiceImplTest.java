package br.com.fase5.hackaton.mspatient.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.model.PatientModel;
import br.com.fase5.hackaton.mspatient.repository.PatientRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    private PatientDTO patientDTO;
    private PatientModel patientModel;

    @BeforeEach
    void setUp() {
        patientDTO = new PatientDTO();
        patientDTO.setId("1");
        patientDTO.setCpf("12345678900");
        patientDTO.setRne("12345XYZ");
        patientDTO.setName("Adrian Test");

        patientModel = new PatientModel();
        patientModel.setId("1");
        patientModel.setCpf("12345678900");
        patientModel.setRne("12345XYZ");
        patientModel.setName("Adrian Test");
    }

    @Test
    void testSave_WhenPatientDoesNotExist_ShouldSaveSuccessfully() {
        when(patientRepository.existsByCpf(patientDTO.getCpf())).thenReturn(false);
        when(patientRepository.existsByRne(patientDTO.getRne())).thenReturn(false);
        when(patientRepository.save(any(PatientModel.class))).thenReturn(patientModel);

        PatientDTO savedPatient = patientService.save(patientDTO);

        assertNotNull(savedPatient);
        assertEquals(patientDTO.getCpf(), savedPatient.getCpf());
        verify(patientRepository, times(1)).save(any(PatientModel.class));
    }

    @Test
    void testSave_WhenPatientExists_ShouldThrowException() {
        when(patientRepository.existsByCpf(patientDTO.getCpf())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.save(patientDTO));
        assertEquals("O paciente já existe com este CPF ou RNE.", exception.getMessage());
    }

    @Test
    void testFindById_WhenPatientExists_ShouldReturnPatient() {
        when(patientRepository.findById("1")).thenReturn(Optional.of(patientModel));

        PatientDTO foundPatient = patientService.findById("1");

        assertNotNull(foundPatient);
        assertEquals("1", foundPatient.getId());
    }

    @Test
    void testFindById_WhenPatientDoesNotExist_ShouldThrowException() {
        when(patientRepository.findById("1")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.findById("1"));
        assertEquals("O paciente não foi localizado com o ID informado.", exception.getMessage());
    }

    @Test
    void testFindAll_ShouldReturnPagedPatients() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PatientModel> patientPage = new PageImpl<>(Collections.singletonList(patientModel));
        when(patientRepository.findAll(any(Pageable.class))).thenReturn(patientPage);

        Page<PatientDTO> result = patientService.findAll(pageable);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(patientRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testDeleteById_ShouldDeleteSuccessfully() {
        doNothing().when(patientRepository).deleteById("1");

        assertDoesNotThrow(() -> patientService.deleteById("1"));
        verify(patientRepository, times(1)).deleteById("1");
    }

    @Test
    void validateCpfOrRne() {
        assertTrue(patientService.validateCpfOrRne(null, null));
        assertFalse(patientService.validateCpfOrRne("12345678900", null));
        assertFalse(patientService.validateCpfOrRne(null, "RNE123456"));
        assertFalse(patientService.validateCpfOrRne("12345678900", "RNE123456"));
    }
}