# TaskManager
Projeto feito na Treina Recife durante curso full stack


👤 Usuário
pode ter vários projetos
pode ter várias tarefas

📁 Projeto
pertence a um usuário
pode ter várias tarefas

✅ Tarefa
pertence a um projeto
pertence a um usuário


Quem tem a FK usa @ManyToOne
@OneToMany → lado que tem lista
Relacionamento SEMPRE é objeto, não id

Enum → usar @Enumerated(EnumType.STRING)
  // end point é ponto final, rota.  a base url rodando na minha maquina é lcoalhost:8080, essa é a base url. abaxo dela, colocamos os endpoint: 
    ///api/usuario.
    /// meu usuaruo tem 3 metoos rest: post, get, put, delete
    /// /api/usuarios
    ///     post(id)  tem que ser unitario
    ///     get(all, id) 
    ///     put(id)  obriatorio ele ser unitario, tem que ter id
    ///     delete(id)    essas coisas nao entram na rota, 
    // a rota é api/usuario
   // a abse url é o  localost:8080
  // pra fazer algo num id espeiifco, faço /api/usuario/{id}

REGISTRO - O QUE MUDEI
📂 1️⃣ UsuarioController.java  corrigimos para {} 
@GetMapping("/(id)")  -> @GetMapping("/{id}")

📂 2️⃣ ProjetoController.java   especificamos o id; e Titulo é um campo que não existe, atualizamos para Nome (A entidade Projeto não tem titulo, só nome.)
findById sem o id: projetoRepository.findById() -> projetoRepository.findById(id)
****Não existe o campo titulo em projeto, só em tarefas. Mudamos para nome: projeto.setTitulo(projetoDetails.getTitulo());  -> projeto.setNome(projetoDetails.getNome());
Rota errada: @GetMapping("/(id)") ->    @GetMapping("/{id}")

📂 3️⃣ TarefaController.java
Rota errada: @GetMapping("/(id)") ->    @GetMapping("/{id}")
findById() incompleto: return tarefaRepository.findById().ma... -> return tarefaRepository.findById(id).ma...
inserimos os outros campos em     @PutMapping("/{id}")






