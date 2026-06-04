package com.Senai.Filmes.Model;

import com.Senai.Filmes.Model.Enums.Cargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Usuario")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome obrigatorio!")
    private String nome;

    @NotBlank(message = "E-mail é obrigatorio!")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Senha Obrigatoria!")
    private String senha;

    @NotNull(message = "Cargo obrigatorio")
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @CreationTimestamp
    private LocalDateTime criadoEm;




}
