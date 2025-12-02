package com.mballem.demo_park_api.service;

import com.mballem.demo_park_api.entity.Usuario;
import com.mballem.demo_park_api.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    // Indica que o método é transacional apenas para leitura.
    // Isso melhora performance e garante consistência.
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {

        // O repository procura o usuário no banco.
        // findById retorna Optional<Usuario>.
        // Se existir -> devolve o usuário.
        // Se não existir -> lança RuntimeException com a mensagem definida.
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não Encontrado.")
        );
    }
    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if(!novaSenha.equals(confirmaSenha)){
            throw new RuntimeException("Nova senha não confere com confirmação de senha");
        }
        // Busca o utilizador pelo ID.
        // Se não existir, lança uma RuntimeException (podes trocar por algo mais elegante depois).
        Usuario user = buscarPorId(id);
        if(!user.getPassword().equals(senhaAtual)){
            throw new RuntimeException(" Sua senha não confere.");
        }


        // Actualiza somente o campo "password".
        // Como a entidade está dentro de uma transacção, o Hibernate detecta a alteração.
        user.setPassword(novaSenha);


        // NÃO é necessário chamar save(), porque:
        // - a entidade está gerida pelo Hibernate
        // - a transacção está activa
        // - ao terminar, o Hibernate faz o UPDATE automaticamente (dirty checking)
        return user;
    }
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {

        // Chama o método findAll() do JpaRepository.
        // Ele faz automaticamente um SELECT * FROM usuario
        // e devolve a lista de todos os utilizadores existentes na BD.
        return usuarioRepository.findAll();
    }



    // Aqui futuramente podem entrar mais regras de negócio:
    // - validar email único
    // - encriptar palavras-passe
    // - regras de criação de utilizador
}
