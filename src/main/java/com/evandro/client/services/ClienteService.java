package com.evandro.client.services;

import com.evandro.client.dto.ClientRequest;
import com.evandro.client.dto.ClientResponse;
import com.evandro.client.entities.Client;
import com.evandro.client.exceptions.DataBaseException;
import com.evandro.client.exceptions.ResourceNotFoundException;
import com.evandro.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClientRepository clientRepository;
    @Transactional(readOnly = true)
    public Page<ClientResponse> findAllPagead(PageRequest pageRequest) {
        var list = clientRepository.findAll(pageRequest);
        return list.map(client -> new ClientResponse(client));
    }
    @Transactional(readOnly = true)
    public ClientResponse findById(Long id) {
        var response = clientRepository.findById(id);
        return new ClientResponse(response.orElseThrow(() -> new ResourceNotFoundException("Entity not found " + id)));
    }
    @Transactional
    public ClientResponse insert(ClientRequest clientRequest) {
        Client client = Client.builder()
                .name(clientRequest.getName())
                .cpf(clientRequest.getCpf())
                .income(clientRequest.getIncome())
                .birthDate(clientRequest.getBirthDate())
                .children(clientRequest.getChildren())
                .build();
        var response = clientRepository.save(client);
        return new ClientResponse(response);
    }
    @Transactional
    public ClientResponse update(Long id, ClientRequest clientRequest) {
        try {
            var entity = clientRepository.getById(id);
            entity = Client.builder()
                .name(clientRequest.getName())
                .cpf(clientRequest.getCpf())
                .income(clientRequest.getIncome())
                .birthDate(clientRequest.getBirthDate())
                .children(clientRequest.getChildren())
                .build();
            entity = clientRepository.save(entity);
            return new ClientResponse(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete (Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
    }
}
