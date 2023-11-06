package com.idtolu.sesion.controller;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idtolu.sesion.model.Usuario;
import com.idtolu.sesion.service.UsuarioService;
import com.idtolu.sesion.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsuarioService servicio;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody Usuario user) {

		Optional<Usuario> resultado = servicio.getUserByUsuario(user);

		if (resultado != null) {
			UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(user.getUsuario(),
					user.getClave());
			Authentication authentication = this.authenticationManager.authenticate(login);

			System.out.println(authentication.isAuthenticated());

			String token = jwtUtil.generarToken(user.getUsuario());
			return ResponseEntity.ok(new JSONObject().put("token", token).put("usuario", resultado.get()).toString());
		}
		return ResponseEntity.ok(new JSONObject().put("token", "0").toString());
	}

	@PostMapping("/register")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario user) {
		Usuario resultado = servicio.addUser(user);
		if (resultado != null) {
			if (resultado.getId() != "Existe") {
				return ResponseEntity.ok("registrado");
			}
			return ResponseEntity.ok("error");
		}
		return ResponseEntity.ok("error");
	}

}
