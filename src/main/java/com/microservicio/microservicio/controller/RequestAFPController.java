package com.microservicio.microservicio.controller;

import com.microservicio.microservicio.model.entity.RequestAFP;
import com.microservicio.microservicio.repository.AssociationAFPRepository;
import com.microservicio.microservicio.repository.ClientRepository;
import com.microservicio.microservicio.repository.RequestAFPRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/requestAFP")

public class RequestAFPController {
    @Autowired
    private RequestAFPRepository irequestAFP;

    @Autowired
    private ClientRepository iclient;
    @Autowired
    private AssociationAFPRepository iAss;

    //Instancia de Slf4j para esta clase
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/listado")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestAFP> getAllClient(){
        LOGGER.info("Hizo la petición de listado");
        return irequestAFP.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public String createRecord(@RequestBody RequestAFP requestsAFP) {

        var existente = irequestAFP.findByDni(requestsAFP.getDni());

        if (existente==null){
            var afp=(iclient.findByDni(requestsAFP.getDni())).getAfp();
            if (afp!=null){
                requestsAFP.setAFP((iclient.findByDni(requestsAFP.getDni())).getAfp());

                var monto=iAss.findByDni(requestsAFP.getDni()).getMoneyAvailable();
                if (monto>0){
                    if (monto<requestsAFP.getMoney()){
                        return "No se puede registrar la solicitud. Monto mayor que el permitido";
                    } else if (monto/2>requestsAFP.getMoney()) {
                        return "Monto mínimo no cubierto por favor revise el monto mínimo a retirar \nMonto minimo:"+monto/2;
                    }
                }
            }else{
                return "cliente no esta afiliado a ninguna AFP";
            }
            irequestAFP.save(requestsAFP);
            LOGGER.info("Hizo la petición de add");
            return "Registro Agregado de forma exitosa";
        }
        else{
            LOGGER.info("Registro ya existe en el Sistema, no se completa la peticion");
            return "Registro ya existe en el Sistema";
        }
    }

    @GetMapping("/getById/{idRetreatAFP}")
    public ResponseEntity<RequestAFP> getRecord(@PathVariable(value = "idRetreatAFP") String idRetreatAFP) {
        LOGGER.info("Hizo la petición de obtener por Id");
        Optional<RequestAFP> requestAFP = irequestAFP.findById(idRetreatAFP);
        return requestAFP.map(afpClient -> new ResponseEntity<>(afpClient, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(requestAFP.get(), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{idRetreatAFP}")
    public String deleteRecord(@PathVariable(value = "idAssoAFP") String idRetreatAFP) {
        LOGGER.info("Hizo la petición eliminar por Id");
        irequestAFP.deleteById(idRetreatAFP);
        return "Se eliminó correctamente la Data del Cliente";
    }

    @PutMapping("/update/{idRetreatAFP}")
    public RequestAFP updateRecord(@RequestBody RequestAFP requestAFP, @PathVariable(value = "idRetreatAFP") String idRetreatAFP) {
        LOGGER.info("Hizo la petición actualizar por Id");
        requestAFP.setIdRetreatAFP(idRetreatAFP);
        irequestAFP.save(requestAFP);
        return requestAFP;
    }
    public RequestAFP getRecordByDni(String dni) {
        LOGGER.info("Hizo la petición de obtener por dni");

        return irequestAFP.findByDni(dni);
    }

}

