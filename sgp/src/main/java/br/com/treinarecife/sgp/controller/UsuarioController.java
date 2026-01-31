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

import br.com.treinarecife.sgp.model.Usuario;
import br.com.treinarecife.sgp.repository.UsuarioRepository;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    // cria a instancia automaticamente, e ja injeta os metodos via herança
    @Autowired  
    private UsuarioRepository usuarioRepository;
    
    // cria uma linha e retorna, a partir do UsuarioRepository, o que criamos
    @PostMapping 
    public Usuario postUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    // faço metodo publico pois ele será acessado por fora
    // criei um metodo que chamei de postUsuario, o arg vem pelo corpo da mensagem, o request body.

    // Retornará uma lista de usuários
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    // O metodo find all recupera todo so usuarios que há na tabela
    }

    // getUsuariobyId é um metodo que nomeados, mas poderia ser também buscarPorId(@Pat....
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());

    }


}


