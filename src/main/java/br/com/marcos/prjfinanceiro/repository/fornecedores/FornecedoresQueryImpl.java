package br.com.marcos.prjfinanceiro.repository.fornecedores;

import br.com.marcos.prjfinanceiro.dto.FornecedoresDto;
import br.com.marcos.prjfinanceiro.model.Fornecedores;
import br.com.marcos.prjfinanceiro.repository.filter.FornecedoresFilter;
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

public class FornecedoresQueryImpl implements FornecedoresQuery{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<FornecedoresDto> filtrar(FornecedoresFilter fornecedoresFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<FornecedoresDto> criteria = builder.createQuery(FornecedoresDto.class);
        Root<Fornecedores> root = criteria.from(Fornecedores.class);

        criteria.select(builder.construct(FornecedoresDto.class,
                root.get("id"),
                root.get("nomeFornecedores"),
                root.get("telefoneFornecedores")
        ));

        Predicate[] predicates = criarRestricoes(fornecedoresFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomeFornecedores")));

        TypedQuery<FornecedoresDto> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(fornecedoresFilter));
    }

    private Predicate[] criarRestricoes(FornecedoresFilter fornecedoresFilter, CriteriaBuilder builder, Root<Fornecedores> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (fornecedoresFilter != null && StringUtils.hasText(fornecedoresFilter.getNomeFornecedores())) {
            predicates.add(builder.like(root.get("nomeFornecedores"), "%" + fornecedoresFilter.getNomeFornecedores() + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<FornecedoresDto> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(FornecedoresFilter fornecedoresFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Fornecedores> root = criteria.from(Fornecedores.class);

        Predicate[] predicates = criarRestricoes(fornecedoresFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }
}
