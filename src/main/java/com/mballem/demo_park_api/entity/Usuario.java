package com.mballem.demo_park_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {//implements Serializable boa pratica quando trbaalha com o frameworj jpa/hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="userName",nullable = false,unique = true,length = 100)
    private String userName;

    @Column(name="password", nullable = false,length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role",nullable = false,length = 25)
    private Role role;

    @Column(name="dataCriacao")
    private LocalDateTime dataCriacao;

    @Column(name="dataModificacao")
    private LocalDateTime dataModificacao;

    @Column(name="criadoPor")
    private String criadoPor;

    @Column(name="modificadoPor")
    private String modificadoPor;

    public enum Role{
        ROLE_ADMIN, ROLE_CLIENTE
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                '}';
    }
}
