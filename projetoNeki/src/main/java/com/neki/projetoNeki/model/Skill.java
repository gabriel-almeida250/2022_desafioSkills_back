package com.neki.projetoNeki.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Skill")
@Table(name = "skill")
public class Skill {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_skill", unique = true)
    private Integer idSkill;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "versao")
	private String versao;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image_url")
	private String imageUrl;

	@JsonIgnore
	@OneToMany(
	        mappedBy = "skill",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	    private Set<UsuarioSkill> usuarioSkillModel;
	
	public Integer getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(Integer idSkill) {
		this.idSkill = idSkill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<UsuarioSkill> getUsuarioSkillModel() {
		return usuarioSkillModel;
	}

	public void setUsuarioSkillModel(Set<UsuarioSkill> usuarioSkillModel) {
		this.usuarioSkillModel = usuarioSkillModel;
	}
	
}
