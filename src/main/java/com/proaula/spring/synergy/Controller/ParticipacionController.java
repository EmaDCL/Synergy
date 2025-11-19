package com.proaula.spring.synergy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proaula.spring.synergy.Service.ParticipacionService;
import com.proaula.spring.synergy.Model.Participacion;

import java.util.List;

@RestController
@RequestMapping("/participacion")
@CrossOrigin(origins = "*")
public class ParticipacionController {

    @Autowired
    private ParticipacionService participacionService;

    // INSCRIBIR usuario a un proyecto
    @PostMapping("/inscribir/{proyectoId}/{usuarioId}")
    public ResponseEntity<?> inscribir(
            @PathVariable Long proyectoId,
            @PathVariable Long usuarioId) {

        Participacion participacion = participacionService.inscribirUsuario(proyectoId, usuarioId);

        if (participacion == null) {
            return ResponseEntity.badRequest().body("Usuario ya está inscrito en este proyecto");
        }

        return ResponseEntity.ok(participacion);
    }

    // LISTAR participantes de un proyecto
    @GetMapping("/listar/{proyectoId}")
    public ResponseEntity<List<Participacion>> listar(@PathVariable Long proyectoId) {
        return ResponseEntity.ok(participacionService.listarParticipantes(proyectoId));
    }

    // VERIFICAR si un usuario ya está inscrito
    @GetMapping("/inscrito/{proyectoId}/{usuarioId}")
    public ResponseEntity<Boolean> estaInscrito(
            @PathVariable Long proyectoId,
            @PathVariable Long usuarioId) {

        return ResponseEntity.ok(participacionService.yaInscrito(proyectoId, usuarioId));
    }

    // ELIMINAR participación
    @DeleteMapping("/eliminar/{proyectoId}/{usuarioId}")
    public ResponseEntity<?> eliminar(
            @PathVariable Long proyectoId,
            @PathVariable Long usuarioId) {

        participacionService.eliminarParticipacion(usuarioId, proyectoId);
        return ResponseEntity.ok("Participación eliminada");
    }
}
