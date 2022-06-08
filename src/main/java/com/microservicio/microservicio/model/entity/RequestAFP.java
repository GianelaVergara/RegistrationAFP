package com.microservicio.microservicio.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("requestAFP")
@Data
@NoArgsConstructor
public class RequestAFP {
    @Id
    private String idRetreatAFP;
    private String dni;
    private double money;
    private String AFP;

}
