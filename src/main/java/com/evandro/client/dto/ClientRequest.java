package com.evandro.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest implements Serializable {

    private String name;
    private String cpf;
    private Double income;
    private Instant birthDate;
    private Integer children;
}
