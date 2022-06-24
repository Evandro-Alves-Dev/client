package com.evandro.client.services;

import com.evandro.client.dto.ClientResponse;
import com.evandro.client.exceptions.ResourceNotFoundException;
import com.evandro.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return new ClientResponse(response.orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
    }
}
