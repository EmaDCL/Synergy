package com.proaula.spring.synergy.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "participacion")
public class Participacion {

    @EmbeddedId
    private ParticipacionId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @ManyToOne
    @MapsId("proyectoId")
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    public Participacion() {}

    public Participacion(Usuarios usuario, Proyecto proyecto) {
        this.usuario = usuario;
        this.proyecto = proyecto;
        this.id = new ParticipacionId(usuario.getId(), proyecto.getId());
    }

    public ParticipacionId getId() { return id; }
    public void setId(ParticipacionId id) { this.id = id; }

    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }
}
