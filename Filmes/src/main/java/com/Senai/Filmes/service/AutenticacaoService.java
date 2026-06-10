package com.Senai.Filmes.service;

import com.Senai.Filmes.DTO.Request.CadastroRequest;
import com.Senai.Filmes.DTO.Request.LoginRequest;
import com.Senai.Filmes.DTO.Response.AuthResponse;
import com.Senai.Filmes.Model.Enums.Cargo;
import com.Senai.Filmes.Model.Usuario;
import com.Senai.Filmes.Security.JwtUtil;
import com.Senai.Filmes.Security.UserDetailsServiceImpl;
import com.Senai.Filmes.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceimpl;

    //cadastrar usuario
    public AuthResponse cadastrarUsuario(CadastroRequest request){
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email ja esta cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setCargo(Cargo.USUARIO);

        usuarioRepository.save(usuario);

        UserDetails userDetails = userDetailsServiceimpl.loadUserByUsername((request.email()));
        String token = jwtUtil.gerarToken(userDetails);

        return new AuthResponse(token, usuario.getNome(), usuario.getCargo().name());

    }

    //login
    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha()));

        Usuario usuario = usuarioRepository.findByEmail((loginRequest.email().describeConstable().orElseThrow()));

        UserDetails userDetails = userDetailsServiceimpl.loadUserByUsername(loginRequest.email());
        String token = jwtUtil.gerarToken(userDetails);

        return new AuthResponse(token, usuario.getNome(), usuario.getCargo().name());
    }

}
