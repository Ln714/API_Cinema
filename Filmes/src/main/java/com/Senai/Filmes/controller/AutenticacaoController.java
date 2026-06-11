package com.Senai.Filmes.controller;


import com.Senai.Filmes.DTO.Request.CadastroRequest;
import com.Senai.Filmes.DTO.Request.LoginRequest;
import com.Senai.Filmes.DTO.Response.AuthResponse;
import com.Senai.Filmes.service.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
@Tag(name = "Autenticacao", description = "Endpoint para cadastro e login do usuario")

public class AutenticacaoController {
    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/cadastro")
    @Operation(summary = "Cadastrar usuario", description = "Cadastrar o novo usuario e retornar o JWT Token")
    public ResponseEntity<AuthResponse> cadastrar(@RequestBody CadastroRequest request){
        return new ResponseEntity<>(autenticacaoService.cadastrarUsuario(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = " Autentica o usuario e retorna um token JWT")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(autenticacaoService.login(request), HttpStatus.OK);
    }

}
