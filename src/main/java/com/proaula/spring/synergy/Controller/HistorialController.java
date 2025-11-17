package com.proaula.spring.synergy.Controller;

import com.proaula.spring.synergy.Service.HistorialService;
import com.proaula.spring.synergy.Service.HistorialService.AccionHistorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/historial")
public class HistorialController {
    
    @Autowired
    private HistorialService historialService;
    
    // Ver historial completo
    @GetMapping
    public String verHistorial(Model model) {
        List<AccionHistorial> historial = historialService.obtenerHistorialCompleto();
        
        model.addAttribute("historial", historial);
        model.addAttribute("totalAcciones", historialService.contarAcciones());
        model.addAttribute("hayDeshacer", historialService.hayAccionesParaDeshacer());
        model.addAttribute("hayRehacer", historialService.hayAccionesParaRehacer());
        
        // Mostrar en consola también
        historialService.mostrarHistorial();
        
        return "historial";
    }
    
    // Deshacer última acción
    @PostMapping("/deshacer")
    public String deshacer(Model model) {
        AccionHistorial accion = historialService.deshacer();
        
        if (accion != null) {
            model.addAttribute("mensaje", "✅ Acción deshecha: " + accion.getDescripcion());
        } else {
            model.addAttribute("mensaje", "⚠️ No hay acciones para deshacer");
        }
        
        return "redirect:/historial";
    }
    
    // Rehacer última acción deshecha
    @PostMapping("/rehacer")
    public String rehacer(Model model) {
        AccionHistorial accion = historialService.rehacer();
        
        if (accion != null) {
            model.addAttribute("mensaje", "✅ Acción rehecha: " + accion.getDescripcion());
        } else {
            model.addAttribute("mensaje", "⚠️ No hay acciones para rehacer");
        }
        
        return "redirect:/historial";
    }
    
    // Limpiar historial
    @PostMapping("/limpiar")
    public String limpiarHistorial() {
        historialService.limpiarHistorial();
        return "redirect:/historial";
    }
    
    // Ver últimas N acciones (API REST)
    @GetMapping("/ultimas/{cantidad}")
    @ResponseBody
    public List<AccionHistorial> obtenerUltimas(@PathVariable int cantidad) {
        return historialService.obtenerUltimasAcciones(cantidad);
    }
    
    // Filtrar por tipo
    @GetMapping("/tipo/{tipo}")
    @ResponseBody
    public List<AccionHistorial> filtrarPorTipo(@PathVariable String tipo) {
        return historialService.filtrarPorTipo(tipo);
    }
}