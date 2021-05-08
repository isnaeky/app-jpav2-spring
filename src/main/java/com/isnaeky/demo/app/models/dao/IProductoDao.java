package com.isnaeky.demo.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isnaeky.demo.app.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {
	
	// La consulta es a nivel de objetos
	@Query("select p from Producto p where p.nombre like %?1% ")
	public List<Producto> findByNombre(String term);

	// Consulta basada en el nombre del metodo sin utilizar @Query
	public List<Producto> findByNombreLikeIgnoreCase(String term);

}
