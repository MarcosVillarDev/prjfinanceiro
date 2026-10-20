package br.com.marcos.prjfinanceiro.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="contasPagar")
public class ContasPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Ela compara objetos com base em campos específicos (ex: id), em vez de referências de memória, garantindo que objetos com o mesmo valor sejam tratados como iguais
    private Long id;

    @Column(name = "descricaocontas", length = 255)
    private String descricaoContas;

    @Column(name = "valorcontas", precision = 12, scale = 2)
    private BigDecimal valorContas;

    @ManyToOne
    @JoinColumn(name = "fornecedores_id", nullable = false)
    private Fornecedores fornecedores;
}
