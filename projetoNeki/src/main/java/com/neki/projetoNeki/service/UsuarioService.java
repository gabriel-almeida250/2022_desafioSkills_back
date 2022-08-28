package com.neki.projetoNeki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki.projetoNeki.exceptions.InvalidLoginException;
import com.neki.projetoNeki.model.Usuario;
import com.neki.projetoNeki.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	} 
	
	public Usuario findById(Integer id) {
		return usuarioRepository.findById(id).isPresent() ? usuarioRepository.findById(id).get() : null;
	}
	
	public Usuario findByLogin(String login) {
		return usuarioRepository.findByLogin(login).isPresent() ? usuarioRepository.findByLogin(login).get() : null;
	}
	
	public Usuario save(Usuario usuario) {
		validarLogin(usuario.getLogin());
		return usuarioRepository.save(usuario);
	}
	
	public Usuario update(Usuario usuario) {
		validarLogin(usuario.getLogin());
		return usuarioRepository.save(usuario);
	}
	
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}
	
	private void validarLogin(String login) {
		var usuario = usuarioRepository.findByLogin(login);
		if (usuario.isPresent()) {
			throw new InvalidLoginException("Esse login já existe no bando de dados");
		}
	}
}

	
