package br.com.treinarecife.sgp.controller;

import java.util.List;

// linha 1 - package
// linha 2 - cores do java java....
// linha 3 imports acssorios como springframework
// linha 4 - import dos codigos que eu criei  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinarecife.sgp.model.Tarefa;
import br.com.treinarecife.sgp.repository.TarefaRepository;


@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    // ligar controller com meu repositorio usando a annotation autowired:
    @Autowired  
    private TarefaRepository tarefaRepository;
    

    @PostMapping 
    public Tarefa postTarefa(@RequestBody Tarefa tarefa){
        return tarefaRepository.save(tarefa);
        // Ele vai la no repositorio e salva os dados que vieram pela variavel tarefa
    }

    // Rota PUT para atualizar um ID especifico

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> putTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetails) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setTitulo(tarefaDetails.getTitulo());
            // agora o PUT atualiza tudo
            tarefa.setDescricao(tarefaDetails.getDescricao());
            tarefa.setStatus(tarefaDetails.getStatus());
            tarefa.setPrioridade(tarefaDetails.getPrioridade());
            tarefa.setProjeto(tarefaDetails.getProjeto());
            tarefa.setUsuario(tarefaDetails.getUsuario());
            Tarefa updateTarefa = tarefaRepository.save(tarefa);
            return ResponseEntity.ok(updateTarefa);
        }).orElse(ResponseEntity.notFound().build());
        
    }

    // Retornará uma lista de tarefas
    // get all:
    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }

    // get id:
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
        return tarefaRepository.findById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());

    }



}


