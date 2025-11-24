package com.mballem.demo_park_api.service;

import com.mballem.demo_park_api.entity.Usuario;
import com.mballem.demo_park_api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Service indica ao Spring que esta classe é um componente de serviço.
 * Aqui ficam as regras de negócio relacionadas ao utilizador.
 * Esta classe pode ser injectada noutras partes da aplicação.
 */
@Service

// Gera automaticamente um construtor com todos os campos 'final'.
// O Spring usa esse construtor para fazer a injecção de dependências.
@RequiredArgsConstructor
public class UsuarioService {

    /**
     * Dependência obrigatória para comunicação com a base de dados.
     * O 'final' garante que:
     * 1. Esta dependência é obrigatória no construtor.
     * 2. Não pode ser alterada depois de inicializada (imutável).
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * @Transactional garante que este método é executado dentro de uma transacção.
     * Ou seja: se acontecer algum erro, o Spring faz rollback.
     * Aqui fazemos operações de escrita na BD, por isso é apropriado.
     */
    @Transactional
    public Usuario salvar(Usuario usuario) {
        // Chama o método save() do JpaRepository,
        // que guarda ou actualiza o registo automaticamente.
        return usuarioRepository.save(usuario);
    }

    // Aqui futuramente podem entrar mais regras de negócio:
    // - validar email único
    // - encriptar palavras-passe
    // - regras de criação de utilizador
}
