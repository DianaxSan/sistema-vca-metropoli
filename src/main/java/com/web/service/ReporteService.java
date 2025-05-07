package com.web.service;

import java.util.List;
import com.web.model.NotificacionEntity;
import com.web.model.ProductoEntity;
import com.web.model.ReporteEntity;
import com.web.model.ReporteEntradaEntity;
import com.web.model.ReporteInventarioEntity;
import com.web.model.ReporteSalidaEntity;

public interface ReporteService {
	
	List<ReporteEntity> listarReportes();
    void eliminarReporte(int idreporte);
    ReporteEntity obtenerReportePorId(int idreporte);
    
 // Métodos para generar reportes específicos
    public List<ProductoEntity> generarReporteTodosProductos();
    public List<ProductoEntity> generarReporteProductosPorStock(int stockMin, int stockMax);
    public List<NotificacionEntity> generarReporteNotificacionesPorFechas(java.sql.Date fechainicio, java.sql.Date fechafin);    
    public ReporteInventarioEntity generarReporteInventario(Integer filtrocategoria);    
    public List<ReporteEntradaEntity> generarReporteEntradasPorFechas(java.sql.Date fechainicio, java.sql.Date fechafin);
    public List<ReporteSalidaEntity> generarReporteSalidasPorFechas(java.sql.Date fechainicio, java.sql.Date fechafin);
    
}
