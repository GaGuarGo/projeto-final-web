package com.br.projeto_clientes.cliente.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.projeto_clientes.cliente.entities.Client;
import com.br.projeto_clientes.cliente.services.ClientService;

@RestController
@RequestMapping("clients")
@CrossOrigin

public class ClientController {
    
   
    @Autowired
    private ClientService service;

    @GetMapping()
    public ResponseEntity<List<Client>> getClients() {

        return ResponseEntity.ok(service.getClients());
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {

        return ResponseEntity.ok(service.getClientById(id));
    }

    @PostMapping("")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {

        Client cli = this.service.addClient(client);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cli.getId())
                .toUri();

        return ResponseEntity.created(location).body(cli);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> editClient(@RequestBody Client client, @PathVariable int id) {

        this.service.editClient(client, id);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable int id) {
        this.service.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

}
