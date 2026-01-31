package br.com.treinarecife.sgp.model;

// CORREÇÁO!!!!!!!!!!!!! IMPORTAR LIST
import java.util.List;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// CORRECAO!!!!!!!!!!!!! FALTOU IMPORTAR ONETOMANUY, MANYTOONE, JOINCOLUMN, ENUMERATED E ENUMTYPE
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


// CORRECAO!!!!!!!!!!!!!!!  Em private long idProjeto  -> private long id;   e long -> Long

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

    //CORRECAO!!!!!!!!!! USAR ENUMTYPE
    @Enumerated(EnumType.STRING)
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



