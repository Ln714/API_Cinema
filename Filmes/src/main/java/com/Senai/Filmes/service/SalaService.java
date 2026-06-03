package com.Senai.Filmes.service;

import com.Senai.Filmes.DTO.Request.SalaRequest;
import com.Senai.Filmes.DTO.Response.FilmeResponse;
import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.Model.Assento;
import com.Senai.Filmes.Model.Filmes;
import com.Senai.Filmes.Model.Sala;
import com.Senai.Filmes.repository.ISalaRepository;

import java.util.ArrayList;
import java.util.List;

public class SalaService {

    private ISalaRepository salaRepository;

    public SalaResponse cadastrarSala(SalaRequest request) {
        Sala sala = new Sala();
        sala.setNome(request.nomeSala());
        sala.setTotalAssentos(request.fileiras() * request.assentosPorFileira());

        List<Assento> assentos = gerarAssentos(sala, request.fileiras(), request.assentosPorFileira());
        sala.setAssentos(assentos);

        return toResponse(salaRepository.save(sala));
    }

    private List<Assento> gerarAssentos(Sala sala, int fileiras, int assentosPorFileira) {
        List<Assento> assentos = new ArrayList<>();
        for (int f = 0; f < fileiras; f++) {
            String fileira = String.valueOf((char) ('A' + f));
            for (int n = 1; n <= assentosPorFileira; n++) {
                Assento assento = new Assento();
                assento.setSala(sala);
                assento.setFileira(fileira);
                assento.setNumeroAssento(n);
                assentos.add(assento);
            }
        }
        return assentos;
    }













    //Aux metodo
    private SalaResponse toResponse(Filmes filmes){
        return new SalaResponse(
                filmes.getId(),
                filmes.getTitulo(),
                filmes.getDescricao(),
                filmes.getUrlPoster(),
                filmes.getGenero(),
                filmes.getDuracaoMinuto()
        );


}


//UUID id,
//
//String nome,
//
//Integer totalAssentos