# TaskManager
Projeto feito na Treina Recife durante curso full stack  --  EM CONSTRUÇÃO


👤 Usuário
pode ter vários projetos
pode ter várias tarefas
</br>
📁 Projeto
pertence a um usuário
pode ter várias tarefas
</br>
✅ Tarefa
pertence a um projeto
pertence a um usuário
</br>
</br>

Testes:
</br>
Usei o Swagger para testar os endpoints REST e o H2 Console para validar os dados persistidos no banco.

http://localhost:9091/swagger-ui/index.html


</br>
| Ferramenta | Serve pra quê       |
| ---------- | ------------------- |
| H2 Console | Ver tabelas e dados |
| Swagger    | Testar rotas da API |
| Swagger UI | Interface gráfica   |
| Try it out | Enviar requisição   |
| Execute    | Rodar a rota        |



Service = regras de negócio

Controller = endpoints

Repository = banco

Relacionamentos são validados no Service
</br>
</br>
Quem tem a FK usa @ManyToOne
@OneToMany → lado que tem lista
Relacionamento SEMPRE é objeto, não id
</br>
</br>
mappedBy significa:
✅ Diz que o outro lado é o dono do relacionamento
✅ Evita criar duas FKs no banco

@JoinColumn → cria a FK no banco
mappedBy → diz “a FK está no outro lado”

EX. PROJETOS.JAVA
@JoinColumn(name = "usuario_id")     =  “Cria uma coluna no banco chamada usuario_id”, 
Essa coluna:
fica na tabela projeto
aponta para usuario.id
é uma Foreign Key de verdade. “Este objeto guarda a chave estrangeira.”
👉 Quem usa @JoinColumn é o DONO do relacionamento. Projeto é o dono. 

mappedBy — LADO INVERSO (NÃO cria FK)
@OneToMany(mappedBy = "projeto")
private List<Tarefa> tarefas;
Isso diz ao JPA: “Não cria coluna aqui.  O relacionamento já está mapeado no atributo projeto da classe Tarefa.”
NÃO cria coluna no banco
NÃO cria FK
Só existe para navegação no Java

📌 Projeto → Tarefa
Lado dono (Tarefa):
@ManyToOne
@JoinColumn(name = "projeto_id")  // ← DONO
private Projeto projeto;

Lado inverso (Projeto):
@OneToMany(mappedBy = "projeto")  // ← INVERSO
private List<Tarefa> tarefas;

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

</br>
</br>

🔹 4️⃣ Ordem CORRETA pra testar teu projeto

⚠️ Isso é MUITO importante:
1️⃣ Criar usuário
POST /api/usuarios

2️⃣ Criar projeto (usando ID do usuário)
POST /api/usuarios/{usuarioId}/projetos

No Swagger:
coloca o usuarioId na URL
body só com dados do projeto

3️⃣ Criar tarefa (usando projeto + usuário)
POST /api/projetos/{projetoId}/usuarios/{usuarioId}/tarefas


## REGISTRO - O QUE MUDEI

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

📂 4️⃣ Tarefa.java (MODEL)
private Long idProjeto; -> @ManyToOne @JoinColumn(name = "projeto_id") private Projeto projeto;
inserir relacionamento entre tarefas e usuario @ManyToOne  @JoinColumn(name = "usuario_id")    private Usuario usuario;
datacriacao; -> dataCriacao
Importar manytone, joincolumn, enumerated, enumtype
Inserir anotaçáo nos dois enum     @Enumerated(EnumType.STRING)


📂 5️⃣ Projeto.java
Inserir relacionamentos manytoone (projetos para usuario) e onetomany (projeto para tarefas)
Importar list, ManyToOne, OneToMany, Enumerated, EnumType, JoinColumn
padronizacao dos ids de cada classe: private long idProjeto  -> private long id; Esses ids nao se colidem entre si, cada um pertence a sua propria tabela
Inserir @Enumerated(EnumType.STRING) em enumStatusProjeto
Corrigir long:  private long id  -> private Long id


📂 6️⃣ Usuario.java
Inserir relacionamentos onetomany (usuario para projetos e para tarefas)
Importar List e @OnetoMany: import java.util.List; e import jakarta.persistence.OneToMany;
Inserir     @Enumerated(EnumType.STRING)
Importar Enumerated e EnumType:   import jakarta.persistence.Enumerated; e import jakarta.persistence.EnumType;


