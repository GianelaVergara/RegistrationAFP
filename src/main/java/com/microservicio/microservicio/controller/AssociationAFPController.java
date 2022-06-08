package com.microservicio.microservicio.controller;

import com.microservicio.microservicio.model.entity.AssociationAFP;
import com.microservicio.microservicio.repository.AssociationAFPRepository;
import com.microservicio.microservicio.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

public class AssociationAFPController {

    @Autowired
    private ClientRepository iclient;
    @Autowired
    private AssociationAFPRepository iAss;

    //Instancia de Slf4j para esta clase
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<AssociationAFP> getAllAfpData() {
        LOGGER.info("Hizo la petición de listado");
        return iAss.findAll();
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public String createAfpData(@RequestBody AssociationAFP associationAFP) {
        var existente=iAss.findByDni(associationAFP.getDni());
        if (existente==null){

            var existeCliente=iclient.findByDni(associationAFP.getDni());
            if (existeCliente!=null){
                iAss.save(associationAFP);
                LOGGER.info("Hizo la petición de add");
                return "Data Agregada de forma exitosa";
            }
            else {
                return "Cliente no existe";
            }
        }
        else{
            LOGGER.info("Data del cliente ya existe en el Sistema, no se completa la peticion");
            return "Data del cliente ya existe en el Sistema";
        }
    }

    @GetMapping("/getById/{idAssoAFP}")
    public ResponseEntity<AssociationAFP> getDataClient(@PathVariable(value = "idAssoAFP") String idAssoAFP) {
        LOGGER.info("Hizo la petición de obtener por Id");
        Optional<AssociationAFP> associationAFP = iAss.findById(idAssoAFP);
        return associationAFP.map(afpClient -> new ResponseEntity<>(afpClient, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(associationAFP.get(), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{idAssoAFP}")
    public String deleteData(@PathVariable(value = "idAssoAFP") String idAssoAFP) {
        LOGGER.info("Hizo la petición eliminar por Id");
        iAss.deleteById(idAssoAFP);
        return "Se eliminó correctamente la Data del Cliente";
    }

    @PutMapping("/update/{idAssoAFP}")
    public AssociationAFP updateData(@RequestBody AssociationAFP associationAFP, @PathVariable(value = "idAssoAFP") String idAssoAFP) {
        LOGGER.info("Hizo la petición actualizar por Id");
        associationAFP.setIdAssoAFP(idAssoAFP);
        iAss.save(associationAFP);
        return associationAFP;
    }
    public AssociationAFP getAssoAFPClientByDni(String dni) {
        LOGGER.info("Hizo la petición de obtener por dni");

        return iAss.findByDni(dni);
    }

}

