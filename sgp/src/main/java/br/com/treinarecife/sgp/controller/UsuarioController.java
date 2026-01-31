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

import br.com.treinarecife.sgp.model.Usuario;
import br.com.treinarecife.sgp.repository.UsuarioRepository;

/* // rest controller recebe as requisições de classe
// request mapping é o caminho da rota 
// NUNCA SEPARAR @ da classe que vem logo abaixo, pois diz-se que @restcontroller alerarrá pela linha de baixo, ai ao inves de vir a classe vem um comentaroo, ele nao entende */

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    // cria a instancia automaticamente, e ja injeta os metodos via herança
    @Autowired  
    private UsuarioRepository usuarioRepository;
    
    // cria uma linha e retorna, a partir do usuariorepository, o que criamos
    @PostMapping 
    public Usuario postUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    // faço metodo publico pois ele será acessado por fora
    // criei um metodo que chamei de postUsuario, o o arg vem pelo corpo da mensagem, o request body

    // retornará uma lista de usuários
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    // o metodo find all recupera todo so usuarios que há na tabela
    }

    @GetMapping("/(id)")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());

    }


}
