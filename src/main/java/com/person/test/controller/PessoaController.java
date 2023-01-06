package com.person.test.controller;

import com.person.test.dto.PessoaDto;
import com.person.test.entity.Pessoa;
import com.person.test.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Validated

public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaDto salvar(@RequestBody @Valid PessoaDto pessoaDto){
        return this.pessoaService.salvar(pessoaDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaDto> listar(){
        return this.pessoaService.lista();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa buscar(@PathVariable Long id){
        return this.pessoaService.buscar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PessoaDto atualizar(@PathVariable Long id, @RequestBody @Valid PessoaDto pessoaDto){
        return this.pessoaService.atualizar(id, pessoaDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@PathVariable Long id){
        this.pessoaService.deletar(id);
    }
}
