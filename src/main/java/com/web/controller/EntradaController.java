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

import com.web.model.DetalleEntradaEntity;
import com.web.model.EntradaEntity;
import com.web.service.DetalleEntradaService;
import com.web.service.EntradaService;
import com.web.service.NotificacionService;
import com.web.service.ProductoService;


@Controller
public class EntradaController {
	
	@Autowired
    private EntradaService objentradaservice;
	@Autowired
	private DetalleEntradaService objdetalleentradaservice;
	@Autowired
	private ProductoService objproductoservice;
	@Autowired
    private NotificacionService objnotificacionService;

	
	@GetMapping("/listarentradas")
    public String listarentradas(@AuthenticationPrincipal User user, Model modelo) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
        modelo.addAttribute("objeto", this.objentradaservice.listarEntradas());
        return "listarentradas"; 
	}
	
	
	@GetMapping("/verdetallesentrada/{identrada}")
    public String verdetallesentrada(@PathVariable("identrada") int identrada, Model modelo, @AuthenticationPrincipal User user) {
		
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
        List<DetalleEntradaEntity> objdetalleentrada = objdetalleentradaservice.listarDetalleEntradasPorEntradaId(identrada);
        EntradaEntity objentrada = objentradaservice.buscarEntrada(identrada); //para mostrar a que entrada pertenecen esos detalles
        modelo.addAttribute("detalles", objdetalleentrada);
        modelo.addAttribute("entrada", objentrada); //para mostrar a que entrada pertenecen esos detalles
        return "detalleentrada";
	}
	
	@GetMapping("/nuevaentrada")
    public String nuevaentrada(@AuthenticationPrincipal User user, Model modelo) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	    
		modelo.addAttribute("objentrada", new EntradaEntity()); // Objeto para el formulario de entrada
		modelo.addAttribute("productos", objproductoservice.listarProductos());
		return "nuevaentrada";
   }
		
	@PostMapping("/registrarentrada")
    public String registrarentrada(@RequestParam("fecha") Date fecha,
                                   @RequestParam("motivo") String motivo,
                                   @RequestParam("productosJson") String productosJson) {
		
		// Imprimir el JSON para depuración
	    System.out.println("Productos JSON: " + productosJson);
	    
        // Crear la entidad de entrada
        EntradaEntity entrada = new EntradaEntity();
        entrada.setFecha(fecha);
        entrada.setMotivo(motivo);

        // Registrar la entrada
        objentradaservice.registrarEntradaProcedure(entrada, productosJson);

        return "redirect:/listarentradas";
    }
	
	@GetMapping("/eliminarentrada/{identrada}")
	public String eliminarEntrada(@PathVariable("identrada") int identrada) {
	    objentradaservice.eliminarEntradaYdetalles(identrada);
	    return "redirect:/listarentradas";
	}

}
