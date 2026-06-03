package com.Senai.Filmes.controller;

import com.Senai.Filmes.DTO.Request.FilmeRequest;
import com.Senai.Filmes.DTO.Request.SalaRequest;
import com.Senai.Filmes.DTO.Response.FilmeResponse;
import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.Model.Sala;
import com.Senai.Filmes.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("*/api/Salas")
public class SalaController {

    @Autowired
    private SalaService salaService;


    //listar
    @GetMapping
    @Operation(summary = "Listar Todas as salas", description = "Rota para listar todas salas disponiveis cadastradas")
    public ResponseEntity<List<SalaResponse>> listarTodos() {
        List<SalaResponse> sala = salaService.listTodos();

        if (sala.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sala, HttpStatus.OK);
    }


    //Buscar por id
    @GetMapping("/{id}")
    @Operation(summary = "Buscar e listar salas por id", description = "Listar salas realizando a buscas por id")
    public ResponseEntity<SalaResponse> bucasPorSalaId(@PathVariable UUID id) {
        return new ResponseEntity<>(salaService.bucasPorSalaId(id), HttpStatus.OK);
    }


    //Cadastro de salas
    @PostMapping
    @Operation(summary = "Cadastrar salas", description = "Cadastramento de Salas no banco de dados para o cliente visualizar a lista geral")
    public ResponseEntity<SalaResponse> cadastrarSala(@RequestBody SalaRequest salarequest) {
        return new ResponseEntity<>(salarequest.nomeSala(salarequest), HttpStatus.CREATED);
    }

    //atualizar
    @PutMapping
    @Operation(summary = "Atualizar Sala", description = "Atualização das sala no banco de dados")
    public ResponseEntity<SalaResponse> Atualizar(@PathVariable UUID id, @RequestBody SalaRequest salarequest) {
        return new ResponseEntity<>(salaService.atualizarSala(id, salarequest), HttpStatus.OK);
    }

    //deletar
    @DeleteMapping
    @Operation(summary = "Deletar Sala", description = "Deletar Sala do banco de dados")
    public ResponseEntity<SalaResponse> deletar(@PathVariable UUID id) {

        SalaResponse.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
