package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.web.model.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
	
	@Query("SELECT p FROM ProductoEntity p WHERE p.categoria.idcategoria = :idcategoria")
    List<ProductoEntity> findByCategoriaId(@Param("idcategoria") int idcategoria);
	
	//consulta para buscar productos por nombre al momento de registrarentrada
	//se utiliza una LIKEcláusula para buscar productos cuyo nombre contenga el nombreparámetro especificado. 
	//La función LOWER se utiliza para que la búsqueda no distinga entre mayúsculas y minúsculas.
	//Lo podemos utilizar para los filtros en el inventario - 3cer sprint
	@Query("SELECT p FROM ProductoEntity p WHERE LOWER(p.nombre) LIKE %:nombre%")
	List<ProductoEntity> buscarProductosPorNombre(@Param("nombre") String nombre);


}
