package br.com.marcos.prjfinanceiro.repository;

import br.com.marcos.prjfinanceiro.repository.fornecedores.FornecedoresQuery;
import br.com.marcos.prjfinanceiro.model.Fornecedores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedoresRepository extends JpaRepository<Fornecedores, Long>, FornecedoresQuery {
}
