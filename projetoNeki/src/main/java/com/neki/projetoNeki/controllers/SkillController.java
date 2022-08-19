package com.neki.projetoNeki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.projetoNeki.model.Skill;
import com.neki.projetoNeki.service.SkillService;

@RestController
@RequestMapping("/api/skill")
@CrossOrigin
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	@GetMapping("/listarTodos")
	public ResponseEntity<List<Skill>> findAllFornecedor() {
		List<Skill> skillList = skillService.findAll();
		return new ResponseEntity<>(skillList, HttpStatus.OK);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Skill> saveSkill(@RequestBody Skill Skill) {
		Skill novoSkill = skillService.save(Skill);
		return new ResponseEntity<>(novoSkill, HttpStatus.CREATED);
	}
}
