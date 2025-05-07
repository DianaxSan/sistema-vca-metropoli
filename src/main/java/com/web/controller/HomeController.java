package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.service.NotificacionService;

@Controller
public class HomeController {
	
	@Autowired
    private NotificacionService objnotificacionService;
	
	@GetMapping("/") // Define la ruta raíz
    public String home() {
        return "redirect:/login"; // Redirigir a la página de login
    }
	
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
	    if (error != null) {
	        model.addAttribute("error", "Usuario o clave incorrecta");
	    }
	    return "login";
	}
	
	
	
	@GetMapping("/index")
	public String index(@AuthenticationPrincipal User user, Model model) {
	    String username = user.getUsername();
	    model.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    model.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    model.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
	    return "index";
	}
	
}
