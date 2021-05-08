package com.isnaeky.demo.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isnaeky.demo.app.models.entity.Cliente;
import com.isnaeky.demo.app.models.service.IClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/listar")
	public List<Cliente> listar() {
		return clienteService.findAll();
	}

	@GetMapping("/buscar/{id}")
	public Cliente buscarOneCliente(@PathVariable long id) {
		return clienteService.findOne(id);

	}
	
	@PutMapping("/actualizar")
	public String actualizar(@Valid Cliente cliente) {
		
		if (cliente != null) {
			clienteService.save(cliente);
		}else {
			return "Cliente vacio";
		}
		return "Cliente actualizado correctamente";
		
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable long id) {
		clienteService.delete(id);
		//return "redirect:/listar";
		return "Cliente eliminado: "+id;
	}

}
