package br.com.fase5.hackaton.mspatient.controller;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDTO> save(@RequestBody PatientDTO patientDTO) {
        PatientDTO patient = patientService.save(patientDTO);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<PatientDTO>> findAll(Pageable pageable){
        Page<PatientDTO> patientDTOS = patientService.findAll(pageable);
        return ResponseEntity.ok(patientDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable String id){
        PatientDTO patientDTO = patientService.findById(id);
        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping("/cpfOrRne")
    public ResponseEntity<PatientDTO> findByCpfOrRne(@RequestParam(value = "cpf", required = false) String cpf,
                                                     @RequestParam(value = "rne", required = false) String rne){
        PatientDTO patientDTO = patientService.findByCpfOrRne(cpf, rne);
        return ResponseEntity.ok(patientDTO);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable String id, @RequestBody PatientDTO patientDTO){
        patientService.update(id, patientDTO);
        return ResponseEntity.ok(patientDTO);
    }

}
