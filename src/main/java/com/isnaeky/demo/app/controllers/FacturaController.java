package com.isnaeky.demo.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isnaeky.demo.app.models.entity.Cliente;
import com.isnaeky.demo.app.models.entity.Factura;
import com.isnaeky.demo.app.models.entity.ItemFactura;
import com.isnaeky.demo.app.models.entity.Producto;
import com.isnaeky.demo.app.models.service.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private IClienteService clienteService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {

		Factura factura = clienteService.fetchByIdWidthClienteWidthItemFacturaWithProducto(id); // clienteService.findFacturaById(id)
		if (factura == null) {
			flash.addFlashAttribute("error", "No exite la factura en BDs");
			return "redirect:/listar";
		}

		model.addAttribute("factura", factura);
		model.addAttribute("titulo", "Factura: ".concat(factura.getDescripcion()));
		return "factura/ver";
	}

	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable Long clienteId, Model model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(clienteId);

		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la BDs");
			return "redirect:/listar";
		}

		Factura factura = new Factura();
		factura.setCliente(cliente);

		model.addAttribute("factura", factura);
		model.addAttribute("titulo", "Crear factura");
		return "factura/form";
	}

	/*
	 * Metodo para retornar un microservicio Rest
	 * 
	 * Para retornar un json
	 * 
	 * @GetMapping(value="/cargar-productos/{term}",produces= {"application/json"})
	 * 
	 * Anotacion para convertir el servicio en JSON
	 * 
	 * @ResponseBody
	 */
	@GetMapping(value = "/cargar-productos/{term}", produces = { "application/json" })
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		// return clienteService.findByNombre(term);
		return clienteService.findByNombreLikeIgnoreCase(term);
	}

	@PostMapping("/form")
	public String guardar(@Valid Factura factura, BindingResult result,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear factura");
			logger.error("Validacion fallida");
			return "factura/form";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Crear factura");
			model.addAttribute("error", "Error: La factura debe de tener lineas");
			return "factura/form";
		}

		for (int i = 0; i < itemId.length; i++) {
			Producto producto = clienteService.findProductoById(itemId[i]);

			ItemFactura linea = new ItemFactura();

			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);

			factura.addItemFactura(linea);

			logger.info("Id: " + itemId[i].toString() + ", Cantidad: " + cantidad[i].toString());

		}

		clienteService.saveFactura(factura);
		status.setComplete();
		flash.addFlashAttribute("success", "Factura creada con exito");
		return "redirect:/ver/" + factura.getCliente().getId();
	}

	@GetMapping("eliminar/{id}")
	public String delete(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Factura factura = clienteService.findFacturaById(id);
		if (factura == null) {
			flash.addFlashAttribute("error", "La factura no existe en la BDs");
			return "redirect:/listar";
		}

		clienteService.deleteFactura(id);
		flash.addFlashAttribute("success", "Factura eliminada con exito");
		return "redirect:/ver/" + factura.getCliente().getId();
	}

}
