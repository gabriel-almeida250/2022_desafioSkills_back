package com.neki.projetoNeki.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neki.projetoNeki.model.UsuarioSkill;

public interface UsuarioSkillRepository extends JpaRepository<UsuarioSkill, Integer> {

	public Optional<UsuarioSkill> findByUsuarioId(Integer usuarioId);
}
