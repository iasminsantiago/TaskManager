package br.com.treinarecife.sgp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinarecife.sgp.model.Projeto;
import br.com.treinarecife.sgp.repository.ProjetoRepository;

/* Rest controller recebe as requisições de classe. 
// Request mapping é o caminho da rota.
// Obs.: Não separamos @ da classe que vem logo abaixo. Ele se orientará pela linha de baixo. Se vir um comentário antes dela, ele não entenderá o comando.


/* 4 verbos basicos: 
post get pra todos  
get especifico  
put para atualziar um id especifico e rota 
delete pra apagar um id especifico */

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {
    // Ligar controller com meu repositorio usando a annotation autowired:
    @Autowired  
    private ProjetoRepository projetoRepository;
    
    // Rota POST:
    // deve ter obrigatoriamente um corpo de dado, e uso requestbody para pegar ele
    @PostMapping 
    public Projeto postProjeto(@RequestBody Projeto projeto){
        return projetoRepository.save(projeto);
        // Ele vai la no repositório e salva os dados que vieram pela variavel projeto.
    }

    /* Rota PUT para atualizar um ID especifico 
    put mapping é uma rota, entao ela tem que receber um paramentro especifico Senão, o id nao é atualizado, nao faz sentido.
    lombok ja criou os metodos get e set pra mim, nao preciso esrever os metosos get e set */ 
    
   
    @PutMapping("/{id}")
    public ResponseEntity<Projeto> putProjeto(@PathVariable Long id, @RequestBody Projeto projetoDetails) {
        return projetoRepository.findById(id).map(projeto -> {
            projeto.setNome(projetoDetails.getNome());
            // !!!!!!!!repetir para os outros campos, como descricao, titulo etc!!!!!!!
            Projeto updateProjeto = projetoRepository.save(projeto);
            return ResponseEntity.ok(updateProjeto);
        }).orElse(ResponseEntity.notFound().build());
        
    }

    // get all:
   // Retornará uma lista de projetos
    @GetMapping
    public List<Projeto> getAllProjetos() {
        return projetoRepository.findAll();
    }

    // get id:
    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        return projetoRepository.findById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());

    }

   /* end point é ponto final, rota.  
   A base url rodando  na minha maquina é localhost:8080, essa é a base url. Abaxo dela, colocamos os endpoints: 
    /api/usuario.
    Meu usuaruo tem 3 metodos rest: post, get, put, delete
     /api/usuarios
        post(id)  tme qeu ser nitario
        get(all, id) 
        put(id)  obriatorio ele se runitario, tem qru ter id
        delete(id)    essas coisa snoa entram na rota, 
   A rota é api/usuario
   A base url é o  localost:8080
  Parara fazer algo num id espeiifco, faço /api/usuario/{id} */




}


