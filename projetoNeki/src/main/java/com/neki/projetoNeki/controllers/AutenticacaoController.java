package com.neki.projetoNeki.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.projetoNeki.Dto.TokenDTO;
import com.neki.projetoNeki.model.Usuario;
import com.neki.projetoNeki.service.TokenService;
import com.neki.projetoNeki.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/autenticacao")
@Validated
@CrossOrigin
@Tag(name = "Autenticação", description = "Endpoints")
public class AutenticacaoController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	TokenService tokenService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Operation(operationId = "auth", summary = "Fazer login", tags = { "Autenticação" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "Autenticação não encontrada"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PostMapping
	public ResponseEntity<TokenDTO> auth(@Valid @RequestBody Usuario usuario) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				usuario.getLogin(), usuario.getSenha());

		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		String token = tokenService.generateToken(authentication);
		TokenDTO tokenDTO = new TokenDTO("Bearer", token, usuario.getLogin(),
				passwordEncoder.encode(usuario.getSenha()));
		return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
	}

	@Operation(operationId = "registrar", summary = "Cadastrar Usuário", tags = { "Autenticação" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "Autenticação não encontrada"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PostMapping("/registro")
	public ResponseEntity<TokenDTO> registrar(@Valid @RequestBody Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		Usuario novoUsuario = usuarioService.save(usuario);

		String token = tokenService.generateTokenWithUserData(novoUsuario);
		TokenDTO tokenDTO = new TokenDTO("Bearer", token, usuario.getLogin(),
				passwordEncoder.encode(usuario.getSenha()));
		return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
	}

//	@PostMapping("/cadastro")
//	public ResponseEntity<TokenDTO> registrarSemFile(@RequestBody Usuario usuario){
//		Usuario novoUsuario = usuarioService.save(usuario);
//		
//		String token = tokenService.generateTokenWithUserData(novoUsuario);
//		TokenDTO tokenDTO = new TokenDTO("Bearer", token);
//		return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
//	}

//	@PostMapping("/recuperar-senha")
//	public ResponseEntity<TokenDTO> recuperarSenha(@RequestBody UsuarioRecuperacaoSenhaDTO usuarioSenhaDTO){
//		String encodedPass = passwordEncoder.encode(usuarioSenhaDTO.getSenha());
//		usuarioSenhaDTO.setSenha(encodedPass);
//
//        Usuario usuarioAtualizado = usuarioService.alteraSenha(usuarioSenhaDTO);
//		
//		String token = tokenService.generateTokenWithUserData(usuarioAtualizado);
//		TokenDTO tokenDTO = new TokenDTO("Bearer", token);
//		return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
//	}
//	
}