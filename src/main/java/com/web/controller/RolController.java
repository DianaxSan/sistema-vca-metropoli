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
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.model.RolEntity;
import com.web.service.NotificacionService;
import com.web.service.RolService;
import com.web.service.UsuarioService;



@Controller
public class RolController {
	
	@Autowired
    private RolService objrolservice;
	@Autowired
	private UsuarioService objusuarioservice;
	@Autowired
    private NotificacionService objnotificacionService;

    
    @GetMapping("/listarrol")
    public String listarRoles(@AuthenticationPrincipal User user, Model modelo) {
    	String username = user.getUsername();//para captura el nombre del usuario
	    modelo.addAttribute("username", username); //para que se vea el usuario en este listarol.html
	 // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
        modelo.addAttribute("objeto", this.objrolservice.listarRoles());
        return "listarrol"; 
    }
    
    @GetMapping("/nuevorol")
    public String nuevorol(@AuthenticationPrincipal User user, Model modelo) {
    	String username = user.getUsername();//para captura el nombre del usuario
	    modelo.addAttribute("username", username); //para que se vea el usuario en este listarol.html
	 // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
    	return "nuevorol";
    }
    
    @PostMapping("/registrarrol")
    public String registrarrol(@ModelAttribute("RolEntity") RolEntity objentidad, @AuthenticationPrincipal User user, Model modelo) {   
    	String username = user.getUsername();//para captura el nombre del usuario
	    modelo.addAttribute("username", username); //para que se vea el usuario en este html
    	try {
    	this.objrolservice.registrarRol(objentidad);
    	return "redirect:/listarrol";
    	}
    	catch(Exception ex) {
    		//return ex.getMessage();
    		return "redirect:/nuevorol"; //para que me dirija nuevamente al mismo registro
    	}
    }
    
    @GetMapping("/frmeditar/{idrol}")
    public String frmeditarrol(@PathVariable int idrol, Model modelo, @AuthenticationPrincipal User user) {
    	String username = user.getUsername();//para captura el nombre del usuario
	    modelo.addAttribute("username", username); //para que se vea el usuario en este listarol.html
	 // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
        modelo.addAttribute("objcontrolador", this.objrolservice.buscarRol(idrol));
        return "frmeditarrol";
    }
    
    
    @PostMapping("/editarrol/{id}")
    public String editarRol(@PathVariable int id, @ModelAttribute("RolEntity") RolEntity objetoentidad, @AuthenticationPrincipal User user, Model modelo) {
    	String username = user.getUsername();//para captura el nombre del usuario
	    modelo.addAttribute("username", username); //para que se vea el usuario en este html
        try {
            RolEntity objeditarrol = this.objrolservice.buscarRol(id);

            objeditarrol.setNombre(objetoentidad.getNombre());
            objeditarrol.setDescripcion(objetoentidad.getDescripcion());

            this.objrolservice.editarRol(objeditarrol);

            return "redirect:/listarrol";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    
    @GetMapping("/eliminarrol/{id}")
    public @ResponseBody String eliminarrol(@PathVariable int id) { //es recomendable dejar @ResponseBody para evitar confusiones
        if (objusuarioservice.hayUsuariosConEsteRol(id)) {
            // No se puede eliminar el rol porque hay usuarios asociados
            return "Error: No se puede eliminar el rol porque hay usuarios asociados";
        } else {
            try {
                this.objrolservice.eliminarRol(id);
                return "redirect:/listarrol";
            } catch (Exception ex) {
                return "Error al eliminar el rol";
            }
        }
    }
}
