package com.estudo.aprendendo_spring.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioDTO {

    private String email;
    private String senha;

}

// Essa classe DTO é uma Entiy (não uma tabela) que possue a função de fazer a transferência
// de objetos de uma classe para outra, expondo dados selecionados ou recebendo dados
