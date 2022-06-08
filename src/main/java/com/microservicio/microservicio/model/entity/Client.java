package com.microservicio.microservicio.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("client")
@Data
@NoArgsConstructor
public class Client {
    @Id
    private String IdClient;
    private String dni;
    private String name;
    private String lastName;
    private String phone;
    private String mail;
    private String afp;
}
