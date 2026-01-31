package br.com.treinarecife.sgp.model;

import java.util.List;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    enum enumStatusProjeto {
        ATIVO, CONCLUIDO, CANCELADO
    }

    //Usando EnumType
    @Enumerated(EnumType.STRING)
    private enumStatusProjeto statusProjeto;


    // Há varios projetos para um usuário. Dessa forma, Fk é projeto
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;
    
    @OneToMany(mappedBy = "projeto")
    private List<Tarefa> tarefas;



}




