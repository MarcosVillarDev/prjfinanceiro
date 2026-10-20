package br.com.marcos.prjfinanceiro.service;

import br.com.marcos.prjfinanceiro.exceptions.EntidadeNaoEncontradaException;
import br.com.marcos.prjfinanceiro.model.Fornecedores;
import br.com.marcos.prjfinanceiro.repository.FornecedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FornecedoresService {
    @Autowired
    private FornecedoresRepository fornecedoresRepository;

    @Transactional
    public Fornecedores salvar(Fornecedores fornecedores) {
        return fornecedoresRepository.save(fornecedores);
    }

    public Fornecedores buscarOuFalhar(Long fornecedoresId) {
        return fornecedoresRepository.findById(fornecedoresId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado com esse ID"));
    }

    @Transactional
    public void excluir(Long fornecedoresId) {
        fornecedoresRepository.deleteById(fornecedoresId);
    }
}
