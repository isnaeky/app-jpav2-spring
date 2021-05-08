package com.isnaeky.demo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isnaeky.demo.app.models.dao.IClienteDao;
import com.isnaeky.demo.app.models.dao.IFacturaDao;
import com.isnaeky.demo.app.models.dao.IProductoDao;
import com.isnaeky.demo.app.models.entity.Cliente;
import com.isnaeky.demo.app.models.entity.Factura;
import com.isnaeky.demo.app.models.entity.Producto;

//Anotacion que trabaja en el patron de diseño facade, que solo es una fachada de nuestra verdadera implementacion 
@Service
public class ClienteServiceImpl implements IClienteService {

	/*
	 * Anotacion que inyecta una dependencia para utilizar el clienteDao que
	 * contiene todos los metodos del CRUD pero por buenas practicas se hace de esta
	 * manera
	 */
	@Autowired
	private IClienteDao clienteDao;

	@Autowired
	private IProductoDao productoDao;

	@Autowired
	private IFacturaDao facturaDao;

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

	@Override
	@Transactional(readOnly = true)
	public Cliente fetchByIdWithFacturas(Long id) {
		return clienteDao.fetchByIdWithFacturas(id);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}

	// Metodo para tener una paginacion con PagingAndSortingRepository
	@Transactional(readOnly = true)
	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombre(term);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Producto> findByNombreLikeIgnoreCase(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%" + term + "%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);

	}

	@Override
	@Transactional(readOnly = true)
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Factura fetchByIdWidthClienteWidthItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWidthClienteWidthItemFacturaWithProducto(id);
	}

}
