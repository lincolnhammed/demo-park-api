package com.mballem.demo_park_api.exception;

public class UsernameUniqueViolationExeption extends RuntimeException {

    public UsernameUniqueViolationExeption(String mensage) {
        super(mensage);
        // Envia a mensagem para a superclasse RuntimeException.
        // Esta exceção será usada quando o username já existir no sistema.
    }
}
