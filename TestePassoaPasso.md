🟢 1️⃣ Abrindo o projeto (30s)

O que fazer

Rodar o projeto (SgpApplication)

Mostrar no console:

Tomcat started on port 9091


O que falar

“Eu estou usando Spring Boot com JPA e banco H2 em memória.”

🟦 2️⃣ Abrir o Swagger (30s)

O que fazer

Abrir no navegador:

http://localhost:9091/swagger-ui/index.html


O que falar

“Aqui no Swagger eu consigo visualizar e testar todos os endpoints REST da aplicação.”

Aponta para:

UsuarioController

ProjetoController

TarefaController

👤 3️⃣ Criar um usuário (1 min)
👉 Ação

Abrir:

POST /api/usuarios


Clicar Try it out

Usar este JSON:

{
  "nome": "Ana",
  "email": "ana@email.com",
  "senha": "123",
  "status": "ATIVO"
}


Clicar Execute

👉 O que falar

“Primeiro eu crio um usuário, porque projetos e tarefas dependem dele.”

Aponta o ID retornado.

📁 4️⃣ Criar um projeto para o usuário (1 min)
👉 Ação

Abrir:

POST /api/usuarios/{usuarioId}/projetos


Colocar o usuarioId da Ana

JSON:

{
  "nome": "Projeto SGP",
  "descricao": "Sistema de gerenciamento de projetos",
  "statusProjeto": "ATIVO"
}

👉 O que falar

“O projeto pertence a um usuário, então o ID do usuário vem na URL.”

Aponta o ID do projeto retornado.

✅ 5️⃣ Criar uma tarefa (1–2 min)
👉 Ação

Abrir:

POST /api/projetos/{projetoId}/usuarios/{usuarioId}/tarefas


Preencher:

projetoId

usuarioId

JSON:

{
  "titulo": "Criar models",
  "descricao": "Usuario, Projeto e Tarefa",
  "prioridade": "ALTA",
  "status": "PENDENTE"
}

👉 O que falar

“A tarefa depende tanto do projeto quanto do usuário, por isso os dois IDs aparecem na rota.”

🔥 Essa frase é ouro.

📋 6️⃣ Listar dados (1 min)
👉 Ação

Testar:

GET /api/usuarios
GET /api/projetos
GET /api/tarefas

👉 O que falar

“Esses endpoints permitem visualizar os recursos cadastrados.”

🟨 7️⃣ Mostrar o H2 Console (1 min)
👉 Ação

Abrir:

http://localhost:9091/h2-console


Preencher:

JDBC URL: jdbc:h2:mem:sgpDB

User: sa

Password: senha

Executar:

SELECT * FROM USUARIO;
SELECT * FROM PROJETO;
SELECT * FROM TAREFA;

👉 O que falar

“Aqui eu consigo confirmar que o JPA criou as tabelas e as chaves estrangeiras corretamente.”

Aponta:

usuario_id

projeto_id

🧠 8️⃣ Fechamento (30s)

Finaliza com:

“A aplicação segue arquitetura em camadas, com Controller, Service e Repository, utilizando JPA para mapeamento dos relacionamentos e Swagger para testes da API.”

📝 FRASES PRONTAS (cola mental)

Se o professor perguntar:

❓ “Onde está o relacionamento?”

“No JPA, os relacionamentos são feitos por objetos, e o banco recebe as chaves estrangeiras automaticamente.”

❓ “Por que usar Service?”

“Para centralizar regras de negócio e manter o controller desacoplado.”

❓ “Como você testou?”

“Usei Swagger para testar os endpoints e H2 Console para validar os dados.”
