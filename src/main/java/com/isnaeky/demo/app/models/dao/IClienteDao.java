package com.isnaeky.demo.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.isnaeky.demo.app.models.entity.Cliente;

/*
 * Interface para crear los metodos que se implementaran
 * para crear un metodo abstracto solo se declara como si fuera un metodo pero son codigo dentro
 */
public interface IClienteDao extends CrudRepository<Cliente, Long>{
	/*
	 * Cuando se hereda de CrudReository podemos crear nuestros metodos 
	 * y ya no se se implementa esta interfaz
	 */
	
}
