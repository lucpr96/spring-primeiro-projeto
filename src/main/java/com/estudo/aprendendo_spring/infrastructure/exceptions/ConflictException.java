package com.estudo.aprendendo_spring.infrastructure.exceptions;

public class ConflictException extends RuntimeException{

    public ConflictException(String mensagem){
        super(mensagem);
    }

    public ConflictException(String mennsagem, Throwable throwable){
        super(mennsagem);
    }
}
