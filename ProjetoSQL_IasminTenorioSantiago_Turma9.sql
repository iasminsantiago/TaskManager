-- ====================================
-- PROJETO FULLSTACK - SQL
-- ====================================
-- ALUNA: IASMIN TENORIO SANTIAGO
-- TURMA: 9 (SABADO)

-- SGBD: MYSQL

-- NOTAS ==============================
-- NN = NOT NULL
-- E = tipo ENUM
-- ENUM = um tipo que nós criamos com valores permitidos. No MySQL, não  usamos espaço entre ENUM e (; nem espaço entre valores e virgula.
-- BIGINT = numero inteiro grande. Não aceita tamanho como o VARCHAR.
-- VARCHAR = texto curto variável, se adapta ao tamanho informado pelo usuário. Por isso é VAR.
-- TEXT = texto longo, não precisa informar tamanho. Não tem limite específico além o máximo do banco de dados. 
-- VARCHAR(MAX) é (essencialmente) o mesmo que TEXT, mas é armazenado em outra área.
-- DATE = ao definir o tipo DATE, não podemos definir como ele guardará a data. O formato será yyyy-dd-mm. Mas podemos definir o formato de exibição na querry (SELECT).
-- Ao comentar, não fazer --Palavra, dá erro. Deve haver espaço entre -- e Palavra.


-- BANCO DE DADOS =====================
CREATE DATABASE IF NOT EXISTS BD_SGP;
USE BD_SGP;


-- TABELAS ============================

CREATE TABLE usuarios (
id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL, 
cpf VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL, 
data_nascimento DATE NOT NULL,
status_usuario ENUM('ATIVO','INATIVO','BLOQUEADO') NOT NULL
);


CREATE TABLE projetos (
id_projeto BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
descricao TEXT,
data_inicio DATE NOT NULL,
data_conclusao DATE,
status_projeto ENUM('ATIVO','CONCLUIDO','CANCELADO') NOT NULL,
responsavel BIGINT NOT NULL,

FOREIGN KEY (responsavel) REFERENCES usuarios(id_usuario)
);


-- Boas práticas: 
-- separar os ids definidos das foreign keys das colunas principais. 
-- Colocar todas as foreign keys só no final ao invés de após definição de variáveis e tipos delas.
-- Colocar comentário antes, não entre campos/no meio da definição


CREATE TABLE tarefas (
id_tarefa BIGINT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(250) NOT NULL,
descricao TEXT,
data_criacao DATE NOT NULL,
data_conclusao DATE,
prioridade_tarefa ENUM('BAIXA','MEDIA','ALTA') NOT NULL,
status_tarefa ENUM('PENDENTE','FAZENDO','CONCLUIDA') NOT NULL,

id_projeto BIGINT NOT NULL,
id_usuario BIGINT NOT NULL,

FOREIGN KEY (id_projeto) REFERENCES projetos(id_projeto),
FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);




-- INSERINDO VALORES PARA TESTE =======
-- Obs.: Iasmin: id 1; Alexandre: id =2; Dea = id 3; Phillip = id 4;
-- O projeto de Phillip foi cancelado, mas há tarefa criada para ele.
-- Dea não tem projetos nem tarefas atreladas a ela.


INSERT INTO usuarios (nome, cpf, email, data_nascimento, status_usuario) 
VALUES
('Iasmin Santiago', '100.000.999-11', 'ia.smincodes@gmail.com', '1995-08-02', 'ATIVO'), ('Alexandre Ferreira', '100.200.300-40', 'alexandre.ferreira@gmail.com', '1950-11-03', 'BLOQUEADO'),
('Déa Araujo', '111.444.666-22', 'dea.araujo@hotmail.com', '1956-09-16', 'INATIVO'),
('Phillip Ferreira', '500.600.700-80', 'phillip.ferreira@outlook.com', '2014-05-28', 'ATIVO');


INSERT INTO projetos (nome, descricao, data_inicio, data_conclusao, status_projeto, responsavel)
VALUES 
('Sistema financeiro', 'Desenvolvimento de novo sistema de emissão de NFs e conciliação bancária da empresa', '2025-12-01', NULL, 'ATIVO', 1),
('Site de curso de joalheria', 'Criação de novo site para vendas e hospedagem de curso online de joalheira do professor Alexandre Ferreira', '2023-11-03', '2025-12-01', 'CONCLUIDO', 2),
('Site de jogos online estilo Roblox', 'Site de jogos para crianças estilo Roblox, feito com criança em trilha de aprendizagem na área de programação de jogos', '2024-12-20', NULL, 'CANCELADO', 4),
('Portfolio online de fotografia', 'Site para postagem de trabalhos como fotógrafa e tutoriais', '2019-08-02', '2024-08-02', 'CONCLUIDO', 1);


INSERT INTO tarefas (titulo, descricao, data_criacao, data_conclusao, prioridade_tarefa, status_tarefa, id_projeto, id_usuario)
VALUES
('Criar banco de dados', 'Modelar e criar as tabelas iniciais do banco de dados', '2025-12-02', NULL, 'ALTA', 'FAZENDO', 1, 1),
('Desenvolver API entre banco e site', 'Criar aplicação que vincule banco do cliente a página de compras do site de curso online.', '2023-11-20', '2023-11-21', 'MEDIA', 'CONCLUIDA', 2, 2),
('Finalizar layout do site', 'Ajustar design responsivo', '2025-12-01', NULL, 'BAIXA','PENDENTE', 3, 4),
('Criar layout do módulo de emissão de notas fiscais eletrônicas', 'Desenvolver layout para interação com o emissor de notas / usuário do site', '2025-11-30', NULL, 'ALTA', 'PENDENTE', 1, 1); 




-- QUERRIES DE TESTE ==================

-- Q1: Selecionar todos os usuários
SELECT * FROM usuarios;

-- Q2: Selecionar todos os projetos
SELECT * FROM projetos;

-- Q3: Selecionar todas as tarefas
SELECT * FROM tarefas;

-- Q4: Ver tarefas com o nome do usuário e o nome do projeto
SELECT t.id_tarefa, t.titulo, t.status_tarefa, t.prioridade_tarefa, u.nome AS Responsável, p.nome AS Projeto
FROM tarefas t
JOIN usuarios u on t.id_usuario = u.id_usuario
JOIN projetos p on t.id_projeto = p.id_projeto;

-- Q5: Tarefas apenas do projeto “Sistema financeiro”
SELECT t.titulo, t.status_tarefa, t.prioridade_tarefa
FROM tarefas t
JOIN projetos p on p.id_projeto = t.id_projeto
WHERE p.nome = 'Sistema financeiro';

-- Q6: Tarefas pendentes ordenadas por prioridade
-- como prioridade_tarefa é string, em DESC, fica MEDIA> BAIXA> ALTA. Assim, podemos usar CASE WHEN para indicar ordem desejada, atribuindo as strings a números. O data_criacao vem para desempatar, caso haja 2 ou + tarefas com mesma prioridade.

SELECT titulo, prioridade_tarefa, data_criacao
FROM tarefas
WHERE status_tarefa = 'PENDENTE'
ORDER BY 
    CASE
        WHEN prioridade_tarefa = 'ALTA' THEN 1
        WHEN prioridade_tarefa = 'MEDIA' THEN 2
	WHEN prioridade_tarefa = 'BAIXA' THEN 3
	ELSE 4
    END,
data_criacao;



-- Q7: Quantidade de tarefas por projeto
SELECT p.nome Projeto, COUNT(t.id_tarefa) qtd_tarefas
FROM projetos p
LEFT JOIN tarefas t on p.id_projeto = t.id_projeto
GROUP BY p.id_projeto, p.nome;

-- Q8: Mostrar usuários e quantidade de projetos sob sua responsabilidade
SELECT u.nome, COUNT(p.id_projeto) qtd_projetos
FROM usuarios u
LEFT JOIN projetos p ON p.responsavel = u.id_usuario
GROUP BY u.id_usuario, u.nome;
