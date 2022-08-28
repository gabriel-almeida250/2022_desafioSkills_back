package com.neki.projetoNeki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki.projetoNeki.model.Skill;
import com.neki.projetoNeki.repositories.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	public List<Skill> findAll(){
		return skillRepository.findAll();
	} 
	
	public Skill findById(Integer id) {
		return skillRepository.findById(id).isPresent() ? skillRepository.findById(id).get() : null;
	}
	
	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}
	
	public Skill update(Skill skill) {
		return skillRepository.save(skill);
	}
	
	public void deleteById(Integer id) {
		skillRepository.deleteById(id);
	}
}
