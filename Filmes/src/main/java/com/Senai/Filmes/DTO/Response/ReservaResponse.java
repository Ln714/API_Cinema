package com.Senai.Filmes.DTO.Response;

import com.Senai.Filmes.Model.Enums.StatusReserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReservaResponse(
        UUID id, SessaoResponse sessao, List<AssentosResponse> assentos, StatusReserva status, LocalDateTime criadoEm

        ) {
}
