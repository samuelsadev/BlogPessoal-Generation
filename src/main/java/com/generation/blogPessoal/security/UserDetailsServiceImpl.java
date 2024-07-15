package com.generation.blogPessoal.security;

import com.generation.blogPessoal.model.Usuario;
import com.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<Usuario> usuario = usuarioRepository.findByUsuario(username);
//
//        if (usuario.isEmpty())
//            return new UserDetailsImpl(usuario.get());
//        else
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario(username);

        if (usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado com o username: " + username);
        }

        Usuario usuario = usuarioOptional.get();

        return User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getSenha())
                .authorities(Collections.emptyList())
                .build();
    }
}
