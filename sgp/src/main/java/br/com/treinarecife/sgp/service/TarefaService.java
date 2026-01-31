/* 📌 Responsabilidades
Criar tarefa
Vincular tarefa a projeto e usuário
Listar tarefas
Buscar por ID */



package br.com.treinarecife.sgp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinarecife.sgp.model.Projeto;
import br.com.treinarecife.sgp.model.Tarefa;
import br.com.treinarecife.sgp.model.Usuario;
import br.com.treinarecife.sgp.repository.ProjetoRepository;
import br.com.treinarecife.sgp.repository.TarefaRepository;
import br.com.treinarecife.sgp.repository.UsuarioRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tarefa criarTarefa(Long projetoId, Long usuarioId, Tarefa tarefa) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        tarefa.setProjeto(projeto);
        tarefa.setUsuario(usuario);

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }
}
