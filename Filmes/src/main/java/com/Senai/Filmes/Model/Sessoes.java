package com.Senai.Filmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Sessoes")

public class Sessoes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "filmes_id")
    @NotNull
    private Filmes filmes;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    @NotNull
    private Sala sala;

    @NotNull(message = "Horario inicial da sessao deve ser obrigatorio!")
    private LocalDateTime Inicio;

    @NotNull(message = "Horario final da sessao deve ser obrigatorio!")
    private LocalDateTime fim;

    @NotNull(message = "Valor obrigatorio!")
    private BigDecimal preco;

}
