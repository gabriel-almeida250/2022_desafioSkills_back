package com.neki.projetoNeki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.projetoNeki.model.UsuarioSkill;
import com.neki.projetoNeki.service.UsuarioSkillService;

@RestController
@RequestMapping("/api/usuario_skill")
public class UsuarioSkillController {
	
	@Autowired
	private UsuarioSkillService usuarioSkillService;
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<UsuarioSkill> findByUsuarioId(@PathVariable Integer id) throws Exception{
		UsuarioSkill usuarioSkill = usuarioSkillService.findByUsuarioId(id);
		if (usuarioSkill == null) {
			throw new Exception("Não existe nenhum colaborador com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(usuarioSkill, HttpStatus.OK);
		}
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<UsuarioSkill> saveUsuarioSkill(@RequestBody UsuarioSkill usuarioSkill) {
		UsuarioSkill novoUsuarioSkill = usuarioSkillService.save(usuarioSkill);
		return new ResponseEntity<>(novoUsuarioSkill, HttpStatus.CREATED);
	}

}
