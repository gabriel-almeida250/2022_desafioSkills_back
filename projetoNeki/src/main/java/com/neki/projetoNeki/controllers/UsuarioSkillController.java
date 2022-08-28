package com.neki.projetoNeki.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.neki.projetoNeki.exceptions.NoSuchElementFoundException;
import com.neki.projetoNeki.model.UsuarioSkill;
import com.neki.projetoNeki.service.UsuarioSkillService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuario_skill")
@Validated
@CrossOrigin
@Tag(name = "UsuarioSkill", description = "Endpoints")
public class UsuarioSkillController {

	@Autowired
	private UsuarioSkillService usuarioSkillService;

	@Operation(operationId = "findUsuarioSkillByUsuarioId", summary = "Listar todos os UsuarioSkill pelo id do usuário", tags = {
			"UsuarioSkill" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "id", description = "UsuarioSkill Id") }, responses = {
							@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
							@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
							@ApiResponse(responseCode = "404", description = "UsuarioSkill não encontrado"),
							@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<UsuarioSkill> findUsuarioSkillByUsuarioId(@PathVariable Integer id) {
		UsuarioSkill usuarioSkill = usuarioSkillService.findByUsuarioId(id);
		if (usuarioSkill == null) {
			throw new NoSuchElementFoundException("Não existe nenhum UsuarioSkill com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(usuarioSkill, HttpStatus.OK);
		}
	}

	@Operation(operationId = "saveUsuarioSkill", summary = "Salvar UsuarioSkill", tags = {
			"UsuarioSkill" }, responses = {
					@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
					@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
					@ApiResponse(responseCode = "404", description = "UsuarioSkill não encontrado"),
					@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PostMapping("/salvar")
	public ResponseEntity<UsuarioSkill> saveUsuarioSkill(@Valid @RequestBody UsuarioSkill usuarioSkill) {
		UsuarioSkill novoUsuarioSkill = usuarioSkillService.save(usuarioSkill);
		return new ResponseEntity<>(novoUsuarioSkill, HttpStatus.CREATED);
	}

	@Operation(operationId = "updateUsuarioSkill", summary = "Atualizar UsuarioSkill", tags = {
			"UsuarioSkill" }, responses = {
					@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
					@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
					@ApiResponse(responseCode = "404", description = "UsuarioSkill não encontrado"),
					@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioSkill> updateUsuarioSkill(@Valid @RequestBody UsuarioSkill usuarioSkill) {
		UsuarioSkill atualizaUsuarioSkill = usuarioSkillService.update(usuarioSkill);
		return new ResponseEntity<>(atualizaUsuarioSkill, HttpStatus.OK);
	}

	@Operation(operationId = "deleteUsuarioSkill", summary = "Deletar UsuarioSkill pelo id", tags = {
			"UsuarioSkill" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "id", description = "UsuarioSkill Id") }, responses = {
							@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
							@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
							@ApiResponse(responseCode = "404", description = "UsuarioSkill não encontrado"),
							@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUsuarioSkill(@PathVariable Integer id) throws Exception {
		if (usuarioSkillService.findById(id) == null) {
			throw new NoSuchElementFoundException("O UsuarioSkill com o ID: " + id + " não existe.");
		} else {
			usuarioSkillService.deleteById(id);
			return new ResponseEntity<>("UsuarioSkill deletada com sucesso.", HttpStatus.OK);
		}
	}

}
