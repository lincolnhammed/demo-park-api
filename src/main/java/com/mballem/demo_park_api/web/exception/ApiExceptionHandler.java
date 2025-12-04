package com.mballem.demo_park_api.web.exception;

import com.mballem.demo_park_api.exception.UsernameUniqueViolationExeption;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j // Cria automaticamente um logger chamado "log" (Lombok)
@RestControllerAdvice // Indica que esta classe trata excepções de forma global na API
public class ApiExceptionHandler {

    // Trata especificamente erros de validação: @Valid + Bean Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> MethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result) {

        // Regista no log o erro completo (stacktrace incluído)
        log.error("Api Error - ", ex);

        // Devolve uma resposta HTTP personalizada
        return ResponseEntity
                // Define o status HTTP 422 (UNPROCESSABLE_ENTITY)
                .status(HttpStatus.UNPROCESSABLE_ENTITY)

                // Força o retorno no formato JSON
                .contentType(MediaType.APPLICATION_JSON)

                // Corpo da resposta, usando o teu objeto de erro personalizado
                // Passa o request, o status e a lista de erros de validação
                .body(new ErrorMessage(
                        request,
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Campo(s) invalido(s) ", // Mensagem geral de erro
                        result // Lista de erros de validação
                ));
    }

    @ExceptionHandler(UsernameUniqueViolationExeption.class)
    // Este método trata a exceção lançada quando o username já existe.

    public ResponseEntity<ErrorMessage> MethodArgumentNotValidException(RuntimeException ex,
                                                                        HttpServletRequest request) {

        log.error("Api Error - ", ex);
        // Regista o erro no log.

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                // Retorna HTTP 409 (CONFLICT), apropriado para dados duplicados.

                .contentType(MediaType.APPLICATION_JSON)
                // Garante resposta no formato JSON.

                .body(new ErrorMessage(
                        request,
                        HttpStatus.CONFLICT,
                        ex.getMessage()
                        // Usa a mensagem da exceção como descrição do erro.
                ));
    }

}

