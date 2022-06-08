package com.microservicio.microservicio.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("associationAFP")
@Data
@NoArgsConstructor

public class AssociationAFP {
    @Id
    private String idAssoAFP;
    private String dni;
    private Double moneyAvailable;
    private Date DateRetreat;
    private String numberCTA;

}
