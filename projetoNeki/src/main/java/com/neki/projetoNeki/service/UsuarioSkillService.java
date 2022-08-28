package com.neki.projetoNeki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki.projetoNeki.model.UsuarioSkill;
import com.neki.projetoNeki.repositories.UsuarioSkillRepository;

@Service
public class UsuarioSkillService {

	@Autowired
	private UsuarioSkillRepository usuarioSkillRepository;
	
	public UsuarioSkill findById(Integer id) {
		return usuarioSkillRepository.findById(id).isPresent() ? usuarioSkillRepository.findById(id).get() : null;
	}
	
	public UsuarioSkill findByUsuarioId(Integer id) {
		return usuarioSkillRepository.findByUsuarioId(id).isPresent() ? usuarioSkillRepository.findByUsuarioId(id).get() : null;
	}
	
	public UsuarioSkill save(UsuarioSkill usuarioSkill) {
		return usuarioSkillRepository.save(usuarioSkill);
	}
	
	public UsuarioSkill update(UsuarioSkill usuarioSkill) {
		return usuarioSkillRepository.save(usuarioSkill);
	}
	
	public void deleteById(Integer id) {
		 usuarioSkillRepository.deleteById(id);
	}

}