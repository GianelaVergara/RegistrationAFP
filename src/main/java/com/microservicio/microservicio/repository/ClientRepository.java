package com.microservicio.microservicio.repository;

import com.microservicio.microservicio.model.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
    public Client findByDni(String dni);
}
