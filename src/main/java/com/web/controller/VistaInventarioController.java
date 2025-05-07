package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.service.NotificacionService;
import com.web.service.VistaInventarioService;

@Controller
public class VistaInventarioController {
	
	@Autowired
    private VistaInventarioService objvistainventarioservice;
	
	@Autowired
    private NotificacionService objnotificacionService;

    @GetMapping("/listarinventario")
    public String listarInventarios(@AuthenticationPrincipal User user, Model modelo) {
    	
    	String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
        // Obtener todos los registros de la vista inventario
        modelo.addAttribute("objeto", objvistainventarioservice.listarinventario());
        return "listarinventario"; 
    }

}
