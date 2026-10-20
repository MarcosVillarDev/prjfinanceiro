package br.com.marcos.prjfinanceiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContasPagarDto {


    private Long id;

    private String descricaoContas;

    private BigDecimal valorContas;

    private Long fornecedoresId;

    private String nomeFornecedores;
}
