package com.Senai.Filmes.service;

import com.Senai.Filmes.DTO.Request.SalaRequest;
import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.Model.Assento;
import com.Senai.Filmes.Model.Sala;
import com.Senai.Filmes.repository.ISalaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
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

    //cadastrar
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


    //BuscaId
    public @Nullable SalaResponse bucasPorSalaId(UUID id) {
            Sala sala = salaRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Sala não encontrada"));

            return toResponse(sala);
    }

    //ListagemSala
    public List<SalaResponse> listTodos() {

        return salaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

//    UpgradeSala
    public @Nullable SalaResponse atualizarSala(
            UUID id,
            SalaRequest salaRequest) {

        Sala sala = salaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sala não encontrada"));

        sala.setNome(salaRequest.nomeSala());

        sala.setTotalAssentos(
                salaRequest.fileiras()
                        * salaRequest.assentosPorFileira()
        );

        Sala salaAtualizada = salaRepository.save(sala);

        return toResponse(salaAtualizada);
    }

    //DeleteSala
    public void deletar(UUID id) {
        if (!salaRepository.existsById(id)) {
            throw new RuntimeException("Sala não encontrada");
        }

        salaRepository.deleteById(id);

    }


    //aux metodo
    private SalaResponse toResponse(Sala sala) {

        return new SalaResponse(
                sala.getId(),
                sala.getNome(),
                sala.getTotalAssentos()
        );
    }




}