package com.mballem.demo_park_api.web.controller;

import com.mballem.demo_park_api.entity.Usuario;
import com.mballem.demo_park_api.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Gera um construtor com o campo 'final', permitindo injecção limpa.
@RequiredArgsConstructor

// Indica que esta classe expõe endpoints REST.
// Todos os métodos devolvem JSON automaticamente.
@RestController

// Caminho base para todos os endpoints deste controller.
// Fica: http://localhost:8080/api/v1/usuarios
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    /**
     * Serviço responsável pela lógica de negócio relacionada ao utilizador.
     * Injectado pelo construtor gerado automaticamente.
     */
    private final UsuarioService usuarioService;

    /**
     * @PostMapping indica que este método responde a uma requisição HTTP POST.
     * É utilizado para criar novos recursos (no caso, um novo utilizador).
     *
     * @RequestBody diz ao Spring para converter o JSON recebido
     * para um objecto Usuario automaticamente.
     *
     * ResponseEntity permite controlar:
     * - o corpo da resposta (body)
     * - o código HTTP (ex.: 201 CREATED)
     */
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {

        // Chama o serviço para gravar o utilizador na base de dados.
        Usuario user = usuarioService.salvar(usuario);

        // Retorna 201 CREATED + o objeto criado em formato JSON.
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    // Endpoint HTTP GET para obter um usuário pelo ID.
    // O {id} é capturado da URL, ex: GET /usuarios/5
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {

        // Chama o serviço para buscar o usuário.
        // Se não existir, o serviço lança exceção.
        Usuario user = usuarioService.buscarPorId(id);

        // Retorna HTTP 200 OK contendo o objeto Usuario como JSON.
        return ResponseEntity.ok().body(user);
    }
    //@PatchMapping é usado para alterações parciais — aqui estás a actualizar apenas a password, não o utilizador completo.
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> updatePassword(
            @PathVariable Long id,          // Captura o ID da URL (ex: /usuarios/5)
            @RequestBody Usuario usuario    // Recebe o JSON com o novo password
    ) {

        // Chama o serviço para actualizar apenas a senha.
        // Do JSON recebido, só usamos o campo "password".
        Usuario user = usuarioService.editarSenha(id, usuario.getPassword());

        // Devolve código 200 (OK) e o utilizador já com a senha actualizada.
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {

        // Chama o service para obter todos os utilizadores da BD.
        List<Usuario> users = usuarioService.buscarTodos();

        // Retorna código 200 (OK) e envia a lista de utilizadores em formato JSON.
        return ResponseEntity.ok(users);
    }


}
