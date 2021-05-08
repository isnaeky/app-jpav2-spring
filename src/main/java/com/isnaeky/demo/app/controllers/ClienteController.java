package com.isnaeky.demo.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isnaeky.demo.app.models.entity.Cliente;
import com.isnaeky.demo.app.models.service.IClienteService;
import com.isnaeky.demo.app.models.service.IUploadFileService;
import com.isnaeky.demo.app.util.paginator.PageRender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.validation.Valid;

//Anotacion para marcar la clase como controlador
@Controller
//anotacion guarda en los atributos de la sesion el cliente, pero se tiene que eliminar en el metodo cuando se guarda
@SessionAttributes("cliente")
public class ClienteController {

	// Tenemos que obtener siempre de la interface no de la clase donde se
	// implementa
	// Anotacion para inyectar una dependencia
	@Autowired
	// Anotacion para indicar que implementacion va a tomar nuestra dependencia, se
	// tiene que indicar el nombre tambien en la implementacion
	// Es cuando tenemos mas de una implementacion
	/*
	 * @Qualifier("clienteDaoJPA") private IClienteDao clienteDao;
	 */

	/*
	 * Implementamos la capa service que es un patron de diseño facade o fachada se
	 * deja a un lado el Dao lo implementa JEE es buena practica
	 */
	private IClienteService clienteService;

	/*
	 * inyeccion de nuestro recurso que hace un CRUD en el service 
	 */
	@Autowired
	private IUploadFileService uploadFileService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * Metodo que carga las imagenes de nuestro directorio ya creado sin tener que cargar 
	 * en las configuraciones de nuestra aplicacion
	 * 
	 * {filename:.+} expresion para obtener el archivo es una expresion regular 
	 */
	@GetMapping("/uploads/{filename:.+}")
	//ResponseEntity<Resource> retorna un response entity 
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			//obtiene un recurso con uploadFileService.load(filename); y lo guarda en una variable
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		//Retorna un response entity
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);

	}

	/*
	 * obtiene un cliente desde la BDs y lo retorna a la vista 
	 */
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable long id, Model model, RedirectAttributes flash) {
		//Obtiene un cliente por id desde la inyeccion de la dependencia 
		Cliente cliente = clienteService.fetchByIdWithFacturas(id); //clienteService.findOne(id);
		//Si el cliente es nulo retorna a la lista y manda un mesaje flash
		if (cliente == null) {
			flash.addFlashAttribute("error", "Cliente no encontrado en la BD");
			return "redirect:/listar";
		}
		//Si encuentra el cliente lo retorna 
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Detalle cliente: " + cliente.getNombre());
		
		return "ver";
	}

	// Anotacion para indicar un metodo GET o ruta
	@GetMapping({ "/index", "/", "" })
	public String index(Model model) {
		model.addAttribute("titulo", "Inicio JPA");
		return "index";
	}

	// Anotacion para indicar un metodo GET o ruta
	@GetMapping({ "/listar", "/list" })
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		// Para nuestra paginacion obtiene la page y especificamos la cantidad de registros que deseamos mostrar
		Pageable pageRequest = PageRequest.of(page, 4);
		//Se obtiene todos los clientes dependiendo los clientes
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

		model.addAttribute("clientes", clientes);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("page", pageRender);
		return "listar";
	}

	// Ruta que muestra la vista del formulario para actualizar
	@GetMapping("/form/{id}")
	public String update(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "Cliente no encontrado en la BD");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "Cliente no puede ser 0");
			return "redirect:/listar";
		}
		model.addAttribute("titulo", "Editar cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("btnTitle", "Editar");
		flash.addFlashAttribute("success", "Cliente actualizado correctamente");
		return "cliente";
	}

	// Ruta que muestra la vista del formulario
	@GetMapping("/form")
	public String crear(Model model) {
		//Para un form hay que mandar tambien nuestro objeto que guardara el form en dado caso que exista un error lo retorna spring 
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
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			model.addAttribute("btnTitle", "Crear cliente");
			logger.error("Validacion fallida");
			return "cliente";
		}
		// Codigo para subir la foto al directorio
		if (!foto.isEmpty()) {

			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {
				//Elimina una foto con el nombre que obtenemos para actualizarla
				uploadFileService.delete(cliente.getFoto());
			}

			String uniqueFileName = null;
			try {
				//Agregamos/subimos una foto nueva una foto 
				uniqueFileName = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Archivo " + uniqueFileName + " subido exitosamente");
			cliente.setFoto(uniqueFileName);
		}

		String mensajeFlash = "";
		if (cliente.getId() != null) {
			mensajeFlash = "Cliente editado con exito";
		} else {
			mensajeFlash = "Cliente creado con exito";
		}

		logger.warn("No hay errores");
		clienteService.save(cliente);
		// elimina el cliente de la sesion declarado en la clase con la anotacion
		// @SessionAttributes("cliente")
		status.setComplete();
		// Mensajes flash solo duran una peticion
		flash.addFlashAttribute("success", mensajeFlash);
		// Despues de guardar redirigimos a /listar
		return "redirect:/listar";
	}

	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes flash) {
		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);

			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con exito");

			if (uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", "Foto: " + cliente.getFoto() + " eliminado con exito");
			}

		}
		return "redirect:/listar";
	}

}
