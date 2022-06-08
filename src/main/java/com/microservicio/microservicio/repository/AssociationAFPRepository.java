package com.microservicio.microservicio.repository;
import com.microservicio.microservicio.model.entity.AssociationAFP;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AssociationAFPRepository extends MongoRepository<AssociationAFP, String> {
    public AssociationAFP findByDni(String dni);
}
