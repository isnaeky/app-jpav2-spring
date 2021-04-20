package com.isnaeky.demo.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.isnaeky.demo.app.models.entity.Cliente;
import com.isnaeky.demo.app.models.service.IClienteService;
import org.apache.logging.log4j.Logger;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;

//Anotacion para marcar la clase como controlador
@Controller
//anotacion guarda en los atributos de la sesion el cliente, pero se tiene que eliminar en el metodo cuando se guarda
@SessionAttributes("cliente")
public class ClienteController {

	Logger logger = LogManager.getLogger(ClienteController.class);

	// Tenemos que obtener siempre de la interface no de la clase donde se
	// implementa
	// Anotacion para inyectar una dependencia
	@Autowired
	// Anotacion para indicar que implementacion va a tomar nuestra dependencia, se
	// tiene que indicar el nombre tambien en la implementacion
	// Es cuando tenemos mas de una implementacion
	/*@Qualifier("clienteDaoJPA")
	private IClienteDao clienteDao;*/
	
	
	/*
	 * Implementamos la capa service que es un patron de diseño facade o fachada se deja a un lado el Dao lo implementa JEE es buena practica
	 */
	private IClienteService clienteService;

	// Anotacion para indicar un metodo GET o ruta
	@GetMapping({ "/index", "/", "" })
	public String index(Model model) {
		model.addAttribute("titulo", "Home");
		return "index";
	}

	// Anotacion para indicar un metodo GET o ruta
	@GetMapping({ "/listar", "/list" })
	public String listar(Model model) {
		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("titulo", "Listado de clientes");
		return "listar";
	}

	// Ruta que muestra la vista del formulario para actualizar
	@GetMapping("/form/{id}")
	public String update(@PathVariable Long id, Model model) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
		} else {
			return "redirect:/listar";
		}
		model.addAttribute("titulo", "Editar cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("btnTitle", "Editar");

		return "cliente";
	}

	// Ruta que muestra la vista del formulario
	@GetMapping("/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo", "Formulario de cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("btnTitle", "Crear cliente");
		return "cliente";
	}

	// Ruta que guarda nuestro formulario en BD a traves de Entity
	@PostMapping("/form")
	// Anotacion @Valid para validar el entity cliente se importa de
	// javax.validation

	// BindingResult es para obtener informacion del entity, tiene que ir a lado de
	// nuestro entity
	// Anotacion @ModelAttribute en dado caso que el objeto que se llamo no es el
	// mismo que se declara aqui
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			model.addAttribute("btnTitle", "Crear cliente");
			logger.error("Validacion fallida");
			return "cliente";
		}

		logger.warn("No hay errores");
		clienteService.save(cliente);
		// elimina el cliente de la sesion declarado en la clase con la anotacion
		// @SessionAttributes("cliente")
		status.setComplete();
		// Despues de guardar redirigimos a /listar
		return "redirect:/listar";
	}

	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable Long id) {
		if (id > 0) {
			clienteService.delete(id);
		}
		return "redirect:/listar";
	}

}
