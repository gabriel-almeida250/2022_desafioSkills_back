package com.neki.projetoNeki.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.projetoNeki.model.Skill;
import com.neki.projetoNeki.model.Usuario;
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
	
	@GetMapping("/{id}")
    public ResponseEntity<Optional<Skill>> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(skillService.findById(id));
    }
	
	@PostMapping("/salvar")
	public ResponseEntity<Skill> saveSkill(@RequestBody Skill Skill) {
		Skill novoSkill = skillService.save(Skill);
		return new ResponseEntity<>(novoSkill, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Skill> updateSkill(@RequestBody Skill Skill) {
		Skill novoSkill = skillService.update(Skill);
		return new ResponseEntity<>(novoSkill, HttpStatus.CREATED);
	}
}
