package com.web.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.model.CategoriaEntity;
import com.web.model.NotificacionEntity;
import com.web.model.ProductoEntity;
import com.web.model.ReporteEntradaEntity;
import com.web.model.ReporteInventarioEntity;
import com.web.model.ReporteSalidaEntity;
import com.web.service.CategoriaService;
import com.web.service.NotificacionService;
import com.web.service.ReporteService;

@Controller
public class ReporteController {
	
	@Autowired
    private ReporteService objreporteservice;
	@Autowired
    private NotificacionService objnotificacionService;
	@Autowired
    private CategoriaService objcategoriaservice;
	
	 @GetMapping("/generarreportes")
	    public String generarreportes(@AuthenticationPrincipal User user, Model modelo) {
		 String username = user.getUsername();
		 modelo.addAttribute("username", username);
		    
		 // Agregamos las notificaciones no leídas y el conteo
		 modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
		 modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
		 
	    return "generarreportes";
	    }	 
	 
	 @GetMapping("/nuevoreporteinventario")
	    public String nuevoreporteinventario(@AuthenticationPrincipal User user, Model modelo) {
		 String username = user.getUsername();
		 modelo.addAttribute("username", username);
		    
		 // Agregamos las notificaciones no leídas y el conteo
		 modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
		 modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
		 List<CategoriaEntity> categorias = objcategoriaservice.listarCategorias(); 
	     modelo.addAttribute("categorias", categorias);
	     
	     // Agregamos el reporte de inventario
	     ReporteInventarioEntity reporteinventario = objreporteservice.generarReporteInventario(null); // Pasar null si no se selecciona una categoría
	     modelo.addAttribute("reporteinventario", reporteinventario);
	     

	    return "reporteinventario";
	    }	
	 
	 @GetMapping("/reportetodosproductos")
	    public String reporteTodosLosProductos(Model model) {
	        List<ProductoEntity> productos = objreporteservice.generarReporteTodosProductos();
	        model.addAttribute("productos", productos);
	        return "reportetodosproductos"; 
	    }
	 
	 @GetMapping("/reporteproductosstock")
	    public String reporteProductosPorStock(@RequestParam("stockmin") int stockMin,
	                                           @RequestParam("stockmax") int stockMax, Model model) {
	        List<ProductoEntity> productos = objreporteservice.generarReporteProductosPorStock(stockMin, stockMax);
	        model.addAttribute("productos", productos);
	        return "reporteproductosstock"; 
	    }
	 
	 @GetMapping("/reportenotificacionesfechas")
	    public String reporteNotificacionesPorFechas(@RequestParam("fechainicio") java.sql.Date fechainicio,
	                                                 @RequestParam("fechafin") java.sql.Date fechafin, Model model) {
	        List<NotificacionEntity> notificaciones = objreporteservice.generarReporteNotificacionesPorFechas(fechainicio, fechafin);
	        model.addAttribute("notificaciones", notificaciones);
	        return "reportenotificacionesfechas"; 
	    }
	 
	 @GetMapping("/reporteinventario")
	 public String reporteInventario(@RequestParam(value = "categoria", required = false) Integer categoria,
	                                 Model model) {
	     ReporteInventarioEntity reporteinventario = objreporteservice.generarReporteInventario(categoria);
	     model.addAttribute("reporteinventario", reporteinventario);
	     
	     // Cargar las categorías
	     List<CategoriaEntity> categorias = objcategoriaservice.listarCategorias();
	     model.addAttribute("categorias", categorias);
	    	     
	     return "reporteinventario";
	 }
	 
	 @GetMapping("/reporteentradasfechas")
	 public String reporteEntradasPorFechas(@RequestParam("fechainicio") java.sql.Date fechainicio,
	                                       @RequestParam("fechafin") java.sql.Date fechafin, Model model) {
	     List<ReporteEntradaEntity> reportesentradas = objreporteservice.generarReporteEntradasPorFechas(fechainicio, fechafin);
	     model.addAttribute("reportesentradas", reportesentradas);
	     return "reporteentradasfechas"; 
	 }
	 
	 @GetMapping("/reportesalidasfechas")
	 public String reporteSalidasPorFechas(@RequestParam("fechainicio") java.sql.Date fechainicio,
	                                       @RequestParam("fechafin") java.sql.Date fechafin, Model model) {
	     List<ReporteSalidaEntity> reportessalidas = objreporteservice.generarReporteSalidasPorFechas(fechainicio, fechafin);
	     model.addAttribute("reportessalidas", reportessalidas);
	     return "reportesalidasfechas"; 
	 }

}
