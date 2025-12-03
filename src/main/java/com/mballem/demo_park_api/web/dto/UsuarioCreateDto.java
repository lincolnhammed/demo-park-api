package com.mballem.demo_park_api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCreateDto {
    //regexp = "" - - - - - - - -  "Minha validaçao "
    //@Email(
    //    regexp = "^(?=.{6,254}$)(?=.{1,64}@)[A-Za-z0-9._%+-]+@minhaempresa\\.pt$",
    //    message = "apenas e-mails @minhaempresa.pt são permitidos"
    //)
    @NotBlank(message = "o nome é obrigatório e não pode estar vazio")//valida que o campo é obrigatório e contém texto real (não vazio nem só espaços)
    @Email(regexp ="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",message = "formato do e-mail está invalido")
    private String userName;
    @NotBlank(message = "password é obrigatório")
    @Size(min =6,max =6)// exige que o campo tenha exatamente 6 caracteres
    private String password;

}
