package com.Senai.Filmes.service;

import com.Senai.Filmes.DTO.Request.FilmeRequest;
import com.Senai.Filmes.DTO.Response.FilmeResponse;
import com.Senai.Filmes.Model.Filmes;
import com.Senai.Filmes.repository.IFilmeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FilmeService {

    @Autowired
    private IFilmeRepository filmeRepository;


    //listagem
    public List<FilmeResponse> listTodos(){
        return filmeRepository.findAll().stream().map(this::toResponse).toList();
    }

        //bucasr id
    public FilmeResponse bucasPorFilmeID(UUID id) {
        Filmes filmes = filmeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filme nao encontrado"));
        return toResponse(filmes);
    }

    public FilmeResponse cadastrarFilme(FilmeRequest request){
        Filmes filmes = new Filmes();
        filmes.setTitulo(request.titulo());
        filmes.setDescricao(request.Descricao());
        filmes.setUrlPoster(request.urlPoster());
        filmes.setGenero(request.genero());
        filmes.setDuracaoMinuto(request.duracaoMinutos());

        return toResponse(filmeRepository.save(filmes));

    }

 public  FilmeResponse atualizarFilme(UUID uuid, FilmeRequest filmeRequest){
        Filmes filme = filmeRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Nenhum Filme encontrado"));


        filme.setTitulo(filmeRequest.titulo());
        filme.setDescricao(filmeRequest.Descricao());
        filme.setGenero(filmeRequest.genero());
        filme.setUrlPoster(filmeRequest.urlPoster());
        filme.setDuracaoMinuto(filmeRequest.duracaoMinutos());
        return toResponse(filmeRepository.save(filme));

 }

 public void deletar(UUID id) {
     Filmes filme = filmeRepository.findById(id)
             .orElseThrow(() -> new EntityNotFoundException("Nenhum Filme encontrado"));
     filmeRepository.delete(filme);
 }


                                         //Aux metodo
    private FilmeResponse toResponse(Filmes filmes){
        return new FilmeResponse(
                filmes.getId(),
                filmes.getTitulo(),
                filmes.getDescricao(),
                filmes.getUrlPoster(),
                filmes.getGenero(),
                filmes.getDuracaoMinuto()
        );
    }
}
