package com.microservicio.microservicio.controller;

import com.microservicio.microservicio.model.entity.Client;
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

public class ClientController {
    @Autowired
    private ClientRepository iclient;

    //Instancia de Slf4j para esta clase
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
        LOGGER.info("Hizo la petición de listado");
        return iclient.findAll();
    }



    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public String createClient(@RequestBody Client client) {
        var existente= getClient(client.getDni());
        if (existente==null){
            iclient.save(client);
            LOGGER.info("Hizo la petición de add");
            return "Cliente Agregado de forma exitosa";
        }
        else{
            LOGGER.info("Cliente ya existe en el Sistema, no se completa la peticion");
            return "Cliente ya existe en el Sistema";
        }
    }


    @GetMapping("/getById/{IdClient}")
    public ResponseEntity<Client> getClient(@PathVariable(value = "IdClient") String IdClient) {
        LOGGER.info("Hizo la petición de obtener por Id");
        Optional<Client> client = iclient.findById(IdClient);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(client.get(), HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/delete/{IdClient}")
    public String deleteClient(@PathVariable(value = "IdClient") String IdClient) {
        LOGGER.info("Hizo la petición eliminar por Id");
        iclient.deleteById(IdClient);
        return "Se eliminó correctamente el cliente";
    }

    @PutMapping("/update/{IdClient}")
    public Client updateClient(@RequestBody Client client, @PathVariable(value = "IdClient") String IdClient) {
        LOGGER.info("Hizo la petición actualizar por Id");
        client.setIdClient(IdClient);
        iclient.save(client);
        return client;
    }
    public Client getClientByDni(String dni) {
        LOGGER.info("Hizo la petición de obtener por dni");

        return iclient.findByDni(dni);
    }

}

