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

import com.web.model.CategoriaEntity;
import com.web.service.CategoriaService;
import com.web.service.NotificacionService;
import com.web.service.ProductoService;

@Controller
public class CategoriaController {
	
	@Autowired
    private CategoriaService objcategoriaservice;
	
	@Autowired
	private ProductoService objproductoservice;
	@Autowired
    private NotificacionService objnotificacionService;
	
	@GetMapping("/listarcategoria")
    public String listarCategorias(@AuthenticationPrincipal User user, Model modelo) {
		
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
        modelo.addAttribute("objeto", this.objcategoriaservice.listarCategorias());
        return "listarcategoria"; 
	}
	
	 @GetMapping("/nuevacategoria")
	    public String nuevacategoria(@AuthenticationPrincipal User user, Model modelo) {
		 String username = user.getUsername();
		 modelo.addAttribute("username", username);
		    
		 // Agregamos las notificaciones no leídas y el conteo
		 modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
		 modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
		 
	    return "nuevacategoria";
	    }
	
	@PostMapping("/registrarcategoria")
	public String registrarcategoria(@ModelAttribute("CategoriaEntity") CategoriaEntity objentidad) {
		try {
		this.objcategoriaservice.registrarCategoria(objentidad);
		return "redirect:/listarcategoria";
		}
		catch(Exception ex) {
			//return ex.getMessage();
			return "redirect:/nuevacategoria"; //para que me dirija nuevamente al mismo registro
		}
	}
	
	
	@GetMapping("/frmeditarcategoria/{idcategoria}")
    public String frmeditarcategoria(@PathVariable int idcategoria, Model modelo, @AuthenticationPrincipal User user) {
		String username = user.getUsername();
		modelo.addAttribute("username", username);
		    
		 // Agregamos las notificaciones no leídas y el conteo
		modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
		modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
        modelo.addAttribute("objcontrolador", this.objcategoriaservice.buscarCategoria(idcategoria));
        return "frmeditarcategoria";
    }

    @PostMapping("/editarcategoria/{id}")
    public String editarcategoria(@PathVariable int id, @ModelAttribute("objcategoria") CategoriaEntity objetoentidad) {
        try {
            CategoriaEntity objeditarcategoria = this.objcategoriaservice.buscarCategoria(id);
            
            objeditarcategoria.setNombre(objetoentidad.getNombre());
            objeditarcategoria.setDescripcion(objetoentidad.getDescripcion());
            
            this.objcategoriaservice.editarCategoria(objeditarcategoria);
            
            return "redirect:/listarcategoria";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    @GetMapping("/eliminarcategoria/{id}")
    public @ResponseBody String eliminarcategoria(@PathVariable int id) { //es recomendable dejar @ResponseBody para evitar confusiones
        if (objproductoservice.hayProductosConEstaCat(id)) {
            // No se puede eliminar la categoria porque hay productos asociados
            return "Error: No se puede eliminar la categoria porque hay productos asociados";
        } else {
            try {
                this.objcategoriaservice.eliminarCategoria(id);
                return "redirect:/listarcategoria";
            } catch (Exception ex) {
                return "Error al eliminar la categoria";
            }
        }
    }


}
