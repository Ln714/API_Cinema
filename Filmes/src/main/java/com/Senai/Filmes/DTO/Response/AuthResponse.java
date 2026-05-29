package com.Senai.Filmes.DTO.Response;

import java.util.UUID;

public record AuthResponse(

        String token, String nome, String cargo

) {
}
