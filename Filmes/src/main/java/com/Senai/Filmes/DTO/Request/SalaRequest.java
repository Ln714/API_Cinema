package com.Senai.Filmes.DTO.Request;

import java.util.UUID;

public record SalaRequest(

  String nomeSala, Integer totalAssentos, Integer fileiras, Integer assentosPorFileira

) {
}
