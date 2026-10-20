package br.com.marcos.prjfinanceiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedoresDto {

    private Long id;

    private String nomeFornecedores;

    private String telefoneFornecedores;
}