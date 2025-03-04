package br.com.fase5.hackaton.mspatient.repository;

import br.com.fase5.hackaton.mspatient.dto.PatientDTO;
import br.com.fase5.hackaton.mspatient.model.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<PatientModel, String> {
    Optional<PatientDTO> findByCpf(String cpf);
    Optional<PatientDTO> findByRne(String rne);
    boolean existsByCpf(String cpf);
    boolean existsByRne(String rne);
}
