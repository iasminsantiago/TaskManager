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
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProjeto;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    enum enumStatusProjeto {
        ATIVO, CONCLUIDO, CANCELADO
    }
    private enumStatusProjeto statusProjeto;

    /* status (Ativo, Concluído, Cancelado)
o responsavel */

    // CORREÇÁO!!!!!!!!! INSERIR LISTAS
    // Varios projetos para um usuario. Fk é projeto
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;
    
    @OneToMany(mappedBy = "projeto")
    private List<Tarefa> tarefas;




}
