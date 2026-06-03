package com.Senai.Filmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Salas")

public class Sala extends Filmes {


@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

@NotBlank(message = "Nome da sala obrigatorio!")
private String nome;

@Min(value = 1L, message = "A sala deve ter pelo menos 1 assento")
private  Integer totalAssentos;

@OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Assento> assentos = new ArrayList<>();


}
