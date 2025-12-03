package com.mballem.demo_park_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioSenhaDto {
    @NotBlank(message = "password é obrigatório")
    @Size(min =6,max =6)// exige que o campo tenha exatamente 6 caracteres
    private String senhaAtual;
    @NotBlank(message = "password é obrigatório")
    @Size(min =6,max =6)// exige que o campo tenha exatamente 6 caracteres
    private String novaSenha;
    @NotBlank(message = "password é obrigatório")
    @Size(min =6,max =6)// exige que o campo tenha exatamente 6 caracteres
    private String confirmaSenha;
}
