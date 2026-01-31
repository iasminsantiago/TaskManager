/* 📌 Responsabilidades
Criar projeto
Vincular projeto a um usuário
Listar projetos
Buscar projeto por ID */ 


package br.com.treinarecife.sgp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinarecife.sgp.model.Projeto;
import br.com.treinarecife.sgp.model.Usuario;
import br.com.treinarecife.sgp.repository.ProjetoRepository;
import br.com.treinarecife.sgp.repository.UsuarioRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Projeto criarProjeto(Long usuarioId, Projeto projeto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        projeto.setResponsavel(usuario);
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }
}
