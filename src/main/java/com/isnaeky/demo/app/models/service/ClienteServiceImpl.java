package com.isnaeky.demo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isnaeky.demo.app.models.dao.IClienteDao;
import com.isnaeky.demo.app.models.entity.Cliente;

//Anotacion que trabaja en el patron de diseño facade, que solo es una fachada de nuestra verdadera implementacion 
@Service
public class ClienteServiceImpl implements IClienteService{

	//Anotacion que inyecta una dependencia 
	@Autowired
	private IClienteDao clienteDao;
	
	// Anotacion que indica que el metodo sea transaccional y con readOnly sea de
	// lectura, anotacion de spring
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}
	
	
	// Anotacion que indica que el metodo sea transaccional sin readonly ya que es
	// de escritura la transaccion
	@Transactional
	@Override
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteDao.save(cliente);
	}
	@Transactional(readOnly = true)
	@Override
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}

}
