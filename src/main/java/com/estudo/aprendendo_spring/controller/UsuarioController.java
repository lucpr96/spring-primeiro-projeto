package com.estudo.aprendendo_spring.controller;

import com.estudo.aprendendo_spring.business.UsuarioService;
import com.estudo.aprendendo_spring.controller.dtos.UsuarioDTO;
import com.estudo.aprendendo_spring.infrastructure.entity.Usuario;
import com.estudo.aprendendo_spring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario") // URI padrão para todos os metodos REST
@RequiredArgsConstructor

public class UsuarioController {

    // Injeção de Dependência da Service que irá receber as requições e mandar para a Repository
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostMapping
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }

    @PostMapping("/login") // O URI adicionou só pode ocrrer no POST
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                        usuarioDTO.getSenha())
        );
        return "Bearer" + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<Usuario> buscaUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }


    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUsuarioPorEmail(@PathVariable String email){
        usuarioService.deleteUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

}
