package com.idtolu.sesion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idtolu.sesion.model.Usuario;
import com.idtolu.sesion.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService servicio;
	
	@GetMapping
	public ResponseEntity<?> consultarUsuarios(){
		List<Usuario> resultado = servicio.getUsers();
		return ResponseEntity.ok(resultado);
	}
	
	@GetMapping("/{identificacion}")
	public ResponseEntity<?> consultarUsuarioPorIdentificacion(@PathVariable String identificacion){
		Optional<Usuario> resultado = servicio.getUserByIdenteficacion(identificacion);
		if (resultado.isPresent()) {
			return ResponseEntity.ok(resultado);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("nada");
	}

}
