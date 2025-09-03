package com.devsuperior.desafioCrudClientes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafioCrudClientes.dto.ClientDTO;
import com.devsuperior.desafioCrudClientes.entities.Client;
import com.devsuperior.desafioCrudClientes.repositories.ClientRepository;
import com.devsuperior.desafioCrudClientes.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(client -> new ClientDTO(client));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client client = new Client();
        setToDto(client, dto);
        clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        setToDto(client, dto);
        clientRepository.save(client);
        return new ClientDTO(client);
    }

    private void setToDto(Client client, ClientDTO dto) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
