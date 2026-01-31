package br.com.treinarecife.sgp.controller;

import java.util.List;

/* // linha 1 - package
// linha 2 - cores do java java....
// linha 3 imports acssorios como springframework
// linha 4 - import dos codigos que eu criei  */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinarecife.sgp.model.Tarefa;
import br.com.treinarecife.sgp.repository.TarefaRepository;

/* // rest controller recebe as requisições de classe
// request mapping é o caminho da rota 
// NUNCA SEPARAR @ da classe que vem logo abaixo, pois diz-se que @restcontroller alerarrá pela linha de baixo, ai ao inves de vir a classe vem um comentaroo, ele nao entende */


// 4 verbos basicos: post get pra todos  get especifico  pt para atualziar um id especifico e rota delete pra apagar um id especifico

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    // ligar controller com meu repositorio usando a annotation autowired:
    @Autowired  
    private TarefaRepository tarefaRepository;
    
    // rota POST:
    // deve ter obrigatoriamente um copo de dado, ai uso requestbody para pegar ele
    @PostMapping 
    public Tarefa postTarefa(@RequestBody Tarefa tarefa){
        return tarefaRepository.save(tarefa);
        // ele vai la no repositorio e salva os dados que vieram pela variavel tarefa
    }

    // Rota PUT para atualizar um ID especifico
    // pit mapping é uma rota, entao ela tem que recebr um apramentro especifico
    // o id nao é atualziado, nao faz sentido
    // lombok ja criou os metodos get e set pra mim, nao preciso esrever os metosos ge te set, so s usar
    // CORREÇÀO!!!!!!!!!!  return tarefaRepository.findById().ma... -> return tarefaRepository.findById(id).ma...
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> putTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetails) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setTitulo(tarefaDetails.getTitulo());
            // !!!!!!!!repetir para os outros campos, como descricao, titulo etc!!!!!!!
            // CORREÇÃO!!!!!!!!!! INSERIMOS OS OUTROS CAMPOS. 
            // agora o PUT atualiza tudo
            tarefa.setDescricao(tarefaDetails.getDescricao());
            tarefa.setStatus(tarefaDetails.getStatus());
            tarefa.setPrioridade(tarefaDetails.getPrioridade());
            tarefa.setProjeto(tarefaDetails.getProjeto());
            tarefa.setUsuario(tarefaDetails.getUsuario());
            Tarefa updateTarefa = tarefaRepository.save(tarefa);
            return ResponseEntity.ok(updateTarefa);
        }).orElse(ResponseEntity.notfound().build());
        
        // preciso encontrar o id par aper o atualizar, chamao
    }

    // retornará uma lista de tarefas
    // get all:
    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }

    // get id:
    // CORREÇÃO!!!!!!!!!!!      @GetMapping("/(id)") ->     @GetMapping("/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
        return tarefaRepository.findById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());

    }

    // end point é ponto final, rota.  a base url rodnao na minha maquina é lcoalhost:8080, essa é a base url. abaxo dela, colocamos os endpoint: 
    ///api/usuario.
    /// meu usuaruo tem 3 metoos rest: post, get, put, delete
    /// /api/usuarios
    ///     post(id)  tme qeu ser nitario
    ///     get(all, id) 
    ///     put(id)  obriatorio ele se runitario, tem qru ter id
    ///     delete(id)    essas coisa snoa entram na rota, 
    // a rota é api/usuario
   // a abse url é o  localost:8080
  // pra fazer algo num id espeiifco, faço /api/usuario/{id}




}
