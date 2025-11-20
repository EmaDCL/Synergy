package com.proaula.spring.synergy.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas_tareas")
public class EntregaTarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con la tarea
    @ManyToOne
    @JoinColumn(name = "id_tarea", nullable = false)
    private Tarea tarea;

    @Column(name = "nombre_archivo", length = 255)
    private String nombreArchivo;

    @Column(name = "tipo_archivo", length = 100)
    private String tipoArchivo;

    @Lob
    @Column(name = "datos")
    private byte[] datos;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    @PrePersist
    public void prePersist() {
        this.fechaEntrega = LocalDateTime.now();
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
