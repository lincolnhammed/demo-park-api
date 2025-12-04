package com.mballem.demo_park_api.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
@Getter @ToString
public class ErrorMessage {

    // Caminho da requisição (ex: /api/usuarios)
    private String path;

    // Método HTTP usado (GET, POST, PUT, DELETE...)
    private String method;

    // Código de status HTTP (ex: 400, 404, 500)
    private int status;

    // Texto do status HTTP (ex: "Bad Request")
    private String statusText;

    // Mensagem geral de erro definida por ti
    private String menssage;

    // Mapa com erros específicos de validação (campo -> mensagem)
    private Map<String, String> errors;

    // Construtor padrão (necessário para serialização/deserialização)
    public ErrorMessage() {
    }

    // Construtor para erros simples, sem validação de campos
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String menssage) {

        // Guarda o path da requisição
        this.path = request.getRequestURI();

        // Guarda o método HTTP usado
        this.method = request.getMethod();

        // Guarda o código numérico do status (ex: 400)
        this.status = status.value();

        // Guarda o texto padrão do status (ex: "Bad Request")
        this.statusText = status.getReasonPhrase();

        // Guarda a mensagem de erro personalizada
        this.menssage = menssage;
    }

    // Construtor para erros COM validação de campos (BindingResult)
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String menssage, BindingResult result) {

        // Mesmo comportamento do construtor anterior
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.menssage = menssage;

        // Adiciona detalhes dos erros de validação
        addErrors(result);
    }

    // Método responsável por preencher o mapa "errors" com mensagens específicas
    private void addErrors(BindingResult result) {

        // Cria um mapa vazio para armazenar os erros
        this.errors = new HashMap<>();

        // Percorre todos os erros de campo gerados pelas validações
        for (FieldError fieldError : result.getFieldErrors()) {

            // Coloca no mapa: nome do campo -> mensagem de erro
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}