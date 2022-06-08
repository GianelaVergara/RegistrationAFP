package com.microservicio.microservicio.repository;

import com.microservicio.microservicio.model.entity.RequestAFP;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestAFPRepository extends MongoRepository<RequestAFP, String> {
    public RequestAFP findByDni(String dni);
}
