package com.Senai.Filmes.repository;

import com.Senai.Filmes.Model.Filmes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFilmeRepository extends JpaRepository<Filmes, UUID> {




}
