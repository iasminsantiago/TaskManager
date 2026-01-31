package br.com.treinarecife.sgp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinarecife.sgp.model.Projeto;
import br.com.treinarecife.sgp.repository.ProjetoRepository;

/* // rest controller recebe as requisições de classe
// request mapping é o caminho da rota 
// NUNCA SEPARAR @ da classe que vem logo abaixo, pois diz-se que @restcontroller alerarrá pela linha de baixo, ai ao inves de vir a classe vem um comentaroo, ele nao entende */


// 4 verbos basicos: post get pra todos  get especifico  pt para atualziar um id especifico e rota delete pra apagar um id especifico

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {
    // ligar controller com meu repositorio usando a annotation autowired:
    @Autowired  
    private ProjetoRepository projetoRepository;
    
    // rota POST:
    // deve ter obrigatoriamente um copo de dado, ai uso requestbody para pegar ele
    @PostMapping 
    public Projeto postProjeto(@RequestBody Projeto projeto){
        return projetoRepository.save(projeto);
        // ele vai la no repositorio e salva os dados que vieram pela variavel projeto
    }

    // Rota PUT para atualizar um ID especifico
    // pit mapping é uma rota, entao ela tem que recebr um apramentro especifico
    // o id nao é atualziado, nao faz sentido
    // lombok ja criou os metodos get e set pra mim, nao preciso esrever os metosos ge te set, so s usar
    // CORREÇÃO!!!!!!!!!!!!  projeto.setTItulo(projetoDetails.getTitulo());  ->    projeto.setNome(projetoDetails.getNome());
    // CORREÇÀO!!!!!!!!!!!   return projetoRepository.findById().map(projeto -> {        return projetoRepository.findById(id).map(projeto -> {
    @PutMapping("/{id}")
    public ResponseEntity<Projeto> putProjeto(@PathVariable Long id, @RequestBody Projeto projetoDetails) {
        return projetoRepository.findById(id).map(projeto -> {
            projeto.setNome(projetoDetails.getNome());
            // !!!!!!!!repetir para os outros campos, como descricao, titulo etc!!!!!!!
            Projeto updateProjeto = projetoRepository.save(projeto);
            return ResponseEntity.ok(updateProjeto);
        }).orElse(ResponseEntity.notfound().build());
        
        // preciso encontrar o id par aper o atualizar, chamao
    }

    // retornará uma lista de projetos
    // get all:
    @GetMapping
    public List<Projeto> getAllProjetos() {
        return projetoRepository.findAll();
    }

    // CORREÇÃO!!!!!!!!!!!!!!     @GetMapping("/(id)") ->    @GetMapping("/{id}")
    // get id:
    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        return projetoRepository.findById(id).map(ResponseEntity::ok)
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
