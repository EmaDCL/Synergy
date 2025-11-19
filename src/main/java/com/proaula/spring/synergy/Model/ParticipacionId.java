package com.proaula.spring.synergy.Model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class ParticipacionId implements Serializable {

    private Long usuarioId;
    private Long proyectoId;

    public ParticipacionId() {}

    public ParticipacionId(Long usuarioId, Long proyectoId) {
        this.usuarioId = usuarioId;
        this.proyectoId = proyectoId;
    }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getProyectoId() { return proyectoId; }
    public void setProyectoId(Long proyectoId) { this.proyectoId = proyectoId; }

    // equals y hashCode SON OBLIGATORIOS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipacionId)) return false;
        ParticipacionId that = (ParticipacionId) o;
        return usuarioId.equals(that.usuarioId) && proyectoId.equals(that.proyectoId);
    }

    @Override
    public int hashCode() {
        return usuarioId.hashCode() + proyectoId.hashCode();
    }
}
