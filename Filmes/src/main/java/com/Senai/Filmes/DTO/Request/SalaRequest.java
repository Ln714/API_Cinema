package com.Senai.Filmes.DTO.Request;

import com.Senai.Filmes.DTO.Response.SalaResponse;

import java.util.UUID;

public record SalaRequest(

  String nomeSala, Integer totalAssentos, Integer fileiras, Integer assentosPorFileira

) {
    public SalaResponse nomeSala(SalaRequest salarequest) {
        return null;
    }
}
