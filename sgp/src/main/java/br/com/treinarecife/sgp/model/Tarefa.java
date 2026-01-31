package br.com.treinarecife.sgp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;

    //criando o enum em si
    enum enumPrioridade {
        BAIXA, MEDIA, ALTA
    };

    // Criando variável que envolve valores de enum
    @Enumerated(EnumType.STRING)
    private enumPrioridade prioridade; 

    enum enumStatus {
        PENDENTE, FAZENDO, FEITO
    };

    @Enumerated(EnumType.STRING)
    private enumStatus status;

    // Não basta escrever private Long idProjeto;  precisamos vincular isso a projeto: 
    // projeto_id é uma FK que aponta para Projeto. @JoinColumn fala com o BANCO.
    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    // projeto_id é o nome da coluna no banco de dados, não o atributo idProjeto da classe Projeto. 
    // Para não confundir, renomeados de forma diferente.
    // JPA trabalha com objeto, isso cria a FK real no banco.
    // Poderíamos usar idProjeto, mas projeto_id é o padrão recomendado.
    // Mas ambas as opcoes criam uma FK, ambas apontam para Projeto.id, ambas funcionam no H2, MySQL, Postgres etc.
}






