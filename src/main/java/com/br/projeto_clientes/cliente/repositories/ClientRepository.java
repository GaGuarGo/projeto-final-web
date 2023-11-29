package com.br.projeto_clientes.cliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.projeto_clientes.cliente.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
