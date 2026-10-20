package br.com.marcos.prjfinanceiro.repository;

import br.com.marcos.prjfinanceiro.repository.contasPagar.ContasPagarQuery;
import br.com.marcos.prjfinanceiro.model.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long>, ContasPagarQuery {
}
