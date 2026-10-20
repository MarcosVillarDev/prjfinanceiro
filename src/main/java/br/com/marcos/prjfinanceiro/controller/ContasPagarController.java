package br.com.marcos.prjfinanceiro.controller;

import br.com.marcos.prjfinanceiro.repository.ContasPagarRepository;
import br.com.marcos.prjfinanceiro.repository.filter.ContasPagarFilter;
import br.com.marcos.prjfinanceiro.service.ContasPagarService;
import br.com.marcos.prjfinanceiro.dto.ContasPagarDto;
import br.com.marcos.prjfinanceiro.model.ContasPagar;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contaspagar")
public class ContasPagarController {
    @Autowired
    private ContasPagarRepository contasPagarRepository;

    @Autowired
    private ContasPagarService contasPagarService;

    @GetMapping
    public List<ContasPagar> listar() {
        return contasPagarRepository.findAll();
    }

    @GetMapping("/filtrar")
    public Page<ContasPagarDto> filtrar(ContasPagarFilter contasPagarFilter, Pageable pageable) {
        return contasPagarRepository.filtrar(contasPagarFilter, pageable);
    }

    @GetMapping("/{contaPagarId}")
    public ContasPagar buscar(@PathVariable Long contasPagarId) {
        return contasPagarService.buscarOuFalhar(contasPagarId);
    }

    @PostMapping
    public ContasPagar adicionar(@RequestBody ContasPagar contasPagar) {
        return contasPagarService.salvar(contasPagar);
    }

    @PutMapping("/{contasPagarId}")
    public ContasPagar atualizar(@PathVariable Long contasPagarId, @RequestBody ContasPagar contasPagar) {
        ContasPagar contasPagarAtual = contasPagarService.buscarOuFalhar(contasPagarId);
        BeanUtils.copyProperties(contasPagar, contasPagarAtual, "id");
        return contasPagarService.salvar(contasPagarAtual);
    }

    @DeleteMapping("/{contasPagarId}")
    public void remover(@PathVariable Long contasPagarId) {
        contasPagarService.excluir(contasPagarId);
    }
}
