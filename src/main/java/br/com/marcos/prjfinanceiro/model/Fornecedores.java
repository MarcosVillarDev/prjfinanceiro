package br.com.marcos.prjfinanceiro.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="fornecedores")
public class Fornecedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nomefornecedores", length = 200)
    private String nomeFornecedores;

    @Column(name = "telefonefornecedores", length = 20)
    private String telefoneFornecedores;

    @OneToMany(mappedBy = "fornecedores")
    private List<ContasPagar> contasPagar;
}
