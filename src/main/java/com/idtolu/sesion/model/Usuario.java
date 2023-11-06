package com.idtolu.sesion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario {

	@Id
	private String id;
	private String nombres;
	private String apellidos;
	private String identificacion;
	private String usuario;
	private String clave;
	private String rol;

	public Usuario() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "{\"id\": \"" + id + "\", \"nombres\": \"" + nombres + "\", \"apellidos\": \"" + apellidos
				+ "\", \"identificacion\": \"" + identificacion + "\", \"usuario\": \"" + usuario + "\", \"clave\": \""
				+ clave + "\", \"rol\": \"" + rol + ",\"}";
	}
}
