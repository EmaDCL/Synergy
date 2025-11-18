package com.proaula.spring.synergy.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "lider_proyecto")
public class LiderProyecto {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Usuarios usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
