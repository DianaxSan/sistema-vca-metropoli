package com.web.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.model.DetalleSalidaEntity;
import com.web.model.SalidaEntity;
import com.web.service.DetalleSalidaService;
import com.web.service.NotificacionService;
import com.web.service.ProductoService;
import com.web.service.SalidaService;

@Controller
public class SalidaController {
	
	@Autowired
	private SalidaService objsalidaservice;
	@Autowired
	private DetalleSalidaService objdetallesalidaservice;
	@Autowired
	private ProductoService objproductoservice;
	@Autowired
    private NotificacionService objnotificacionService;
	
	@GetMapping("/listarsalidas")
    public String listarsalidas(@AuthenticationPrincipal User user, Model modelo) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
        modelo.addAttribute("objeto", this.objsalidaservice.listarSalidas());
        return "listarsalidas"; 
	}
	
	@GetMapping("/verdetallessalida/{idsalida}")
	public String verdetallessalida(@PathVariable("idsalida") int idsalida, Model modelo, @AuthenticationPrincipal User user) {
		
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
	    List<DetalleSalidaEntity> objdetallesalida = objdetallesalidaservice.listarDetalleSalidasPorSalidaId(idsalida);
	    SalidaEntity objsalida = objsalidaservice.buscarSalida(idsalida); //para mostrar a que salida pertenecen esos detalles
	    modelo.addAttribute("detalles", objdetallesalida);
	    modelo.addAttribute("salida", objsalida); //para mostrar a que salida pertenecen esos detalles
	    return "detallesalida";
		}
	
	@GetMapping("/nuevasalida")
    public String nuevasalida(@AuthenticationPrincipal User user,  Model modelo) {
		
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
		modelo.addAttribute("objsalida", new SalidaEntity()); // Objeto para el formulario de salida
		modelo.addAttribute("productos", objproductoservice.listarProductos());
		return "nuevasalida";
   }
	
	@PostMapping("/registrarsalida")
    public String registrarsalida(@RequestParam("fecha") Date fecha,
                                   @RequestParam("motivo") String motivo,
                                   @RequestParam("productosJson") String productosJson) {
	    
        // Crear la entidad de entrada
        SalidaEntity salida = new SalidaEntity();
        salida.setFecha(fecha);
        salida.setMotivo(motivo);

        // Registrar la entrada
        objsalidaservice.registrarSalidaProcedure(salida, productosJson);

        return "redirect:/listarsalidas";
    }
	
	@GetMapping("/eliminarsalida/{idsalida}")
	public String eliminarsalida(@PathVariable("idsalida") int idsalida) {
	    objsalidaservice.eliminarSalidaYdetalles(idsalida);
	    return "redirect:/listarsalidas";
	}

}
