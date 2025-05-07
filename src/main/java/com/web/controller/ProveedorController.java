package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.model.ProveedorEntity;
import com.web.service.NotificacionService;
import com.web.service.ProveedorService;

@Controller
public class ProveedorController {
	
	@Autowired
    private ProveedorService objproveedorservice;
	
	@Autowired
    private NotificacionService objnotificacionService;

	
	@GetMapping("/listarproveedor")
    public String listarProveedores(@AuthenticationPrincipal User user, Model modelo) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
        modelo.addAttribute("objeto", this.objproveedorservice.listarProveedores());
        return "listarproveedor"; 
	}
	
	@GetMapping("/nuevoproveedor")
    public String nuevoproveedor(@AuthenticationPrincipal User user, Model modelo) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
    	return "nuevoproveedor";
    }
	
	
	@PostMapping("/registrarproveedor")
    public String registrarproveedor(@ModelAttribute("ProveedorEntity") ProveedorEntity objentidad, Model model) {
        try {
            this.objproveedorservice.registrarProveedor(objentidad);
            return "redirect:/listarproveedor";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorMessage", "El nombre y/o contacto del proveedor ya existe. Por favor, modifique.");
            return "nuevoproveedor";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Ocurrió un error al registrar el proveedor. Por favor, intente nuevamente.");
            return "nuevoproveedor";
        }
    }
	
	
	@GetMapping("/frmeditarproveedor/{idproveedor}")
    public String frmeditarproveedor(@PathVariable int idproveedor, Model modelo, @AuthenticationPrincipal User user) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
        modelo.addAttribute("objcontrolador", this.objproveedorservice.buscarProveedor(idproveedor));
        return "frmeditarproveedor";
    }

    @PostMapping("/editarproveedor/{id}")
    public String editarproveedor(@PathVariable int id, @ModelAttribute("objproveedor") ProveedorEntity objetoentidad) {
        try {
            ProveedorEntity objeditarproveedor = this.objproveedorservice.buscarProveedor(id);
            
            objeditarproveedor.setNombre(objetoentidad.getNombre());
            objeditarproveedor.setDireccion(objetoentidad.getDireccion());
            objeditarproveedor.setContacto(objetoentidad.getContacto());
            
            this.objproveedorservice.editarProveedor(objeditarproveedor);
            
            return "redirect:/listarproveedor";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @GetMapping("/eliminarproveedor/{id}")
    public String eliminarproveedor(@PathVariable int id) {
        try {
            this.objproveedorservice.eliminarProveedor(id);
            return "redirect:/listarproveedor";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}
