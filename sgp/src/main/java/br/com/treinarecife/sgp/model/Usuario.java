package br.com.treinarecife.sgp.model;

import java.time.LocalDate;
import java.util.List;

// Boa prática: colocar em ordem alfabética -> seleciona o bloco, cntl shift p, sort line ascending
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;




// Adicionaremos a annotation entidade, que é a entidade usuário
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // usamos long pois é maior que int
    // Por padrão: em toda entidade, o id dela é do tipo Long

    private String nome;

    private String descricao;

    private String email;

    private String senha;

    private LocalDate dataNascimento;  // localDate não é nativo, por isso importamos bibliotea

    enum enumStatus {
        ATIVO, BLOQUEADO, INATIVO
    }; // Vetor de 3 posições


    @Enumerated(EnumType.STRING)
    private enumStatus status;

    // Inserindo relacionamentos:
    // usuario para projetos, e usuario para tarefas
    @OneToMany(mappedBy = "responsavel")
    private List<Projeto> projetos;

    @OneToMany(mappedBy = "usuario")
    private List<Tarefa> tarefas;

}
// o lombok faz o construtor e o getter e setter por mim, por isso não usamos estes métodos


