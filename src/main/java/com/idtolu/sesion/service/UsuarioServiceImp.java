package com.idtolu.sesion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idtolu.sesion.config.SecurityConfig;
import com.idtolu.sesion.model.Usuario;
import com.idtolu.sesion.repository.UsuarioRepository;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioRepository repositorio;
	@Autowired
	private SecurityConfig config;

	@Override
	public Usuario addUser(Usuario user) {
		if (user.getId() == null) {
			Optional<Usuario> busquedaIdentificacion = repositorio.findByIdentificacion(user.getIdentificacion());
			Optional<Usuario> busquedaUsuario = repositorio.findByUsuario(user.getUsuario());
			if (busquedaIdentificacion.isPresent() || busquedaUsuario.isPresent()) {
				user.setId("Existe");
				return user;
			}
		}
		user.setClave(config.passwordEncoder().encode(user.getClave()));
		return repositorio.save(user);
	}

	@Override
	public List<Usuario> getUsers() {
		return repositorio.findAll();
	}

	@Override
	public Optional<Usuario> getUserByUsuario(Usuario usuario) {
		Optional<Usuario> user = repositorio.findByUsuario(usuario.getUsuario());
		if (!user.isEmpty()) {
			boolean resultado = config.passwordEncoder().matches(usuario.getClave(), user.get().getClave());
			if (resultado) {
				return user;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public Optional<Usuario> getUserByIdenteficacion(String identificacion) {
		Optional<Usuario> usuario = repositorio.findByIdentificacion(identificacion);
		return usuario;
	}

}
