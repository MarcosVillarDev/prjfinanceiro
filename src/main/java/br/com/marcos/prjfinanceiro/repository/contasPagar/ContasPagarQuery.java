package br.com.marcos.prjfinanceiro.repository.contasPagar;

import br.com.marcos.prjfinanceiro.dto.ContasPagarDto;
import br.com.marcos.prjfinanceiro.repository.filter.ContasPagarFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContasPagarQuery {
    Page<ContasPagarDto> filtrar(ContasPagarFilter contasPagarFilter, Pageable pageable);
}
