package com.mballem.demo_park_api.service;

import com.mballem.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor//cria um metodo contrutor para fazer injecao de dependencias
@Service

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
}
