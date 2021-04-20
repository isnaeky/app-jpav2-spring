package com.isnaeky.demo.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

//Anotacion para decirle a nuestra clase que es un entity pero la importacion es de javax no de spring 
@Entity
//Anotacion en dado caso que nuestra tabla este de diferente nombre en nuestra base de datos 
@Table(name = "clientes")
/*
 * Se recomienda para trabajar en serializacion Se tiene que implementar la
 * serializacion de nuestra clase entity, tambien se tiene que generar un id
 * automatico
 */
public class Cliente implements Serializable {

	/**
	 * Variable para el id unico para la clase por la serializacion
	 */
	private static final long serialVersionUID = 1L;

	// Anotacion que indica es un id, se importa desde javax no spring
	@Id
	// Anotacion para generar el id de que forma
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*
	 * Anotacion para validar expresiones regulares
	 * 
	 * @Pattern(regexp = "[A-Z0-9]+",
	 * message="El código del producto solo puede tener letras mayúsculas o números"
	 * )
	 */
	/*
	 * Anotacion para los campos String para que no sean vacios al menos un
	 * caracter, se agrega hibernete validation pero se importa de javax.validation
	 * Asignar el mensaje personalizado con message
	 */
	@NotEmpty(message = "El nombre no debe estar vacio")
	// Anotacion para poner un rango de caracteres se importa de javax.validation
	// @Size(min=2,max = 4)
	private String nombre;

	// Anotacion para los campos String para que no sean vacios al menos un
	// caracter, se agrega hibernete validation pero se importa de javax.validation
	@NotEmpty(message = "El apellido no debe estar vacio")
	private String apellido;

	// Anotacion para los campos String para que no sean vacios al menos un
	// caracter, se agrega hibernete validation pero se importa de javax.validation
	@NotEmpty(message = "El correo no debe estar vacio")
	// anotacion para validar el campo email como email de javax
	@Email(message = "El correo no es valido")
	private String email;

	// Anotacion para que la fecha no sea nula menos para String de javax
	@NotNull
	// Importar el "Date" de java.util no de java.sql
	// Anotacion en dado caso que nuestro campo se llama diferente en la BD
	@Column(name = "create_at")
	// Anotacion para indicar el formato en que se va a guardar la fecha
	@Temporal(TemporalType.DATE)
	// Anotacion para cambiar el formato de la fecha
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	// Anotacion para ejecutar el metodo antes de la persistencia, justo antes de
	// insertar los datos a la BD
	/*
	 * @PrePersist public void prePersist() { createAt= new Date(); }
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
