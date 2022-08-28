package com.neki.projetoNeki.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.projetoNeki.exceptions.EmptyListException;
import com.neki.projetoNeki.exceptions.NoSuchElementFoundException;
import com.neki.projetoNeki.model.Usuario;
import com.neki.projetoNeki.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuario")
@Validated
@CrossOrigin
@Tag(name = "Usuário", description = "Endpoints")
public class UsuarioController {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UsuarioService service;

	@Operation(operationId = "findAllUsuario", summary = "Listar todos os Usuários", tags = { "Usuário" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@GetMapping("/listarTodos")
	public ResponseEntity<List<Usuario>> findAllUsuario() {
		List<Usuario> usuarioList = service.findAll();
		if (usuarioList.isEmpty()) {
			throw new EmptyListException("A lista de usuarios está vazia.");
		} else {
			return new ResponseEntity<>(usuarioList, HttpStatus.OK);
		}
	}

	@Operation(operationId = "findByIdUsuario", summary = "Encontrar o usuário pelo id", tags = {
			"Usuário" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "id", description = "Usuario Id") }, responses = {
							@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
							@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
							@ApiResponse(responseCode = "404", description = "Usuario não encontrado"),
							@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findByIdUsuario(@PathVariable Integer id) {
		Usuario usuario = service.findById(id);
		if (usuario == null) {
			throw new NoSuchElementFoundException("Não existe nenhum usuario com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}
	}

	@Operation(operationId = "findByLogin", summary = "Encontrar os usuários pelo login", tags = {
			"Usuário" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "login", description = "Usuario Login") }, responses = {
							@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
							@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
							@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
							@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@GetMapping("usuarioAchado/{login}")
	public ResponseEntity<Usuario> findByLogin(@PathVariable String login) {
		Usuario usuario = service.findByLogin(login);
		if (usuario == null) {
			throw new NoSuchElementFoundException("Não existe nenhum usuario com o login: " + login + ".");
		} else {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}
	}

	@Operation(operationId = "saveUsuario", summary = "Salvar Usuário", tags = { "Usuário" }, responses = {
					@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
					@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
					@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
					@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PostMapping("/salvar")
	public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		Usuario novoUsuario = service.save(usuario);
		return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
	}
	
	@Operation(operationId = "updateUsuario", summary = "Atualizar Usuário", tags = { "Usuário" }, responses = {
					@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
					@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
					@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
					@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		Usuario usuarioAtualizado = service.update(usuario);
		return new ResponseEntity<>(usuarioAtualizado, HttpStatus.CREATED);
	}

	@Operation(operationId = "deleteUsuario", summary = "Deletar Usuário pelo id", tags = { "Usuário" }, parameters = {
			@Parameter(in = ParameterIn.PATH, name = "id", description = "Usuario Id") }, responses = {
					@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
					@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
					@ApiResponse(responseCode = "404", description = "Usuario não encontrado"),
					@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) throws Exception {
		if (service.findById(id) == null) {
			throw new NoSuchElementFoundException("O Usuario com o ID: " + id + " não existe.");
		} else {
			service.deleteById(id);
			return new ResponseEntity<>("Usuario deletado com sucesso.", HttpStatus.OK);
		}
	}

//    @GetMapping("/validarSenha")
//    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
//                                                @RequestParam String senha) {
//
//        Optional<Usuario> optUsuario = repository.findByLogin(login);
//        if (optUsuario.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
//        }
//
//        Usuario usuario = optUsuario.get();
//        boolean valid = encoder.matches(senha, usuario.getSenha());
//
//        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
//        return ResponseEntity.status(status).body(valid);
//
//    }

}
