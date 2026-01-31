package br.com.treinarecife.sgp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinarecife.sgp.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
