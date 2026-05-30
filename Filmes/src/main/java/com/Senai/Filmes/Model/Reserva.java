package com.Senai.Filmes.Model;

import com.Senai.Filmes.Model.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Reservas")


public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @NotNull(message = "Status deve ser preenchido.")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessoes sessao;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaAssento> assentos = new ArrayList<ReservaAssento>();
}
