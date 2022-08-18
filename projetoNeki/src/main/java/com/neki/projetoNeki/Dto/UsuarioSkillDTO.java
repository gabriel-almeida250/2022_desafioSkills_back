package com.neki.projetoNeki.Dto;

import com.neki.projetoNeki.model.Skill;

public class UsuarioSkillDTO {

    private Integer UsuarioSkillId;
 
    private SkillDTO skill;
    
    private Integer knowledgeLevel;

	public Integer getUsuarioSkillId() {
		return UsuarioSkillId;
	}

	public void setUsuarioSkillId(Integer usuarioSkillId) {
		UsuarioSkillId = usuarioSkillId;
	}

	public SkillDTO getSkill() {
		return skill;
	}

	public void setSkill(SkillDTO skill) {
		this.skill = skill;
	}

	public Integer getKnowledgeLevel() {
		return knowledgeLevel;
	}

	public void setKnowledgeLevel(Integer knowledgeLevel) {
		this.knowledgeLevel = knowledgeLevel;
	}
    
    
 
}
