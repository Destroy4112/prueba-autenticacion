package com.idtolu.sesion.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.idtolu.sesion.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	public Optional<Usuario> findByUsuario(String usuario);
	
	public Optional<Usuario> findByIdentificacion(String identificacion);	

}
