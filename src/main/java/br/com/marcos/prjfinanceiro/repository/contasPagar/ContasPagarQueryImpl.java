package br.com.marcos.prjfinanceiro.repository.contasPagar;

import br.com.marcos.prjfinanceiro.repository.filter.ContasPagarFilter;
import br.com.marcos.prjfinanceiro.dto.ContasPagarDto;
import br.com.marcos.prjfinanceiro.model.ContasPagar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
public class ContasPagarQueryImpl implements ContasPagarQuery {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ContasPagarDto> filtrar(ContasPagarFilter contasPagarFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ContasPagarDto> criteria = builder.createQuery(ContasPagarDto.class);
        Root<ContasPagar> root = criteria.from(ContasPagar.class);

        criteria.select(builder.construct(ContasPagarDto.class,
                root.get("id"),
                root.get("descricaoContas"),
                root.get("valorContas"),
                root.get("fornecedores").get("nomeFornecedores")
        ));

        Predicate[] predicates = criarRestricoes(contasPagarFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("descricaoContas")));

        TypedQuery<ContasPagarDto> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(contasPagarFilter));
    }

    private Predicate[] criarRestricoes(ContasPagarFilter contasPagarFilter, CriteriaBuilder builder, Root<ContasPagar> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (contasPagarFilter != null && StringUtils.hasText(contasPagarFilter.getDescricaoContas())) {
            predicates.add(builder.like(root.get("descricaoContas"), "%" + contasPagarFilter.getDescricaoContas() + "%"));
        }

        if (contasPagarFilter != null && StringUtils.hasText(contasPagarFilter.getNomeFornecedores())) {
            predicates.add(builder.like(root.get("fornecedores").get("nomeFornecedores"), "%" + contasPagarFilter.getNomeFornecedores() + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<ContasPagarDto> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(ContasPagarFilter contasPagarFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ContasPagar> root = criteria.from(ContasPagar.class);

        Predicate[] predicates = criarRestricoes(contasPagarFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }
}
