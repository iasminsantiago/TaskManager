# 📌 SGP - Sistema de Gerenciamento de Projetos

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de **projetos e tarefas**.

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

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- Banco de dados H2 / MySQL (ajuste conforme o projeto)
- VS Code

---

## 📁 Estrutura do projeto

```text
sgp
 └── src
     └── main
         └── java
             └── br.com.treinarecife.sgp
                 ├── controller
                 ├── model
                 ├── repository
                 └── SgpApplication.java
