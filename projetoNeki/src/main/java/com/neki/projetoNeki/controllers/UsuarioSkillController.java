package com.neki.projetoNeki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.projetoNeki.model.UsuarioSkill;
import com.neki.projetoNeki.service.UsuarioSkillService;

@RestController
@RequestMapping("/api/usuario_skill")
@CrossOrigin
public class UsuarioSkillController {
	
	@Autowired
	private UsuarioSkillService usuarioSkillService;
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<UsuarioSkill> findByUsuarioId(@PathVariable Integer id){
		UsuarioSkill usuarioSkill = usuarioSkillService.findByUsuarioId(id);
		return new ResponseEntity<>(usuarioSkill, HttpStatus.OK);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<UsuarioSkill> saveUsuarioSkill(@RequestBody UsuarioSkill usuarioSkill) {
		UsuarioSkill novoUsuarioSkill = usuarioSkillService.save(usuarioSkill);
		return new ResponseEntity<>(novoUsuarioSkill, HttpStatus.CREATED);
	}
	
	@PutMapping("atualizar/{id}")
	public ResponseEntity<UsuarioSkill> updateUsuarioSkill(@RequestBody UsuarioSkill usuarioSkill) {
		UsuarioSkill atualizaUsuarioSkill = usuarioSkillService.update(usuarioSkill);
		return new ResponseEntity<>(atualizaUsuarioSkill, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEndereco(@PathVariable Integer id) throws Exception {
		if (usuarioSkillService.findById(id) == null) {
			throw new Exception("A usuarioSkill com o ID: " + id + " não existe.");
		} else {
			usuarioSkillService.deleteById(id);
			return new ResponseEntity<>("UsuarioSkill deletada com sucesso.", HttpStatus.OK);
		}
	}

}
