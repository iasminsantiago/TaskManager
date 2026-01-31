package br.com.treinarecife.sgp.model;

import java.time.LocalDate;

// para colocar em ordem alfabetica, seleciona o bloco, cntl shift p, sort line ascending
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// adicionaremos a annotation entidade, que é a entidade usuario
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // usamos long pois é maior que int
    // por padrão: toda entidade, o id dela é do tipo long

    private String nome;

    private String descricao;

    private String email;

    private String senha;

    private LocalDate dataNascimento;  // localdate não é nativo, importamos bibliotea

    enum enumStatus {
        ATIVO, BLOQUEADO, INATIVO
    }; // vetor de 3 posições

    private enumStatus status;

}

// o lombok faz o construtor e o getter e setter por mim, por isso ele nao colocou