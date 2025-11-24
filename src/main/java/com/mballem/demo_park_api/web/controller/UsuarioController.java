package com.mballem.demo_park_api.web.controller;

// Importa o serviço que será usado neste controller.
import com.mballem.demo_park_api.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Gera automaticamente um construtor com os campos 'final'.
// É usado para injecção de dependências de forma limpa e segura.
@RequiredArgsConstructor

// Indica que esta classe é um controller REST.
// O Spring passa a tratar esta classe como um endpoint HTTP.
@RestController

// Define o caminho base para todos os endpoints deste controller.
// Ex.: http://localhost:8080/api/v1/usuarios
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    // Dependência obrigatória do serviço de utilizadores.
    // O 'final' garante imutabilidade e injecção via construtor.
    private final UsuarioService usuarioService;
}
