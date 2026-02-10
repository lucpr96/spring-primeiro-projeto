package com.estudo.aprendendo_spring.business;

import com.estudo.aprendendo_spring.infrastructure.entity.Usuario;
import com.estudo.aprendendo_spring.infrastructure.exceptions.ConflictException;
import com.estudo.aprendendo_spring.infrastructure.exceptions.ResourceNotFoundException;
import com.estudo.aprendendo_spring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Metodo 1 -  para salvar o usuário
    public Usuario salvaUsuario(Usuario usuario){
        try{

            emailExiste(usuario.getEmail());
            //Regra de negocio para verificar se o email já existe - por isso preciso ficar no {} do try/catch

            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            //Por meio do objeto PasswordEncoder é possível codificar a senha pelo metodo " set senha "


            return usuarioRepository.save(usuario);
            //Possui a função de salvar, uma vez que extende o metodo JpaRepository

        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado " + e.getCause());
        }

    }

    // Metodo 2 - usado para verificar se o email cadastrado existe,
    // lançando o ConflictException da Exception Class
    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictException("Email já cadastrado " + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado " + e.getCause());
        }
    }

    // Metodo 3 - usado para chamar o metodo da Interface UsuarioRepository
    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }


    //Variável para fazer a busca de Usuario por email - Metodo GET
    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado: " + email));
    }


    //Variável para fazer o delete do Usuario por email - Metodo DELTE
    public void deleteUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }


}


