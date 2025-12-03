package com.mballem.demo_park_api.web.dto.mapper;

import com.mballem.demo_park_api.entity.Usuario;
import com.mballem.demo_park_api.web.dto.UsuarioCreateDto;
import com.mballem.demo_park_api.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioCreateDto createDto){
        /* essa biblioteca faz isso por de baixo dos panos
        ModelMapper().map();
        Usuario user = new Usuario();
        user.setUserName(createDto.getUserName());
        user.setPassword(createDto.getPassword());
        return user;*/
        return new ModelMapper().map(createDto,Usuario.class);
    }
    public static UsuarioResponseDto toDto(Usuario usuario ){
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario,UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios) {

        return usuarios.stream()                // Converte a lista numa stream para processamento funcional
                .map(user -> toDto(user))// Para cada Usuario, aplica o método toDto(), convertendo-o para DTO
                .collect(Collectors.toList()); // Recolhe os DTOs e devolve-os como uma nova lista
    }


}
