package com.Senai.Filmes.Model;

import com.Senai.Filmes.Model.Enums.GeneroFilme;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table (name = "filmes")
public class Filmes {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Titulo obrigatorio!")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String urlPoster;

    @NotNull(message = "Genero obrigatorio!")
    @Enumerated(EnumType.STRING)
    private GeneroFilme genero;

    @NotNull(message = "Campo minutos obrigatorio!")
    @Min(value = 1, message = "A duracao dever ser maior que 0")
    private Integer duracaoMinuto;

}
