package com.neki.projetoNeki.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neki.projetoNeki.model.Usuario;
import com.neki.projetoNeki.repositories.UsuarioRepository;


@Service
public class UserDetailsServiceImplService implements UserDetailsService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException{
		Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
		
		if(usuario.isPresent()) {
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("Usuário não encontrado");
	}
}