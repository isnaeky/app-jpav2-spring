package com.isnaeky.demo.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.isnaeky.demo.app.models.entity.Cliente;

/*
 * Interface para crear los metodos que se implementaran
 * para crear un metodo abstracto solo se declara como si fuera un metodo pero son codigo dentro
 */
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{
	/*
	 * Cuando se hereda de CrudReository podemos crear nuestros metodos 
	 * y ya no se se implementa esta interfaz
	 */
	
	@Query("select c from Cliente c left join fetch c.facturas f where c.id = ?1")
	public Cliente fetchByIdWithFacturas(Long id);
	
}
