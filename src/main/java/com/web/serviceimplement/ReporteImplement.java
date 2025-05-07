package com.web.serviceimplement;



import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.web.model.CategoriaEntity;
import com.web.model.NotificacionEntity;
import com.web.model.ProductoEntity;
import com.web.model.ReporteEntity;
import com.web.model.ReporteEntradaDetalleEntity;
import com.web.model.ReporteEntradaEntity;
import com.web.model.ReporteInventarioEntity;
import com.web.model.ReporteSalidaDetalleEntity;
import com.web.model.ReporteSalidaEntity;
import com.web.repository.ReporteRepository;
import com.web.service.ReporteService;

@Service
public class ReporteImplement implements ReporteService {

	@Autowired
    private ReporteRepository repo;
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<ReporteEntity> listarReportes() {
		return repo.findAll();
	}

	@Override
	public void eliminarReporte(int idreporte) {
		repo.deleteById(idreporte);
		
	}
	
	@Override
    public ReporteEntity obtenerReportePorId(int idreporte) {
        return repo.findById(idreporte).orElse(null);
    }

	@Override
    public List<ProductoEntity> generarReporteTodosProductos() {
        String sql = "CALL spreportetodosproductos()";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductoEntity producto = new ProductoEntity();
            CategoriaEntity categoria = new CategoriaEntity();
            
            // Establece el ID de la categoría aquí
            categoria.setNombre(rs.getString("categoria"));
            
            producto.setIdproducto(rs.getInt("idproducto"));
            producto.setCodigo(rs.getString("codigo"));
            producto.setNombre(rs.getString("nombre"));
            producto.setCategoria(categoria); // Configura la categoría
            producto.setCosto(rs.getFloat("costo"));
            producto.setPrecio(rs.getFloat("precio"));
            producto.setStock(rs.getInt("stock"));
            producto.setObservacion(rs.getString("observacion"));
            
            return producto;
        });
    }

	@Override
	public List<ProductoEntity> generarReporteProductosPorStock(int stockMin, int stockMax) {
		String sql = "CALL spreporteproductosstock(?, ?)";
	    
	    // Usamos un RowMapper separado
	    return jdbcTemplate.query(sql, (rs, rowNum) -> {
	        ProductoEntity producto = new ProductoEntity();
	        CategoriaEntity categoria = new CategoriaEntity();
	        
	        // Obtenemos el nombre de la categoría aquí
	        categoria.setNombre(rs.getString("categoria"));
	        
	        producto.setIdproducto(rs.getInt("idproducto"));
	        producto.setCodigo(rs.getString("codigo"));
	        producto.setNombre(rs.getString("nombre"));
	        producto.setCategoria(categoria); 
	        producto.setCosto(rs.getFloat("costo"));
	        producto.setPrecio(rs.getFloat("precio"));
	        producto.setStock(rs.getInt("stock"));
	        producto.setObservacion(rs.getString("observacion"));
	        
	        return producto;
	    }, stockMin, stockMax); // Establece los parámetros aquí
	}

	@Override
	public List<NotificacionEntity> generarReporteNotificacionesPorFechas(Date fechainicio, Date fechafin) {
		String sql = "CALL spreportenotificacionesfechas(?, ?)";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            NotificacionEntity notificacion = new NotificacionEntity();
            notificacion.setIdnotificacion(rs.getInt("idnotificacion"));
            notificacion.setFecha(rs.getDate("fecha"));
            notificacion.setMensaje(rs.getString("mensaje"));
            notificacion.setLeida(rs.getBoolean("leida"));
            
            // obtenemos el nombre del producto aqui
            ProductoEntity producto = new ProductoEntity();
            producto.setNombre(rs.getString("producto"));
            notificacion.setProducto(producto);
            return notificacion;
            
        }, fechainicio, fechafin);
	}

	@Override
	public ReporteInventarioEntity generarReporteInventario(Integer filtrocategoria) {
		String sql = "CALL spreporteinventario(?)";
	    
		List<ProductoEntity> productos = jdbcTemplate.query(sql, (rs, rowNum) -> {
		    ProductoEntity producto = new ProductoEntity();
		    CategoriaEntity categoria = new CategoriaEntity();
		    
		    categoria.setNombre(rs.getString("categoria"));
		    
		    producto.setIdproducto(rs.getInt("idproducto"));
		    producto.setCodigo(rs.getString("codigo"));
		    producto.setNombre(rs.getString("nombre"));
		    producto.setCategoria(categoria); 
		    producto.setCosto(rs.getFloat("costo"));
		    producto.setPrecio(rs.getFloat("precio"));
		    producto.setStock(rs.getInt("stock"));
		    producto.setObservacion(rs.getString("observacion"));
		    
		    return producto;
		}, filtrocategoria);
			    
		ReporteInventarioEntity reporte = new ReporteInventarioEntity();
		reporte.setProductos(productos);
			    
		double totalCosto = 0;
		double totalVenta = 0;
		double costoTotalInventario = 0;
		double valorTotalInventario = 0;
		for (ProductoEntity producto : productos) {
		    totalCosto += producto.getCosto() * producto.getStock();
		    totalVenta += producto.getPrecio() * producto.getStock();
		    costoTotalInventario += producto.getCosto() * producto.getStock();
		    valorTotalInventario += producto.getPrecio() * producto.getStock();
		}
		reporte.setTotalCosto(totalCosto);
		reporte.setTotalVenta(totalVenta);
		reporte.setCostoTotalInventario(costoTotalInventario);
		reporte.setValorTotalInventario(valorTotalInventario);
			    
		return reporte;
	}

	@Override
	public List<ReporteEntradaEntity> generarReporteEntradasPorFechas(Date fechainicio, Date fechafin) {
		String sql = "CALL spreporteentradasfechas(?, ?)";
	    Map<Integer, ReporteEntradaEntity> reportes = new HashMap<>();
	    
	    jdbcTemplate.query(sql, (rs, rowNum) -> {
	        int identrada = rs.getInt("identrada");
	        ReporteEntradaEntity reporte = reportes.get(identrada);
	        if (reporte == null) {
	            reporte = new ReporteEntradaEntity();
	            reporte.setIdentrada(identrada);
	            reporte.setFecha(rs.getDate("fecha"));
	            reporte.setMotivo(rs.getString("motivo"));
	            reporte.setDetalles(new ArrayList<>());
	            reportes.put(identrada, reporte);
	        }
	        
	        ReporteEntradaDetalleEntity detalle = new ReporteEntradaDetalleEntity();
	        detalle.setIdentrada(rs.getInt("identrada"));
	        detalle.setIdproducto(rs.getInt("idproducto"));
	        detalle.setCodigo(rs.getString("codigo"));
	        detalle.setProducto(rs.getString("producto"));
	        detalle.setCantidad(rs.getInt("cantidad"));
	        detalle.setObservacion(rs.getString("observacion"));
	        
	        reporte.getDetalles().add(detalle);
	        return reporte;
	    }, fechainicio, fechafin);
	    
	    return new ArrayList<>(reportes.values());
	}

	@Override
	public List<ReporteSalidaEntity> generarReporteSalidasPorFechas(Date fechainicio, Date fechafin) {
		String sql = "CALL spreportesalidasfechas(?, ?)";
	    Map<Integer, ReporteSalidaEntity> reportes = new HashMap<>();
	    
	    jdbcTemplate.query(sql, (rs, rowNum) -> {
	        int idsalida = rs.getInt("idsalida");
	        ReporteSalidaEntity reporte = reportes.get(idsalida);
	        if (reporte == null) {
	            reporte = new ReporteSalidaEntity();
	            reporte.setIdsalida(idsalida);
	            reporte.setFecha(rs.getDate("fecha"));
	            reporte.setMotivo(rs.getString("motivo"));
	            reporte.setDetalles(new ArrayList<>());
	            reportes.put(idsalida, reporte);
	        }
	        
	        ReporteSalidaDetalleEntity detalle = new ReporteSalidaDetalleEntity();
	        detalle.setIdsalida(rs.getInt("idsalida"));
	        detalle.setIdproducto(rs.getInt("idproducto"));
	        detalle.setCodigo(rs.getString("codigo"));
	        detalle.setProducto(rs.getString("producto"));
	        detalle.setCantidad(rs.getInt("cantidad"));
	        detalle.setObservacion(rs.getString("observacion"));
	        
	        reporte.getDetalles().add(detalle);
	        return reporte;
	    }, fechainicio, fechafin);
	    
	    return new ArrayList<>(reportes.values());
	}
	
}
