package com.idtolu.sesion.service;

import java.util.List;
import java.util.Optional;

import com.idtolu.sesion.model.Usuario;

public interface UsuarioService {

	public Usuario addUser(Usuario user);
	
	public List<Usuario> getUsers();
	
	public Optional<Usuario> getUserByUsuario(Usuario usuario);
	
	public Optional<Usuario> getUserByIdenteficacion(String identificacion);
}
