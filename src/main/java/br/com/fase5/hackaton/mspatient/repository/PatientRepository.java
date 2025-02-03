package br.com.fase5.hackaton.mspatient.repository;

import br.com.fase5.hackaton.mspatient.model.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<PatientModel, String> {
}
