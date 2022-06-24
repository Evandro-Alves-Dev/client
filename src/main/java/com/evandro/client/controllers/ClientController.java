package com.evandro.client.controllers;

import com.evandro.client.dto.ClientResponse;
import com.evandro.client.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    private ClienteService clienteService;
    @GetMapping
    public ResponseEntity<Page<ClientResponse>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        var pageList = clienteService.findAllPagead(pageRequest);
        return ResponseEntity.ok().body(pageList);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        var response = clienteService.findById(id);
        return ResponseEntity.ok().body(response);
    }

}
