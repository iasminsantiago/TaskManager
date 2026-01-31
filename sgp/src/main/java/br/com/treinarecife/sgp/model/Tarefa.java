package br.com.treinarecife.sgp.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



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
    private LocalDate datacriacao;
    private LocalDate dataConclusao;

    //criando o enum em si
    enum enumPrioridade {
        BAIXA, MEDIA, ALTA
    };

    // criando variável que envolve valores de enum
    private enumPrioridade prioridade; 

    enum enumStatus {
        PENDENTE, FAZENDO, FEITO
    };

    private enumStatus status;


    // tarefa importa projeto
    private Long idProjeto;    // falta vincular a projeto, mas como?

}
