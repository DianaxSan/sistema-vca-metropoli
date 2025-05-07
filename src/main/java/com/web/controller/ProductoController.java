package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.model.ProductoEntity;
import com.web.service.CategoriaService;
import com.web.service.ImagenService;
import com.web.service.NotificacionService;
import com.web.service.ProductoService;

@Controller
public class ProductoController {
	
	
	@Autowired
    private ProductoService objproductoservice;
	
	@Autowired
    private CategoriaService objcategoriaservice;
	
	@Autowired
	private ImagenService objimagenservice;
	
	@Autowired
    private NotificacionService objnotificacionService;
	
	@GetMapping("/listarproducto")
    public String listarProductos(@AuthenticationPrincipal User user, Model modelo) {
		String username = user.getUsername();
	    modelo.addAttribute("username", username);
	    
	    // Agregamos las notificaciones no leídas y el conteo
	    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
	    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
        modelo.addAttribute("objeto", this.objproductoservice.listarProductos());
        return "listarproducto"; 
    }
	
	 @GetMapping("/nuevoproducto")
	    public String nuevoproducto(@AuthenticationPrincipal User user, Model modelo) {
			String username = user.getUsername();
		    modelo.addAttribute("username", username);
		    
		    // Agregamos las notificaciones no leídas y el conteo
		    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
		    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());		 
		    modelo.addAttribute("categorias", objcategoriaservice.listarCategorias());
		 
		    return "nuevoproducto";
	    }
	 

	 
	 @PostMapping("/registrarproducto")
	 public String registrarproducto(@ModelAttribute("ProductoEntity") ProductoEntity objentidad, @RequestParam("imagen") MultipartFile imagen, Model model) {
	     try {
	         String uniqueFilename = objimagenservice.subir(imagen);
	         objentidad.setImagenruta(uniqueFilename);
	         this.objproductoservice.registrarProducto(objentidad);
	         return "redirect:/listarproducto";
	     } catch (DataIntegrityViolationException ex) {
	         model.addAttribute("errorMessage", "El nombre y/o código del producto ya existe. Por favor, modifique.");
	         model.addAttribute("categorias", objcategoriaservice.listarCategorias());
	         return "nuevoproducto";
	     } catch (Exception ex) {
	         model.addAttribute("errorMessage", "Ocurrió un error al registrar el producto. Por favor, intente nuevamente.");
	         model.addAttribute("categorias", objcategoriaservice.listarCategorias());
	         return "nuevoproducto";
	     }
	 }
	 
	 @GetMapping("/uploads/{filename:.+}")
	 public ResponseEntity<Resource> verImagen(@PathVariable String filename) {
	     try {
	         Resource recurso = objimagenservice.cargar(filename);
	         return ResponseEntity.ok()
	                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
	                 .body(recurso);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.notFound().build();
	     }
	 }


	 //
	 @GetMapping("/frmeditarproducto/{idproducto}")
	    public String frmeditarProducto(@PathVariable int idproducto, Model modelo, @AuthenticationPrincipal User user) {
			String username = user.getUsername();
		    modelo.addAttribute("username", username);
		    
		    // Agregamos las notificaciones no leídas y el conteo
		    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
		    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());	
		    
	        modelo.addAttribute("objcontrolador", objproductoservice.buscarProducto(idproducto));
	        modelo.addAttribute("categorias", objcategoriaservice.listarCategorias()); 
	        return "frmeditarproducto";
	    }
	    	 
	 @PostMapping("/editarproducto/{id}")
	    public String editarProducto(@PathVariable int id, @ModelAttribute("ProductoEntity") ProductoEntity objetoentidad, @RequestParam("imagen") MultipartFile imagen) {
	        try {
	            // Buscar el producto existente por ID
	            ProductoEntity objeditarproducto = objproductoservice.buscarProducto(id);

	            // Actualizar los campos del producto con los valores del objetoentidad
	            objeditarproducto.setCodigo(objetoentidad.getCodigo());
	            objeditarproducto.setNombre(objetoentidad.getNombre());
	            objeditarproducto.setCategoria(objetoentidad.getCategoria());
	            objeditarproducto.setPrecio(objetoentidad.getPrecio());
	            objeditarproducto.setCosto(objetoentidad.getCosto());
	            objeditarproducto.setStock(objetoentidad.getStock());
	            objeditarproducto.setObservacion(objetoentidad.getObservacion());

	            // Si se proporciona una nueva imagen, subirla y actualizar la ruta
	            if (!imagen.isEmpty()) {
	                String uniqueFilename = objimagenservice.subir(imagen);
	                objeditarproducto.setImagenruta(uniqueFilename);
	            }

	            // Guardar los cambios en el producto
	            objproductoservice.editarProducto(objeditarproducto);
	            return "redirect:/listarproducto";
	        } catch (Exception ex) {
	            return ex.getMessage();
	        }
	    }
 	    
	    @GetMapping("/eliminarproducto/{id}")
	    public String eliminarProducto(@PathVariable int id) {
	        try {
	        	objproductoservice.eliminarProducto(id);
	            return "redirect:/listarproducto";
	        } catch (Exception ex) {
	            return ex.getMessage();
	        }
	    } 
	    	    
	    @GetMapping("/verdetalleproducto/{idproducto}")
	    public String verDetalleProducto(@PathVariable("idproducto") int idproducto, Model modelo, @AuthenticationPrincipal User user) {
			String username = user.getUsername();
		    modelo.addAttribute("username", username);
		    
		    // Agregamos las notificaciones no leídas y el conteo
		    modelo.addAttribute("notificaciones", this.objnotificacionService.obtenerNotificacionesNoLeidas());
		    modelo.addAttribute("notificacionesNoLeidasCount", this.objnotificacionService.contarNotificacionesNoLeidas());
	        ProductoEntity objverproducto = objproductoservice.buscarProducto(idproducto);
	        modelo.addAttribute("producto", objverproducto);
	        return "detalleproducto";
	    }	    
}
