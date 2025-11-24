package com.mballem.demo_park_api.service;

import com.mballem.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Service indica ao Spring que esta classe é um componente de serviço.
 * Ela será registada automaticamente no contexto da aplicação
 * e poderá ser injectada noutras classes.
 */
@Service
@RequiredArgsConstructor // Gera automaticamente um construtor com os campos 'final'.
public class UsuarioService {

    /**
     * O 'final' indica que esta dependência é obrigatória e imutável.
     * O Spring irá injectar automaticamente o UsuarioRepository
     * através do construtor gerado pelo @RequiredArgsConstructor.
     */
    private final UsuarioRepository usuarioRepository;

    // Aqui no futuro vais criar métodos de negócio,
    // por exemplo: criar utilizador, listar, actualizar, etc.
}