package com.Senai.Filmes.repository;

import com.Senai.Filmes.Model.Assento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAssentoRepository extends JpaRepository<Assento, UUID> {
    List<Assento> findAllBySalaId(UUID salaId);
}
