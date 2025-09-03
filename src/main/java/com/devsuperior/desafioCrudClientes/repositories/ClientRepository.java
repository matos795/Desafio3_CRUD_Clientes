package com.devsuperior.desafioCrudClientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.desafioCrudClientes.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
