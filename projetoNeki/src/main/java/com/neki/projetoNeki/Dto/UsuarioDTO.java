package com.neki.projetoNeki.Dto;

import java.util.List;

public class UsuarioDTO {

	private Integer id;

	List<UsuarioSkillDTO> usuarioSkill;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<UsuarioSkillDTO> getUsuarioSkill() {
		return usuarioSkill;
	}

	public void setUsuarioSkill(List<UsuarioSkillDTO> usuarioSkill) {
		this.usuarioSkill = usuarioSkill;
	}
	
	
}
