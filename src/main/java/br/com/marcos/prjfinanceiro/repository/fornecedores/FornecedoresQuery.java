package br.com.marcos.prjfinanceiro.repository.fornecedores;

import br.com.marcos.prjfinanceiro.dto.FornecedoresDto;
import br.com.marcos.prjfinanceiro.repository.filter.FornecedoresFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface FornecedoresQuery {
    Page<FornecedoresDto> filtrar(FornecedoresFilter fornecedoresFilter, Pageable pageable);
}
