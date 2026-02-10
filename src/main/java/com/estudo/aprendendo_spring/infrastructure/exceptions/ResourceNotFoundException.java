package com.estudo.aprendendo_spring.infrastructure.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }

    public ResourceNotFoundException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}


// Exception para mostrar o erro de Usuario não encontrado pel email deste Usuario.