package com.Senai.Filmes.repository;

import com.Senai.Filmes.Model.Sessoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ISessaoRepository extends JpaRepository<Sessoes, UUID> {
    @Query("SELECT s FROM Sessao s WHERE s.inicio >= :inicioDia and s.inicio < :fimDia")
    List<Sessoes> findByData(@Param("inicioDia") LocalDateTime inicioDia, @Param("fimDia") LocalDateTime fimDia);

    @Query("SELECT s from Sessao s WHERE s.filme.id = :filmeId ORDER BY s.inicio asc")
    List<Sessoes> findAByFilmeId(@Param("filmeId") UUID filme);


}
