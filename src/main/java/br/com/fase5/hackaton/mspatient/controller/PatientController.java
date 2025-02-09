package br.com.fase5.hackaton.mspatient.controller;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
@Tag(name = "Pacientes", description = "CRUDL de pacientes")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @Operation(summary = "Salvar um paciente",
            description = "Este endpoint salva um paciente no sistema.")
    @PostMapping
    public ResponseEntity<PatientDTO> save(@Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO patient = patientService.save(patientDTO);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar todos os pacientes",
            description = "Este endpoint retorna todos os pacientes registrados no sistema.")
    @GetMapping
    public ResponseEntity<Page<PatientDTO>> findAll(@ParameterObject @PageableDefault(page =0, size = 10) Pageable pageable){
        Page<PatientDTO> patientDTOS = patientService.findAll(pageable);
        return ResponseEntity.ok(patientDTOS);
    }

    @Operation(summary = "Buscar um paciente por ID",
            description = "Este endpoint retorna um paciente de acordo com o ID informado na URL.")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable String id){
        PatientDTO patientDTO = patientService.findById(id);
        return ResponseEntity.ok(patientDTO);
    }

    @Operation(summary = "Buscar um paciente por CPF",
            description = "Este endpoint retorna um paciente de acordo com o CPF informado por parâmetro.")
    @GetMapping("/cpf")
    public ResponseEntity<PatientDTO> findByCpf(@RequestParam(value = "cpf") String cpf){
        PatientDTO patientDTO = patientService.findByCpf(cpf);
        return ResponseEntity.ok(patientDTO);
    }

    @Operation(summary = "Buscar um paciente por RNE",
            description = "Este endpoint retorna um paciente de acordo com o RNE informado por parâmetro.")
    @GetMapping("/rne")
    public ResponseEntity<PatientDTO> findByRne(@RequestParam(value = "rne") String rne){
        PatientDTO patientDTO = patientService.findByRne(rne);
        return ResponseEntity.ok(patientDTO);
    }

    @Operation(summary = "Deletar um paciente por ID",
            description = "Este endpoint deleta um paciente de acordo com o ID informado na URL.")
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar um paciente por ID",
            description = "Este endpoint atualiza um paciente de acordo com o ID informado na URL.")
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable String id, @RequestBody PatientDTO patientDTO){
        PatientDTO patient = patientService.update(id, patientDTO);
        return ResponseEntity.ok(patient);
    }

}
