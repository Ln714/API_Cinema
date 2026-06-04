package com.Senai.Filmes.controller;

import com.Senai.Filmes.DTO.Request.SalaRequest;
import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Salas", description = "Endpoint para gerenciamento de salas do cinema")
@RestController
@CrossOrigin("*")
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    @Operation(
            summary = "Listar Todas as salas",
            description = "Rota para listar todas salas disponíveis cadastradas"
    )
    public ResponseEntity<List<SalaResponse>> listarTodos() {

        List<SalaResponse> salas = salaService.listTodos();

        if (salas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar sala por id",
            description = "Busca uma sala pelo seu identificador"
    )
    public ResponseEntity<SalaResponse> buscarPorSalaId(@PathVariable UUID id) {

        return ResponseEntity.ok(
                salaService.bucasPorSalaId(id)
        );
    }

    @PostMapping
    @Operation(
            summary = "Criar sala",
            description = "Cadastra uma nova sala com geraçao de assentos"
    )
    public ResponseEntity<SalaResponse> cadastrarSala(
            @RequestBody SalaRequest salaRequest) {

        return new ResponseEntity<>(
                salaService.cadastrarSala(salaRequest),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar sala",
            description = "Atualiza os dados de uma sala"
    )
    public ResponseEntity<SalaResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody SalaRequest salaRequest) {

        return ResponseEntity.ok(
                salaService.atualizarSala(id, salaRequest)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar sala",
            description = "Remove uma sala do banco de dados"
    )
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {

        salaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}