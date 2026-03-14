# 📌 SGP - Sistema de Gerenciamento de Projetos

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de **projetos e tarefas**.

- Usuário
pode ter vários projetos
pode ter várias tarefas
</br>
- Projeto
pertence a um usuário
pode ter várias tarefas
</br>
- Tarefa
pertence a um projeto
pertence a um usuário
</br>
</br>

---

##  Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- Banco de dados H2 / MySQL
- VS Code

---

##  Estrutura do projeto

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

```
##  Como executar o projeto
Pré-requisitos:
</br>

- Java 21 instalado

- Maven instalado

#### Verifique as versões do Java e Maven:
 
java -version
</br>
mvn -v


#### Executando a aplicação
Na pasta onde está o pom.xml, utilizando o terminal, execute:
mvn spring-boot:run


#### A aplicação estará disponível em:
http://localhost:9091


</br>

## Observações

A atualização (PUT) pode ser expandida para incluir todos os atributos da entidade.

Comentários no código indicam possíveis melhorias futuras.
