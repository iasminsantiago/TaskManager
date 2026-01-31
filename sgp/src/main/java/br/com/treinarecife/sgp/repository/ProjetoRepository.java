package br.com.treinarecife.sgp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinarecife.sgp.model.Projeto;


public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}