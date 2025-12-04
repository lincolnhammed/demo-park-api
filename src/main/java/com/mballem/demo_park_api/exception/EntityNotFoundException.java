package com.mballem.demo_park_api.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String mensage) {
        super(mensage);
        // Passa a mensagem para a superclasse RuntimeException.
        // Esta exceção é usada quando uma entidade procurada não existe no sistema.
    }
}
