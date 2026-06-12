package com.Senai.Filmes.Config;

import com.Senai.Filmes.Model.Enums.Cargo;
import com.Senai.Filmes.Model.Usuario;
import com.Senai.Filmes.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.email:admin@cinema.com}")
    private String adminEmail;

    @Value("${admin.senha:Admin@123}")
    private String adminSenha;

    @Override
    public void run(String... args) throws Exception {

        if(usuarioRepository.existsByEmail(adminEmail)) {
            System.out.println("Admin ja existe " + adminEmail);
            return;
        }

        Usuario admin = new Usuario();
        admin.setNome("Lucas");
        admin.setEmail(adminEmail);
        admin.setSenha(passwordEncoder.encode(adminSenha));
        admin.setCargo(Cargo.ADMIN);

        usuarioRepository.save(admin);
        System.out.println(">>> Admin criado: " + adminEmail);

    }

}
