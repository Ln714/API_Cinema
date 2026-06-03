package com.Senai.Filmes.service;

import com.Senai.Filmes.DTO.Request.SalaRequest;
import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.Model.Assento;
import com.Senai.Filmes.Model.Sala;
import com.Senai.Filmes.repository.ISalaRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SalaService {

    @Autowired
    private ISalaRepository salaRepository;

    public SalaResponse cadastrarSala(SalaRequest request) {

        Sala sala = new Sala();

        sala.setNome(request.nomeSala());
        sala.setTotalAssentos(
                request.fileiras() * request.assentosPorFileira()
        );

        List<Assento> assentos = gerarAssentos(
                sala,
                request.fileiras(),
                request.assentosPorFileira()
        );

        sala.setAssentos(assentos);

        Sala salaSalva = salaRepository.save(sala);

        return toResponse(salaSalva);
    }

    private SalaResponse toResponse(Sala sala) {

        return new SalaResponse(
                sala.getId(),
                sala.getNome(),
                sala.getTotalAssentos()
        );
    }

    private List<Assento> gerarAssentos(
            Sala sala,
            int fileiras,
            int assentosPorFileira) {

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

    public @Nullable SalaResponse bucasPorSalaId(UUID id) {
        return null;
    }

    public List<SalaResponse> listTodos() {
        return null; }

    public @Nullable SalaResponse atualizarSala(UUID id, SalaRequest salaRequest) {
        return null;}

    public void deletar(UUID id) {

    }
}