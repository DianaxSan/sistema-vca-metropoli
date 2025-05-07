package com.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.model.NotificacionEntity;
import com.web.service.NotificacionService;


@Controller
public class NotificacionController {
	
	@Autowired
    private NotificacionService objnotificacionService;

    
	@GetMapping("/listarnotificaciones")
    public String listarnotificaciones(@AuthenticationPrincipal User user, Model modelo) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
		modelo.addAttribute("notificaciones", this.objnotificacionService.listarNotificaciones());
        return "listarnotificaciones"; 
    } 
	
	@GetMapping("/vernotificacion/{idnotificacion}")
    public String vernotificacion(@AuthenticationPrincipal User user, @PathVariable int idnotificacion, Model modelo) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
        //model.addAttribute("notificacion", this.objnotificacionService.buscarNotificacion(idnotificacion));
		NotificacionEntity notificacion = this.objnotificacionService.buscarNotificacion(idnotificacion);
	    modelo.addAttribute("notificacion", notificacion);
	    modelo.addAttribute("producto", notificacion.getProducto());
        return "vernotificacion";
    }

    @PostMapping("/marcarcomoleida/{id}")
    public String marcarcomoleida(@PathVariable int id, Model modelo) {
        try {
            this.objnotificacionService.marcarComoLeida(id);
            return "redirect:/listarnotificaciones";
        } catch (Exception ex) {
            modelo.addAttribute("errorMessage", "Ocurrió un error al marcar la notificación como leída. Por favor, intente nuevamente.");
            return "vernotificacion";
        }
    }

    @GetMapping("/eliminarnotificacion/{id}")
    public String eliminarnotificacion(@PathVariable int id) {
        try {
            this.objnotificacionService.eliminarNotificacion(id);
            return "redirect:/listarnotificaciones";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}
