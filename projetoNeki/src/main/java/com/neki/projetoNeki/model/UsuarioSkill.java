package com.neki.projetoNeki.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "UsuarioSkill")
@Table(name = "usuario_skill")
public class UsuarioSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_skill", unique = true)
    private Integer UsuarioSkillId;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
 
    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id_skill")
    private Skill skill;
    
    @Column(name = "knowledge_level")
    private Integer knowledgeLevel;
    
    @Column(name = "created_at")
    private Date createdAt = new Date();
    
    @Column(name = "updated_at")
    private Date updatedAt;

	
	public Integer getUsuarioSkillId() {
		return UsuarioSkillId;
	}

	public void setUsuarioSkillId(Integer usuarioSkillId) {
		UsuarioSkillId = usuarioSkillId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getKnowledgeLevel() {
		return knowledgeLevel;
	}

	public void setKnowledgeLevel(Integer knowledgeLevel) {
		this.knowledgeLevel = knowledgeLevel;
	}

	@Override
	public String toString() {
		return "UsuarioSkill [UsuarioSkillId=" + UsuarioSkillId + ", usuario=" + usuario + ", skill=" + skill
				+ ", knowledgeLevel=" + knowledgeLevel + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	 
	
	
}
