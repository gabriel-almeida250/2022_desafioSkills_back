package com.neki.projetoNeki.controllers;

import java.util.List;

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

import com.neki.projetoNeki.exceptions.EmptyListException;
import com.neki.projetoNeki.exceptions.NoSuchElementFoundException;
import com.neki.projetoNeki.model.Skill;
import com.neki.projetoNeki.service.SkillService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/skill")
@Validated
@CrossOrigin
@Tag(name = "Skill", description = "Endpoints")
public class SkillController {

	@Autowired
	private SkillService skillService;

	@Operation(operationId = "findAllSkill", summary = "Listar todas as Skills", tags = { "Skill" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "Skill não encontrada"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@GetMapping("/listarTodos")
	public ResponseEntity<List<Skill>> findAllSkill() {
		List<Skill> skillList = skillService.findAll();
		if (skillList.isEmpty()) {
			throw new EmptyListException("A lista de skills está vazia.");
		} else {
			return new ResponseEntity<>(skillList, HttpStatus.OK);
		}
	}

	@Operation(operationId = "findByIdSkill", summary = "Encontrar a skill pelo id", tags = { "Skill" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "Skill não encontrada"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@GetMapping("/{id}")
	public ResponseEntity<Skill> findByIdSkill(@PathVariable Integer id) {
		Skill skill = skillService.findById(id);
		if (skill == null) {
			throw new NoSuchElementFoundException("Não existe nenhuma skill com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(skill, HttpStatus.OK);
		}
	}

	@Operation(operationId = "saveSkill", summary = "Salvar a skill", tags = { "Skill" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "Skill não encontrada"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PostMapping("/salvar")
	public ResponseEntity<Skill> saveSkill(@Valid @RequestBody Skill skill) {
		Skill novoSkill = skillService.save(skill);
		return new ResponseEntity<>(novoSkill, HttpStatus.CREATED);
	}

	@Operation(operationId = "updateSkill", summary = "Atualizar a skill", tags = { "Skill" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "Skill não encontrada"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PutMapping("/update")
	public ResponseEntity<Skill> updateSkill(@Valid @RequestBody Skill skill) {
		Skill novoSkill = skillService.update(skill);
		return new ResponseEntity<>(novoSkill, HttpStatus.CREATED);
	}
	
	@Operation(operationId = "deleteSkill", summary = "Deletar Skill pelo id", tags = { "Skill" }, parameters = {
			@Parameter(in = ParameterIn.PATH, name = "id", description = "Skill Id") }, responses = {
					@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class))),
					@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
					@ApiResponse(responseCode = "404", description = "Skill não encontrada"),
					@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSkill(@PathVariable Integer id) throws Exception {
		if (skillService.findById(id) == null) {
			throw new NoSuchElementFoundException("A Skill com o ID: " + id + " não existe.");
		} else {
			skillService.deleteById(id);
			return new ResponseEntity<>("Skill deletada com sucesso.", HttpStatus.OK);
		}
	}
}
