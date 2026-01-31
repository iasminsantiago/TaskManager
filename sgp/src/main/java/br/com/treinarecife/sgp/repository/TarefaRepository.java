package br.com.treinarecife.sgp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinarecife.sgp.model.Tarefa;


public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}