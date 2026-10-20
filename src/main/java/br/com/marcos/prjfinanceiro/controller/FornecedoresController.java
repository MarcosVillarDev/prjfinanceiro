package br.com.marcos.prjfinanceiro.controller;

import br.com.marcos.prjfinanceiro.repository.FornecedoresRepository;
import br.com.marcos.prjfinanceiro.repository.filter.FornecedoresFilter;
import br.com.marcos.prjfinanceiro.dto.FornecedoresDto;
import br.com.marcos.prjfinanceiro.model.Fornecedores;
import br.com.marcos.prjfinanceiro.service.FornecedoresService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedoresController {
    @Autowired
    private FornecedoresRepository fornecedoresRepository;

    @Autowired
    private FornecedoresService fornecedoresService;

    @GetMapping
    public List<Fornecedores> listar() {
        return fornecedoresRepository.findAll();
    }

    @GetMapping("/filtrar")
    public Page<FornecedoresDto> filtrar(FornecedoresFilter fornecedoresFilter, Pageable pageable) {
        return fornecedoresRepository.filtrar(fornecedoresFilter, pageable);
    }

    @GetMapping("/{fornecedoresId}")
    public Fornecedores buscar(@PathVariable Long fornecedoresId) {
        return fornecedoresService.buscarOuFalhar(fornecedoresId);
    }

    @PostMapping
    public Fornecedores adicionar(@RequestBody Fornecedores fornecedores) {
        return fornecedoresService.salvar(fornecedores);
    }

    @PutMapping("/{fornecedoresId}")
    public Fornecedores atualizar(@PathVariable Long fornecedoresId, @RequestBody Fornecedores fornecedores) {
        Fornecedores fornecedoresAtual = fornecedoresService.buscarOuFalhar(fornecedoresId);
        BeanUtils.copyProperties(fornecedores, fornecedoresAtual, "id");
        return fornecedoresService.salvar(fornecedoresAtual);
    }

    @DeleteMapping("/{fornecedoresId}")
    public void remover(@PathVariable Long fornecedoresId) {
        fornecedoresService.excluir(fornecedoresId);
    }
}
