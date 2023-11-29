package com.br.projeto_clientes.cliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.projeto_clientes.cliente.entities.Client;
import com.br.projeto_clientes.cliente.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> getClients() {
        return repository.findAll();
    }

    public Client getClientById(int id) {
        // lida com a possibilidade de não ter um cliente relacionado ao id
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente não Existente"));
    }

    public Client addClient(Client client) {

        return repository.save(client);

    }

    public void editClient(Client client, int id) {
        try {
            Client edClient = repository.getReferenceById(id);
            edClient.setName(client.getName());
            edClient.setEmail(client.getEmail());
            edClient.setPhone(client.getPhone());
            edClient.setAddress(client.getAddress());
            edClient.setCity(client.getCity());
            edClient.setCep(client.getCep());
            edClient.setState(client.getState());

            this.repository.save(edClient);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Cliente não Cadastrado!");
        }
    }

    public void deleteClientById(int id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Cliente não Existente");
        }
    }

}
