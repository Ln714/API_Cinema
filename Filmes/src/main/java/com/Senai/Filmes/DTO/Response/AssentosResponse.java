package com.Senai.Filmes.DTO.Response;

import java.util.UUID;

public record AssentosResponse(

        UUID id, String fileira, Integer numero, boolean disponivel
) {
}
