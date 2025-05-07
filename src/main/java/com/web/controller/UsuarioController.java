package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.model.UsuarioEntity;
import com.web.service.NotificacionService;
import com.web.service.RolService;
import com.web.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
    private UsuarioService objusuarioservice;
	
	@Autowired
    private RolService objrolservice;
	@Autowired
    private NotificacionService objnotificacionService;
	
	@GetMapping("/listarusuario")
    public String listarUsuarios(@AuthenticationPrincipal User user, Model modelo) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
        modelo.addAttribute("objeto", this.objusuarioservice.listarUsuarios());
        return "listarusuario"; 
    }
	
	@GetMapping("/nuevousuario")
    public String nuevousuario(@AuthenticationPrincipal User user, Model modelo) {
		//String username = user.getUsername();
	    //modelo.addAttribute("username", username);
	    
	    if (user != null) {
	        String username = user.getUsername();
	        modelo.addAttribute("username", username);
	    } else {
	        modelo.addAttribute("username", "Invitado"); // O cualquier valor por defecto
	    }
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    modelo.addAttribute("rol", objrolservice.listarRoles());
	 
	    return "nuevousuario";
    }
	
	@PostMapping("/registrarusuario")
	 public String registrarusuario(@ModelAttribute("UsuarioEntity") UsuarioEntity objentidad) {
	 	try {
	 	this.objusuarioservice.registrarUsuario(objentidad);
	 	return "redirect:/listarusuario";
	 	}
	 	catch(Exception ex) {
	 		//return ex.getMessage();
	 		return "redirect:/nuevousuario"; //para que me dirija nuevamente al mismo registro
	 	}
	 }
	
	@GetMapping("/frmeditarusuario/{idusuario}")
    public String frmeditarUsuario(@PathVariable int idusuario, Model modelo, @AuthenticationPrincipal User user) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
        modelo.addAttribute("objcontrolador", objusuarioservice.buscarUsuario(idusuario));
        modelo.addAttribute("rol", objrolservice.listarRoles());
        return "frmeditarusuario";
    }
	
	@PostMapping("/editarusuario/{id}")
    public String editarUsuario(@PathVariable int id, @ModelAttribute("UsuarioEntity") UsuarioEntity objentidad) {
        try {
            UsuarioEntity objeditarusuario = objusuarioservice.buscarUsuario(id);
            
            objeditarusuario.setUsuario(objentidad.getUsuario());
            if (!objentidad.getClave().isEmpty()) {
                objeditarusuario.setClave(objentidad.getClave());
            }
            //objeditarusuario.setClave(objentidad.getClave());
            objeditarusuario.setRol(objentidad.getRol());
            
            objusuarioservice.editarUsuario(objeditarusuario);
            
            return "redirect:/listarusuario";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    @GetMapping("/eliminarusuario/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        try {
            objusuarioservice.eliminarUsuario(id);
            return "redirect:/listarusuario";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }


}
