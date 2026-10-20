package br.com.marcos.prjfinanceiro.service;

import br.com.marcos.prjfinanceiro.exceptions.EntidadeNaoEncontradaException;
import br.com.marcos.prjfinanceiro.repository.ContasPagarRepository;
import br.com.marcos.prjfinanceiro.model.ContasPagar;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContasPagarService {
    @Autowired
    private ContasPagarRepository contasPagarRepository;

    @Transactional
    public ContasPagar salvar(ContasPagar contasPagar) {
        return contasPagarRepository.save(contasPagar);
    }

    public ContasPagar buscarOuFalhar(Long contasPagarId) {
        return contasPagarRepository.findById(contasPagarId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Conta a pagar não encontrada com esse ID"));
    }

    @Transactional
    public void excluir(Long contasPagarId) {
        contasPagarRepository.deleteById(contasPagarId);
    }
}
