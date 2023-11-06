package com.idtolu.sesion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.idtolu.sesion.model.Usuario;
import com.idtolu.sesion.repository.UsuarioRepository;

@Service
public class UserSecurityService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repositorio.findByUsuario(username)
				.orElseThrow(()-> new UsernameNotFoundException("User "+ username + " not found"));
		
		return User.builder()
				.username(user.getUsuario())
				.password(user.getClave())
				.roles(user.getRol())
				.build();
		
	}

}
